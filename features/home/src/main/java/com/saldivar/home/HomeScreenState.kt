package com.saldivar.home

import com.saldivar.domain.model.RecipeModel

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
sealed class HomeScreenState {
    object Initial : HomeScreenState()
    object Loading : HomeScreenState()
    data class DataLoaded(
        val taskList: List<RecipeModel>
    ) : HomeScreenState()
    object EmptyState : HomeScreenState()
}