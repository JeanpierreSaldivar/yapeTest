package com.saldivar.domain.usecase

import com.saldivar.domain.common.ErrorHandler
import com.saldivar.domain.common.SuspendableUseCase
import com.saldivar.domain.model.RecipeModel
import com.saldivar.domain.repository.HomeRepository

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
class GetListRecipeUseCase (
    private val repository: HomeRepository,
    errorHandler: ErrorHandler
) : SuspendableUseCase<Unit, List<RecipeModel?>>(errorHandler) {

    override suspend fun execute(parameters: Unit): List<RecipeModel?> {
        return repository.getListRecipe()
    }
}