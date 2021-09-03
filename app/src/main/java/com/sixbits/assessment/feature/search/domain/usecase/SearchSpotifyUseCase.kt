package com.sixbits.assessment.feature.search.domain.usecase

import com.sixbits.assessment.feature.search.data.SpotifyRepository
import com.sixbits.assessment.feature.search.domain.datamodel.SpotifyDataModel
import com.sixbits.assessment.feature.search.domain.failures.NullQueryFailure
import com.sixbits.assessment.feature.search.domain.failures.SessionExpiredFailure
import com.sixbits.authenticator.AuthGuard
import com.sixbits.reactive.executor.PostExecutionThread
import com.sixbits.reactive.executor.ThreadExecutor
import com.sixbits.reactive.interactor.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchSpotifyUseCase @Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val spotifyRepository: SpotifyRepository,
    private val authGuard: AuthGuard
) : SingleUseCase<List<SpotifyDataModel>, String>(threadExecutor, postExecutionThread) {

    companion object {
        private const val TAG = "SearchSpotifyUseCase"
    }

    override fun buildUseCaseSingle(params: String?): Single<List<SpotifyDataModel>> {
        if (params == null) {
            return Single.error(NullQueryFailure())
        }
        if (!authGuard.userLoggedIn()) {
            return Single.error(SessionExpiredFailure())
        }
        return spotifyRepository.search(params, authGuard.getAuthToken())
    }
}
