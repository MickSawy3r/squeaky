package com.sixbits.reactive.interactor

import com.sixbits.reactive.executor.PostExecutionThread
import com.sixbits.reactive.executor.ThreadExecutor
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.DisposableSingleObserver

abstract class NoParamsUseCase<Results : Any>(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : BaseReactiveUseCase(threadExecutor, postExecutionThread) {
    /**
     * Builds an [Single] which will be used when executing the current [SingleParamUseCase].
     */
    abstract fun buildUseCaseSingle(): Single<Results>

    /**
     * Executes the current use case.
     *
     * @param observer [DisposableSingleObserver] which will be listening to the observer build
     * by [buildUseCaseSingle] method.
     */
    fun execute(observer: DisposableSingleObserver<Results> = EmptySingleObserver()) {
        val single = buildUseCaseSingleWithSchedulers()
        addDisposable(single.subscribeWith(observer))
    }

    /**
     * Builds a [Single] which will be used when executing the current [NoParamsUseCase].
     * With provided Schedulers
     */
    private fun buildUseCaseSingleWithSchedulers(): Single<Results> {
        return buildUseCaseSingle()
            .subscribeOn(threadExecutorScheduler)
            .observeOn(postExecutionThreadScheduler)
    }
}
