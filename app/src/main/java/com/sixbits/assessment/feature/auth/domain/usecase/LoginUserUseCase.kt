package com.sixbits.assessment.feature.auth.domain.usecase

import com.sixbits.assessment.core.interactor.Either
import com.sixbits.assessment.core.interactor.OneParamUseCase
import com.sixbits.authenticator.AuthGuard
import com.sixbits.extention.Failure
import com.spotify.sdk.android.auth.AuthorizationResponse
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val authenticator: AuthGuard
) : OneParamUseCase<Boolean, AuthorizationResponse>() {
    override suspend fun run(param: AuthorizationResponse): Either<Failure, Boolean> {
        authenticator.setLoggedIn(param.accessToken, param.expiresIn)
        return Either.Right(true)
    }
}
