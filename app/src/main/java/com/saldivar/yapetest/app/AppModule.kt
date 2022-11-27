package com.saldivar.yapetest.app

import com.saldivar.detail.DetailRecipeNavigator
import com.saldivar.home.HomeNavigator
import com.saldivar.yapetest.RouteNavigator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by César Jeanpierre Saldivar on 26/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHomeNavigator(): HomeNavigator = RouteNavigator

    @Singleton
    @Provides
    fun provideRouteNavigator(): RouteNavigator = RouteNavigator

    @Singleton
    @Provides
    fun provideDetailRecipeNavigator(): DetailRecipeNavigator = RouteNavigator
}