package com.sixbits.assessment.feature.search.domain.usecase

import com.sixbits.assessment.core.interactor.Either
import com.sixbits.assessment.core.interactor.NoParamsUseCase
import com.sixbits.assessment.feature.search.data.SpotifyRepository
import com.sixbits.assessment.feature.search.domain.datamodel.SpotifyDataModel
import com.sixbits.extention.Failure
import javax.inject.Inject

class GetLastSearchUseCase @Inject constructor(
    private val spotifyRepository: SpotifyRepository
) : NoParamsUseCase<List<SpotifyDataModel>>() {
    override suspend fun run(): Either<Failure, List<SpotifyDataModel>> =
        spotifyRepository.getCached()
}
