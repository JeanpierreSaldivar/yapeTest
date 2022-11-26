package com.saldivar.domain.common

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
interface ErrorHandler {
    fun getError(throwable: Throwable): Error
}