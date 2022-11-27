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
import com.saldivar.core.*
import com.saldivar.detail.databinding.FragmentDetailRecipeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class DetailRecipeFragment : Fragment() {

    private val viewModel: DetailRecipeViewModel by viewModels()

    private val args: DetailRecipeFragmentArgs by navArgs()

    @Inject
    lateinit var navigator: DetailRecipeNavigator

    private lateinit var binding: FragmentDetailRecipeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val contextThemeWrapper: Context =
            ContextThemeWrapper(
                requireContext(), com.saldivar.core.R.style.HomeTheme
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
    }

    private fun onNewState(state: DetailRecipeScreenState) {
        when(state){
            is DetailRecipeScreenState.LoadData -> {
                showData(state.recipe)
            }
            DetailRecipeScreenState.hasLocation -> showButtonLocation()
            DetailRecipeScreenState.hasNotLocation -> hideButtonLocation()
            is DetailRecipeScreenState.LoadLocation -> TODO()
            DetailRecipeScreenState.Loading -> TODO()
        }
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
        descriptionDish.text = recipe.description
    }

    private fun initViews() {
        setupAddButton()
        setDataRecipeModelDetail()
    }

    private fun setDataRecipeModelDetail() {
        val model = args.recipeModelDetail.convertObject<RecipeModelDetailUI>()
        viewModel.postEvent(DetailRecipeScreenEvent.onDataLoad(model))
    }

    private fun setupAddButton()= with(binding) {
        backArrow.setOnClickListener{
            navigator.onBack()
        }
        buttonMap.setOnClickListener{

        }
    }

}