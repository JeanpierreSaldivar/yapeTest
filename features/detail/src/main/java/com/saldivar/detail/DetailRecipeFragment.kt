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
import com.saldivar.core.convertJson
import com.saldivar.core.convertObject
import com.saldivar.detail.databinding.FragmentDetailRecipeBinding
import javax.inject.Inject


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

    }

    private fun initViews() {
        setupAddButton()
        setDataRecipeModelDetail()
    }

    private fun setDataRecipeModelDetail() {
        val model = args.recipeModelDetail.convertObject(RecipeModelDetailUI::class.java)
    }

    private fun setupAddButton()= with(binding) {

    }

}