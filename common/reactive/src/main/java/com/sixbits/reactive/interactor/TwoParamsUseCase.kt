package com.sixbits.reactive.interactor

import com.sixbits.reactive.executor.PostExecutionThread
import com.sixbits.reactive.executor.ThreadExecutor
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.DisposableSingleObserver

abstract class TwoParamsUseCase<Results : Any, in P0, in P1>(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : BaseReactiveUseCase(threadExecutor, postExecutionThread) {
    /**
     * Builds an [Single] which will be used when executing the current [TwoParamsUseCase].
     */
    abstract fun buildUseCaseSingle(p0: P0? = null, p1: P1? = null): Single<Results>

    /**
     * Executes the current use case.
     *
     * @param observer [DisposableSingleObserver] which will be listening to the observer build
     * by [buildUseCaseSingle] method.
     * @param p0 Parameters (Optional) used to build/execute this use case.
     * @param p1 Parameters (Optional) used to build/execute this use case.
     */
    fun execute(
        observer: DisposableSingleObserver<Results> = EmptySingleObserver(),
        p0: P0? = null,
        p1: P1? = null
    ) {
        val single = buildUseCaseSingleWithSchedulers(p0, p1)
        addDisposable(single.subscribeWith(observer))
    }

    /**
     * Builds a [Single] which will be used when executing the current [TwoParamsUseCase].
     * With provided Schedulers
     */
    private fun buildUseCaseSingleWithSchedulers(p0: P0?, p1: P1?): Single<Results> {
        return buildUseCaseSingle(p0, p1)
            .subscribeOn(threadExecutorScheduler)
            .observeOn(postExecutionThreadScheduler)
    }
}
