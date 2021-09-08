package com.sixbits.assessment.feature.search.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sixbits.assessment.feature.search.domain.datamodel.TrackDetailsDataModel
import com.sixbits.assessment.feature.search.domain.failures.UnauthorizedException
import com.sixbits.assessment.feature.search.domain.usecase.LoadTrackDetailsUseCase
import com.sixbits.extention.Failure
import com.sixbits.platform.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrackDetailsViewModel @Inject constructor(
    private val loadTrackDetailsUseCase: LoadTrackDetailsUseCase
) : BaseViewModel() {

    private val _detailsLiveDate = MutableLiveData<TrackDetailsDataModel>()
    val details: LiveData<TrackDetailsDataModel> = _detailsLiveDate

    fun loadDetails(id: String) {
        Log.d(TAG, "loadDetails: $id")
        setLoading(true)
        loadTrackDetailsUseCase(id, viewModelScope) {
            it.fold(::handleError, ::handleSuccess)
        }
    }

    private fun handleSuccess(track: TrackDetailsDataModel) {
        setLoading(false)
        _detailsLiveDate.postValue(track)
    }

    private fun handleError(e: Failure) {
        Log.d(TAG, "onError: $e")
        setLoading(false)
        if (e is UnauthorizedException) {
            handleFailure(Failure.UnauthorizedError)
        }
        handleFailure(Failure.NetworkConnection)
    }

    companion object {
        private const val TAG = "DetailsViewModel"
    }
}
