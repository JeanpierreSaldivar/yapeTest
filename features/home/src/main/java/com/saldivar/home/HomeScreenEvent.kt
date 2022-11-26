package com.saldivar.home

import com.saldivar.domain.model.RecipeModel

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
sealed class HomeScreenEvent {
    data class OnTextChanged(
        val searchNameIngredient: String,
    ) : HomeScreenEvent()
    data class OnDetailButtonClicked(
        val recipe: RecipeModel,
    ) : HomeScreenEvent()
    object LoadListRecipeAll : HomeScreenEvent()
}