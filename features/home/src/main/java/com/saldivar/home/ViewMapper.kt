package com.saldivar.home

import com.saldivar.domain.model.RecipeModel
import com.saldivar.home.adapter.IngredientsModelUI
import com.saldivar.home.adapter.RecipeModelUI
import javax.inject.Inject

/**
 * Created by César Jeanpierre Saldivar on 26/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
class ViewMapper
@Inject constructor() {
    fun fromModelToView(recipe: List<RecipeModel?>): List<RecipeModelUI?> {
        return recipe.map {
            RecipeModelUI(
                image = it?.image,
                name = it?.name,
                listIngredients = it?.listIngredients?.map{element->
                    IngredientsModelUI(
                        element?.ingredient
                    )
                }?: listOf(),
                description = it?.description,
                country = it?.country
            )
        }
    }
}