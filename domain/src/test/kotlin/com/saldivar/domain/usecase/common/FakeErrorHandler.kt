package com.saldivar.domain.usecase.common
import com.saldivar.domain.common.Error
import com.saldivar.domain.common.ErrorHandler

/**
 * Created by César Jeanpierre Saldivar on 27/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 */
class FakeErrorHandler : ErrorHandler {
    override fun getError(throwable: Throwable): Error {
        return Error.GenericError(message = throwable.message ?: "Unexpected error")
    }

}