package com.sixbits.assessment.feature.search.domain.usecase

import com.sixbits.assessment.core.interactor.Either
import com.sixbits.assessment.core.interactor.OneParamUseCase
import com.sixbits.assessment.feature.search.data.SpotifyRepository
import com.sixbits.assessment.feature.search.domain.datamodel.TrackDetailsDataModel
import com.sixbits.assessment.feature.search.domain.failures.SessionExpiredFailure
import com.sixbits.authenticator.AuthGuard
import com.sixbits.extention.Failure
import javax.inject.Inject

class LoadTrackDetailsUseCase @Inject constructor(
    private val spotifyRepository: SpotifyRepository,
    private val authGuard: AuthGuard
) : OneParamUseCase<TrackDetailsDataModel, String>() {

    override suspend fun run(param: String): Either<Failure, TrackDetailsDataModel> {
        return when (authGuard.userLoggedIn()) {
            false -> Either.Left(SessionExpiredFailure)
            true -> spotifyRepository.getTrackDetails(param, authGuard.getAuthToken())
        }
    }
}
