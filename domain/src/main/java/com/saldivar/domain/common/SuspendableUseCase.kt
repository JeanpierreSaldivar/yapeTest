package com.saldivar.domain.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
abstract class SuspendableUseCase<in P, out R>(
    private val errorHandler: ErrorHandler,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend operator fun invoke(parameters: P): ResultWrapper<R> {
        return withContext(coroutineDispatcher) {
            try {
                ResultWrapper.Success<R>(execute(parameters))
            } catch (throwable: Throwable) {
                ResultWrapper.Failure(errorHandler.getError(throwable))
            }
        }
    }

    protected abstract suspend fun execute(parameters: P): R
}
