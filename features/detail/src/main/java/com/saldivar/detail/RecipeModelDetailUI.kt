package com.saldivar.detail

/**
 * Created by César Jeanpierre Saldivar on 26/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
data class RecipeModelDetailUI(
    val image : String?,
    val name :String?,
    val listIngredients :List<IngredientsModelUI?>
)

data class IngredientsModelUI(
    val ingredient : String?,
)