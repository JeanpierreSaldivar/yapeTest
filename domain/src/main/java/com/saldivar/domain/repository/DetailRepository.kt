package com.saldivar.domain.repository

import com.saldivar.domain.model.CoordinatesModel
import com.saldivar.domain.model.RecipeModel

/**
 * Created by César Jeanpierre Saldivar on 27/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
interface DetailRepository {
    suspend fun getLocation(country: String): CoordinatesModel
}