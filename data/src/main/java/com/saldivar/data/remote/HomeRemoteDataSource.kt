package com.saldivar.data.remote

import com.saldivar.data.response.ListRecipeResponse

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
interface HomeRemoteDataSource {
    suspend fun getListRecipe(): ListRecipeResponse
}