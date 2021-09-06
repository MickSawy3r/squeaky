package com.sixbits.assessment.feature.auth.domain.usecase

import com.sixbits.authenticator.AuthGuard
import com.sixbits.reactive.executor.PostExecutionThread
import com.sixbits.reactive.executor.ThreadExecutor
import com.sixbits.reactive.interactor.SingleParamUseCase
import com.spotify.sdk.android.auth.AuthorizationResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val authenticator: AuthGuard
) : SingleParamUseCase<Boolean, AuthorizationResponse>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseSingle(params: AuthorizationResponse): Single<Boolean> {
        authenticator.setLoggedIn(params.accessToken, params.expiresIn)
        return Single.just(true)
    }
}
