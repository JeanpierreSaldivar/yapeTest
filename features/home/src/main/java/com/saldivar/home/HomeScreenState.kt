package com.saldivar.home

import com.saldivar.domain.model.RecipeModel
import com.saldivar.home.adapter.RecipeModelUI

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
sealed class HomeScreenState {
    object Loading : HomeScreenState()
    data class DataLoaded(
        val recipeList: List<RecipeModelUI?>
    ) : HomeScreenState()
    object EmptyState : HomeScreenState()
}