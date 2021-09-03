package com.sixbits.assessment.feature.auth.domain.usecase

import com.spotify.sdk.android.auth.AuthorizationResponse
import com.sixbits.assessment.feature.auth.domain.failures.LoginErrorFailure
import com.sixbits.authenticator.AuthGuard
import com.sixbits.reactive.executor.PostExecutionThread
import com.sixbits.reactive.executor.ThreadExecutor
import com.sixbits.reactive.interactor.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val authenticator: AuthGuard
) : SingleUseCase<Boolean, AuthorizationResponse>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseSingle(params: AuthorizationResponse?): Single<Boolean> {
        if (params == null) {
            return Single.error(LoginErrorFailure("Null Login Response!"))
        }
        authenticator.setLoggedIn(params.accessToken, params.expiresIn)
        return Single.just(true)
    }
}
