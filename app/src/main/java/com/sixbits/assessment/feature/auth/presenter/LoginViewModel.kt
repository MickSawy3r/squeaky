package com.sixbits.assessment.feature.auth.presenter

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sixbits.assessment.feature.auth.domain.usecase.LoginUserUseCase
import com.sixbits.extention.Failure
import com.sixbits.platform.core.BaseViewModel
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase
) : BaseViewModel() {

    private val _loggedInLiveData = MutableLiveData(false)
    val loggedInLiveData = _loggedInLiveData

    fun loginUser(authResponse: AuthorizationResponse) {
        setLoading(true)
        // Save Stuff
        Log.d(TAG, "loginUser: ")
        when (authResponse.type) {
            AuthorizationResponse.Type.TOKEN -> {
                loginUserUseCase(authResponse, viewModelScope) {
                    it.fold(::handleFailure, ::handleSuccess)
                }
            }
            AuthorizationResponse.Type.CODE -> {
                loginUserUseCase(authResponse, viewModelScope) {
                    it.fold(::handleFailure, ::handleSuccess)
                }
            }
            else -> handleFailure(Failure.NetworkConnection)
        }
    }

    private fun handleSuccess(isSuccess: Boolean) {
        Log.d(TAG, "onSuccess: Logged in $isSuccess")
        setLoading(false)
        _loggedInLiveData.postValue(true)
    }

    companion object {
        private const val TAG = "LoginViewModel"
    }
}
