package com.sixbits.assessment.feature.search.domain.usecase

import com.sixbits.assessment.feature.search.data.SpotifyRepository
import com.sixbits.assessment.feature.search.domain.datamodel.ArtistDetailsDataModel
import com.sixbits.assessment.feature.search.domain.failures.SessionExpiredFailure
import com.sixbits.authenticator.AuthGuard
import com.sixbits.reactive.executor.PostExecutionThread
import com.sixbits.reactive.executor.ThreadExecutor
import com.sixbits.reactive.interactor.SingleParamUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoadArtistDetailsUseCase @Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val spotifyRepository: SpotifyRepository,
    private val authGuard: AuthGuard
) : SingleParamUseCase<ArtistDetailsDataModel, String>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseSingle(params: String): Single<ArtistDetailsDataModel> {
        if (!authGuard.userLoggedIn()) {
            return Single.error(SessionExpiredFailure())
        }

        return spotifyRepository.getArtistDetails(params, authGuard.getAuthToken())
    }
}
