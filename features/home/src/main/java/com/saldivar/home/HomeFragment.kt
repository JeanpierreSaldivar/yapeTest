package com.saldivar.home

import android.content.Context
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.saldivar.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    // return inflater.inflate(R.layout.fragment_home, container, false)
    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var navigator: HomeNavigator

    private lateinit var binding: FragmentHomeBinding

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

    private fun observeViewModelChanges()= with(viewModel) {
        screenState.observe(viewLifecycleOwner){
            onNewState(it.peekContent())
        }

        error.observe(viewLifecycleOwner){

        }

        listRecipe.observe(viewLifecycleOwner){

        }
    }

    private fun onNewState(state: HomeScreenState) {
        when (state) {
            is HomeScreenState.DataLoaded -> TODO()
            HomeScreenState.EmptyState -> TODO()
            HomeScreenState.Initial -> TODO()
            HomeScreenState.Loading -> showLoading()
        }
    }

    private fun showLoading() = with(binding) {
        loginContainer.isGone = true
        loginLoader.isVisible = true
    }
}