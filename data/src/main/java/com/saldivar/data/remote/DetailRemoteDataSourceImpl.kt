package com.saldivar.data.remote

import com.saldivar.data.remote.retrofit.RestService
import com.saldivar.data.response.CoordinatesResponse

/**
 * Created by César Jeanpierre Saldivar on 27/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
class DetailRemoteDataSourceImpl(
    private val service: RestService
): DetailRemoteDataSource{
    override suspend fun getLocation(country: String): CoordinatesResponse {
        return service.getLocation(country)
    }
}