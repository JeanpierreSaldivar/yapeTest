package com.saldivar.domain.common

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
sealed class Error {
    data class GenericError(val code: Int? = null, val message: String) : Error()
    object NetworkError : Error()
}