package com.sixbits.assessment.feature.search.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sixbits.assessment.feature.search.domain.datamodel.SpotifyDataModel
import com.sixbits.assessment.feature.search.domain.failures.UnauthorizedException
import com.sixbits.assessment.feature.search.domain.usecase.GetLastSearchUseCase
import com.sixbits.assessment.feature.search.domain.usecase.SearchSpotifyUseCase
import com.sixbits.extention.Failure
import com.sixbits.platform.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchSpotifyUseCase: SearchSpotifyUseCase,
    private val getLastSearchUseCase: GetLastSearchUseCase
) : BaseViewModel() {

    private val _searchLiveData: MutableLiveData<List<SpotifyDataModel>> = MutableLiveData()
    val search: LiveData<List<SpotifyDataModel>> = _searchLiveData

    private var isConnected = false
    private var lastSearchQuery: String? = null
    private var hasQue = false

    fun fetchCache() {
        setLoading(true)
        getLastSearchUseCase(viewModelScope) {
            it.fold(::onError, ::onSuccess)
        }
    }

    fun searchFor(query: String) {
        if (query.isEmpty()) {
            handleFailure(EmptySearchQueryFailure())
            return
        }

        lastSearchQuery = query
        if (isConnected) {
            setLoading(true)
            searchSpotifyUseCase(query, viewModelScope) {
                it.fold(::onError, ::onSuccess)
            }
        } else {
            hasQue = true
            setLoading(false)
            handleFailure(Failure.NetworkConnection)
        }
    }

    fun setNetworkAvailable(isConnected: Boolean) {
        this.isConnected = isConnected
        if (hasQue) {
            hasQue = false
            lastSearchQuery?.let { searchFor(it) }
        }
    }

    private fun onSuccess(t: List<SpotifyDataModel>) {
        _searchLiveData.postValue(t)
        setLoading(false)
    }

    private fun onError(e: Throwable) {
        Log.d(TAG, "onError: $e at \n${e.stackTrace}")
        setLoading(false)
        if (e is UnauthorizedException) {
            handleFailure(Failure.UnauthorizedError)
        } else {
            handleFailure(Failure.NetworkConnection)
        }
    }

    companion object {
        private const val TAG = "SearchViewModel"
    }
}
