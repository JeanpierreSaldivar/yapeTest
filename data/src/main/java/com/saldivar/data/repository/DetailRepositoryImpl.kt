package com.saldivar.data.repository

import com.saldivar.data.mapper.LocationMapper
import com.saldivar.data.mapper.RecipeMapper
import com.saldivar.data.remote.DetailRemoteDataSource
import com.saldivar.data.remote.HomeRemoteDataSource
import com.saldivar.domain.model.CoordinatesModel
import com.saldivar.domain.repository.DetailRepository
import com.saldivar.domain.repository.HomeRepository

/**
 * Created by César Jeanpierre Saldivar on 27/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
class DetailRepositoryImpl(
    private val detailRemoteDataSource: DetailRemoteDataSource,
    private val locationMapper : LocationMapper
) : DetailRepository {
    override suspend fun getLocation(country: String): CoordinatesModel {
        val result = detailRemoteDataSource.getLocation(country)
        return locationMapper.fromCoordinatesResponseToCoordinatesModel(result)
    }
}