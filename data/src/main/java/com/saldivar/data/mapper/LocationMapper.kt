package com.saldivar.data.mapper

import com.saldivar.data.response.CoordinatesResponse
import com.saldivar.data.response.RecipeResponse
import com.saldivar.domain.model.CoordinatesModel
import com.saldivar.domain.model.IngredientsModel
import com.saldivar.domain.model.RecipeModel

/**
 * Created by César Jeanpierre Saldivar on 27/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
class LocationMapper {
    fun fromCoordinatesResponseToCoordinatesModel(result: CoordinatesResponse): CoordinatesModel {
        return CoordinatesModel(
            latitude = result.latitude,
            longitude = result.longitude
        )
    }
}