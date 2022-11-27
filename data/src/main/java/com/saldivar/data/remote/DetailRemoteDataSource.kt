package com.saldivar.data.remote

import com.saldivar.data.response.CoordinatesResponse

/**
 * Created by César Jeanpierre Saldivar on 27/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
interface DetailRemoteDataSource {
    suspend fun getLocation(country: String): CoordinatesResponse
}