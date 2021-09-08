package com.sixbits.assessment.core.interactor

import com.sixbits.extention.Failure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class OneParamUseCase<out Type, in Param> where Type : Any {

    abstract suspend fun run(param: Param): Either<Failure, Type>

    operator fun invoke(
        param: Param,
        scope: CoroutineScope = GlobalScope,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) {
        scope.launch(Dispatchers.Main) {
            val deferred = async(Dispatchers.IO) {
                run(param)
            }
            onResult(deferred.await())
        }
    }

    class None
}
