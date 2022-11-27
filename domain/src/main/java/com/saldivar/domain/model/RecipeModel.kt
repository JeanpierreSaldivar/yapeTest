package com.saldivar.domain.model

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
data class RecipeModel(
    val image : String?,
    val name :String?,
    val listIngredients :List<IngredientsModel?>,
    val description :String?,
    val country : String?
)

data class IngredientsModel(
    val ingredient : String?,
)