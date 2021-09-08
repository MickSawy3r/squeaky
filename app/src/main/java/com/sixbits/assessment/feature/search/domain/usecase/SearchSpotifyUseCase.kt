package com.sixbits.assessment.feature.search.domain.usecase

import com.sixbits.assessment.core.interactor.Either
import com.sixbits.assessment.core.interactor.OneParamUseCase
import com.sixbits.assessment.feature.search.data.SpotifyRepository
import com.sixbits.assessment.feature.search.domain.datamodel.SpotifyDataModel
import com.sixbits.assessment.feature.search.domain.failures.SessionExpiredFailure
import com.sixbits.authenticator.AuthGuard
import com.sixbits.extention.Failure
import javax.inject.Inject

class SearchSpotifyUseCase @Inject constructor(
    private val spotifyRepository: SpotifyRepository,
    private val authGuard: AuthGuard
) : OneParamUseCase<List<SpotifyDataModel>, String>() {

    override suspend fun run(param: String): Either<Failure, List<SpotifyDataModel>> {
        return when (authGuard.userLoggedIn()) {
            true -> spotifyRepository.search(param, authGuard.getAuthToken())
            false -> Either.Left(SessionExpiredFailure)
        }
    }
}
