package com.saldivar.home.adapter

/**
 * Created by César Jeanpierre Saldivar on 26/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
data class RecipeModelUI(
    val image : String?,
    val name :String?,
    val listIngredients :List<IngredientsModelUI?>
)

data class IngredientsModelUI(
    val ingredient : String?,
)
