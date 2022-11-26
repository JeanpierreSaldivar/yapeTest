package com.saldivar.data.remote.retrofit

import com.saldivar.data.remote.common.ServiceUrl.LIST_RECIPE
import com.saldivar.data.response.ListRecipeResponse
import retrofit2.http.GET

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
interface RestService {
    @GET(LIST_RECIPE)
    suspend fun getListRecipe(): ListRecipeResponse
}