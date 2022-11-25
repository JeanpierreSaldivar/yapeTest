package com.saldivar.home

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
interface HomeScreenState {
    object Loading : HomeScreenState
    data class Success(val data: String) : HomeScreenState
    data class Failure(val exception: Exception) : HomeScreenState
}