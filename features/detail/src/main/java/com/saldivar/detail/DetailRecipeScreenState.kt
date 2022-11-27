package com.saldivar.detail

import com.saldivar.domain.model.CoordinatesModel

/**
 * Created by César Jeanpierre Saldivar on 26/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
sealed class DetailRecipeScreenState {
    data class LoadData(val recipe: RecipeModelDetailUI) : DetailRecipeScreenState()
    object Loading : DetailRecipeScreenState()
    data class LoadLocation(val latitude: String,val longitude:String) : DetailRecipeScreenState()
    object hasNotLocation : DetailRecipeScreenState()
    object hasLocation : DetailRecipeScreenState()
}