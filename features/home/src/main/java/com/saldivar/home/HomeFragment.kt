package com.saldivar.home

import android.content.Context
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.saldivar.core.observeEvent
import com.saldivar.core.showSnackBarError
import com.saldivar.home.adapter.OnItemClickImpl
import com.saldivar.home.adapter.RecipeListAdapter
import com.saldivar.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var navigator: HomeNavigator

    private lateinit var binding: FragmentHomeBinding

    private val recipeListAdapter: RecipeListAdapter by lazy {
        RecipeListAdapter(OnItemClickImpl(navigator))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val contextThemeWrapper: Context =
            ContextThemeWrapper(
                requireContext(), com.saldivar.core.R.style.HomeTheme
            )
        val localInflater: LayoutInflater = inflater.cloneInContext(contextThemeWrapper)
        binding = FragmentHomeBinding.inflate(localInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelChanges()
        initViews()
    }

    private fun initViews() {
        onTextChange()
        setupAddButton()
        configureRecipeList()
        getListAll()
    }

    private fun onTextChange() {
        binding.etSearch.addTextChangedListener(onTextChanged = { text, _, _, _ ->
            viewModel.postEvent(
                HomeScreenEvent.OnTextChanged(
                    text.toString()
                )
            )
        })
    }

    private fun getListAll() {
        viewModel.postEvent(HomeScreenEvent.LoadListRecipeAll)
    }

    private fun setupAddButton() = with(binding){
        ivClose.setOnClickListener {
            if (etSearch.text?.isNotEmpty() == true) etSearch.setText("")
        }
    }

    private fun configureRecipeList() {
        with(binding.recycler) {
            this.adapter = recipeListAdapter
            this.layoutManager = LinearLayoutManager(context)
        }
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

    private fun onNewState(state: HomeScreenState) {
        when (state) {
            is HomeScreenState.DataLoaded ->{
                hideLoading()
                hideMessageEmpty()
                showListRecipe()
                this.recipeListAdapter.submitList(state.recipeList.toMutableList())
            }
            HomeScreenState.EmptyState -> {
                hideLoading()
                hideListRecipe()
                showMessageEmpty()
                this.recipeListAdapter.submitList(mutableListOf())
            }
            HomeScreenState.Loading -> {
                hideListRecipe()
                hideMessageEmpty()
                showLoading()
            }
        }
    }

    private fun showMessageEmpty() = with(binding) {
        emptyList.visibility = View.VISIBLE
    }
    private fun hideMessageEmpty() = with(binding) {
        emptyList.visibility = View.GONE
    }
    private fun showListRecipe() = with(binding) {
        recycler.visibility = View.VISIBLE
    }
    private fun hideListRecipe() = with(binding) {
        recycler.visibility = View.GONE
    }

    private fun showLoading() = with(binding) {
        shimmer.apply{
            visibility = View.VISIBLE
            startShimmer()
        }
    }
    private fun hideLoading() = with(binding)  {
        shimmer.apply{
            stopShimmer()
            visibility = View.GONE
        }
    }
}