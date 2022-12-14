package com.saldivar.data.response


/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
data class ListRecipeResponse(
    val listRecipe : List<RecipeResponse?>
)

data class RecipeResponse(
    val image : String?,
    val name :String?,
    val listIngredients :List<IngredientsResponse?>,
    val description :String?,
    val country :String?,
)

data class IngredientsResponse(
    val ingredient : String?,
)

