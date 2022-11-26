package com.saldivar.domain.repository

import com.saldivar.domain.model.RecipeModel

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
interface HomeRepository {
    suspend fun getListRecipe(): List<RecipeModel?>
}