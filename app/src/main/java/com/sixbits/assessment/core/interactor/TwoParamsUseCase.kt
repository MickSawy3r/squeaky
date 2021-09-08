package com.sixbits.assessment.core.interactor

import com.sixbits.extention.Failure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class TwoParamsUseCase<out Type, in Param1, in Param2> where Type : Any {

    abstract suspend fun run(param1: Param1 ,param2: Param2): Either<Failure, Type>

    operator fun invoke(
        param1: Param1,
        param2: Param2,
        scope: CoroutineScope = GlobalScope,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) {
        scope.launch(Dispatchers.Main) {
            val deferred = async(Dispatchers.IO) {
                run(param1, param2)
            }
            onResult(deferred.await())
        }
    }

    class None
}
