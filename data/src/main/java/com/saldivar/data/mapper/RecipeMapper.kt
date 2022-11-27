package com.saldivar.data.mapper

import com.saldivar.data.response.IngredientsResponse
import com.saldivar.data.response.RecipeResponse
import com.saldivar.domain.model.IngredientsModel
import com.saldivar.domain.model.RecipeModel

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
class RecipeMapper {
    fun fromRecipeResponseToRecipeModel(result: RecipeResponse): RecipeModel {
        return RecipeModel(
            image = result.image,
            name = result.name,
            listIngredients = result.listIngredients.map {
                IngredientsModel(
                    it?.ingredient
                )
            }
        )
    }

}