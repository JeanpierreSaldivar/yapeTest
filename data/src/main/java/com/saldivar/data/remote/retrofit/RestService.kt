package com.saldivar.data.remote.retrofit

import com.saldivar.data.remote.common.ServiceUrl.LIST_RECIPE
import com.saldivar.data.remote.common.ServiceUrl.LOCATION
import com.saldivar.data.response.CoordinatesResponse
import com.saldivar.data.response.ListRecipeResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
interface RestService {

    @GET(LIST_RECIPE)
    suspend fun getListRecipe(): ListRecipeResponse

    @GET(LOCATION)
    suspend fun getLocation(
        @Path("country") country: String
    ): CoordinatesResponse

}