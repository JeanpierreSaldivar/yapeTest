package com.saldivar.data.remote

import com.saldivar.data.remote.retrofit.RestService
import com.saldivar.data.response.CoordinatesResponse
import com.saldivar.data.response.ListRecipeResponse

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
class HomeRemoteDataSourceImpl (
    private val service: RestService
) : HomeRemoteDataSource {

    override suspend fun getListRecipe(): ListRecipeResponse {
        return  service.getListRecipe()
    }
}