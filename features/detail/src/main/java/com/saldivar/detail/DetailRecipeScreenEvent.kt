package com.saldivar.detail

/**
 * Created by César Jeanpierre Saldivar on 26/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
sealed class DetailRecipeScreenEvent {
    data class OnMapLocationButtonClicked(
        val country: String,
    ) : DetailRecipeScreenEvent()
    data class onDataLoad(
        val recipe: RecipeModelDetailUI,
    ) : DetailRecipeScreenEvent()
}