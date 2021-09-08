package com.sixbits.assessment.feature.search.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sixbits.assessment.feature.search.domain.datamodel.ArtistDetailsDataModel
import com.sixbits.assessment.feature.search.domain.failures.UnauthorizedException
import com.sixbits.assessment.feature.search.domain.usecase.LoadArtistDetailsUseCase
import com.sixbits.extention.Failure
import com.sixbits.platform.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArtistDetailsViewModel @Inject constructor(
    private val loadArtistDetailsUseCase: LoadArtistDetailsUseCase
) : BaseViewModel() {

    private val _detailsLiveDate = MutableLiveData<ArtistDetailsDataModel>()
    val details: LiveData<ArtistDetailsDataModel> = _detailsLiveDate

    fun loadDetails(id: String) {
        Log.d(TAG, "loadDetails: $id")
        setLoading(true)
        loadArtistDetailsUseCase(id, viewModelScope) {
            it.fold(::onError, ::onSuccess)
        }
    }

    private fun onSuccess(t: ArtistDetailsDataModel) {
        setLoading(false)
        _detailsLiveDate.postValue(t)
    }

    private fun onError(e: Throwable?) {
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
