package com.saldivar.data.repository

import com.saldivar.data.mapper.RecipeMapper
import com.saldivar.data.remote.HomeRemoteDataSource
import com.saldivar.domain.model.RecipeModel
import com.saldivar.domain.repository.HomeRepository

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
class HomeRepositoryImpl (
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val recipeMapper : RecipeMapper
) : HomeRepository {
    override suspend fun getListRecipe(): List<RecipeModel?> {
        val result= homeRemoteDataSource.getListRecipe()
        return result.listRecipe.map{
            it?.let { it1 -> recipeMapper.fromRecipeResponseToRecipeModel(it1) }
        }
    }
}