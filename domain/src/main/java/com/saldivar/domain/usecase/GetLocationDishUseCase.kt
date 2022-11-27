package com.saldivar.domain.usecase

import com.saldivar.domain.common.ErrorHandler
import com.saldivar.domain.common.SuspendableUseCase
import com.saldivar.domain.model.CoordinatesModel
import com.saldivar.domain.model.RecipeModel
import com.saldivar.domain.repository.DetailRepository
import com.saldivar.domain.repository.HomeRepository

/**
 * Created by César Jeanpierre Saldivar on 27/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
class GetLocationDishUseCase(
    private val repository: DetailRepository,
    errorHandler: ErrorHandler
) : SuspendableUseCase<GetLocationDishUseCase.Params, CoordinatesModel>(errorHandler) {

    data class Params(
        val country: String,
    )

    override suspend fun execute(parameters: Params): CoordinatesModel {
        return repository.getLocation(parameters.country)
    }


}