package com.saldivar.domain_di

import com.saldivar.data.common.ErrorHandlerImpl
import com.saldivar.data.mapper.LocationMapper
import com.saldivar.data.mapper.RecipeMapper
import com.saldivar.data.remote.DetailRemoteDataSourceImpl
import com.saldivar.data.remote.HomeRemoteDataSourceImpl
import com.saldivar.data.remote.retrofit.RestService
import com.saldivar.data.remote.retrofitClient
import com.saldivar.data.repository.DetailRepositoryImpl
import com.saldivar.data.repository.HomeRepositoryImpl
import com.saldivar.domain.usecase.GetListRecipeUseCase
import com.saldivar.domain.usecase.GetLocationDishUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by César Jeanpierre Saldivar on 27/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
@Module
@InstallIn(SingletonComponent::class)
object DetailDomainModule {
    @Provides
    fun providesGetLocationDishUseCase() :GetLocationDishUseCase =
        GetLocationDishUseCase(
            repository = DetailRepositoryImpl(
                detailRemoteDataSource = DetailRemoteDataSourceImpl(
                    service = retrofitClient.create(RestService::class.java)
                ),
                locationMapper = LocationMapper()
            ),
            errorHandler = ErrorHandlerImpl()
        )
}