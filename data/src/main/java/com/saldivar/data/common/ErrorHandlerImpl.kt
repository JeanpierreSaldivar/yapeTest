package com.saldivar.data.common
import com.saldivar.domain.common.Error
import com.saldivar.domain.common.ErrorHandler

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
class ErrorHandlerImpl : ErrorHandler {
    override fun getError(throwable: Throwable): Error {
        return Error.GenericError(
            null,
            throwable.message ?: ""
        )
    }
}