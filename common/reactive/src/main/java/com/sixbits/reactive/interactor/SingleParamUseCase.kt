package com.sixbits.reactive.interactor

import com.sixbits.reactive.executor.PostExecutionThread
import com.sixbits.reactive.executor.ThreadExecutor
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.DisposableSingleObserver

abstract class SingleParamUseCase<Results : Any, in Params>(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : BaseReactiveUseCase(threadExecutor, postExecutionThread) {
    /**
     * Builds an [Single] which will be used when executing the current [SingleParamUseCase].
     */
    abstract fun buildUseCaseSingle(params: Params): Single<Results>

    /**
     * Executes the current use case.
     *
     * @param observer [DisposableSingleObserver] which will be listening to the observer build
     * by [buildUseCaseSingle] method.
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    fun execute(
        observer: DisposableSingleObserver<Results> = EmptySingleObserver(),
        params: Params
    ) {
        val single = buildUseCaseSingleWithSchedulers(params)
        addDisposable(single.subscribeWith(observer))
    }

    /**
     * Builds a [Single] which will be used when executing the current [SingleParamUseCase].
     * With provided Schedulers
     */
    private fun buildUseCaseSingleWithSchedulers(params: Params): Single<Results> {
        return buildUseCaseSingle(params)
            .subscribeOn(threadExecutorScheduler)
            .observeOn(postExecutionThreadScheduler)
    }
}
