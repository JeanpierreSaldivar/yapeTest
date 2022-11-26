package com.saldivar.domain_di

import com.saldivar.data.common.ErrorHandlerImpl
import com.saldivar.data.mapper.RecipeMapper
import com.saldivar.data.remote.HomeRemoteDataSourceImpl
import com.saldivar.data.remote.retrofit.RestService
import com.saldivar.data.remote.retrofitClient
import com.saldivar.data.repository.HomeRepositoryImpl
import com.saldivar.domain.usecase.GetListRecipeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
@Module
@InstallIn(SingletonComponent::class)
object HomeDomainModule {
    @Provides
    fun providesGetListRecipeUseCase(): GetListRecipeUseCase =
        GetListRecipeUseCase(
            repository = HomeRepositoryImpl(
                homeRemoteDataSource = HomeRemoteDataSourceImpl(
                    service = retrofitClient.create(RestService::class.java)
                ),
                recipeMapper = RecipeMapper()
            ),
            errorHandler = ErrorHandlerImpl()
        )
}