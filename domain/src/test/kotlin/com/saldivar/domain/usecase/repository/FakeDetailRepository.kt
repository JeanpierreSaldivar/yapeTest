package com.saldivar.domain.usecase.repository

import com.saldivar.domain.model.CoordinatesModel
import com.saldivar.domain.repository.DetailRepository
import com.saldivar.domain.usecase.detail.GetLocationDishUseCaseTest.Companion.model
import java.util.Random

/**
 * Created by César Jeanpierre Saldivar on 27/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
class FakeDetailRepository: DetailRepository {
    override suspend fun getLocation(country: String): CoordinatesModel {
        if(country.isNotEmpty()){
            return model!!
        }else{
            throw RuntimeException("Country should not be empty")
        }
    }
}