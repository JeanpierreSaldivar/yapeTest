package com.saldivar.detail

import android.content.Context
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.saldivar.core.*
import com.saldivar.detail.databinding.FragmentDetailRecipeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailRecipeFragment : Fragment() {

    private val viewModel: DetailRecipeViewModel by viewModels()

    private val args : DetailRecipeFragmentArgs by navArgs()

    @Inject
    lateinit var navigator: DetailRecipeNavigator

    private lateinit var binding: FragmentDetailRecipeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val contextThemeWrapper: Context =
            ContextThemeWrapper(
                requireContext(), com.saldivar.core.R.style.DetailTheme
            )
        val localInflater: LayoutInflater = inflater.cloneInContext(contextThemeWrapper)
        binding = FragmentDetailRecipeBinding.inflate(localInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelChanges()
        initViews()
    }

    private fun observeViewModelChanges()= with(viewModel) {
        screenState.observeEvent(viewLifecycleOwner){
            onNewState(it)
        }
        error.observeEvent(viewLifecycleOwner){
            if (it.show) {
                hideLoading()
                showSnackBarError(it.message, Snackbar.LENGTH_LONG)
            }
        }
    }

    private fun initViews() {
        setupAddButton()
        setDataRecipeModelDetail()
    }

    private fun onNewState(state: DetailRecipeScreenState) {
        when(state){
            is DetailRecipeScreenState.LoadData -> {
                showData(state.recipe)
            }
            DetailRecipeScreenState.hasLocation -> {
                hideLoading()
                showButtonLocation()
            }
            DetailRecipeScreenState.hasNotLocation -> {
                hideLoading()
                hideButtonLocation()
            }
            is DetailRecipeScreenState.LoadLocation -> {
                hideLoading()
                openMap(state)
            }
            DetailRecipeScreenState.Loading -> showLoading()
        }
    }

    private fun openMap(state: DetailRecipeScreenState.LoadLocation) {
        navigator.navigateOnMap(longitude = state.longitude, latitude = state.latitude, countryName = state.countryName)
    }

    private fun showLoading()= with(binding) {
        clLoading.visibility = View.VISIBLE
    }

    private fun hideLoading()= with(binding) {
        clLoading.visibility = View.GONE
    }

    private fun hideButtonLocation()= with(binding) {
        buttonMap.visibility = View.GONE
    }

    private fun showButtonLocation() = with(binding){
        buttonMap.visibility = View.VISIBLE
    }

    private fun showData(recipe: RecipeModelDetailUI)=with(binding) {
        recipe.image?.let { imageDish.loadByResourceWithoutCache(it) }
        nameDish.text = recipe.name?.convertCapitalized()
        var ingredientes = ""
        recipe.listIngredients.forEach {element->
            val sign = if(element?.ingredient == recipe.listIngredients.last()?.ingredient) "." else ","
            element?.run{ ingredientes += "${element.ingredient}$sign " }
        }
        ingredientsDish.text =ingredientes
        descriptionDish.text = recipe.description
    }

    private fun setDataRecipeModelDetail() {
        viewModel.postEvent(DetailRecipeScreenEvent.onDataLoad(recipeModelDetail()))
    }

    private fun setupAddButton()= with(binding) {
        backArrow.setOnClickListener{
            navigator.onBack()
        }
        buttonMap.setOnClickListener{
            recipeModelDetail().country?.run{
                viewModel.postEvent(DetailRecipeScreenEvent.OnMapLocationButtonClicked(this))
            }?:run{
                hideButtonLocation()
            }
        }
    }

    private fun recipeModelDetail() =args.recipeModelDetail.convertObject<RecipeModelDetailUI>()
}