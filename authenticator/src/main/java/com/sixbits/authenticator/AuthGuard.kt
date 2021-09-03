package com.sixbits.authenticator

import android.content.Context
import android.util.Log

class AuthGuard constructor(context: Context) {
    private var prefStore: PrefStore = PrefStore(context)

    fun userLoggedIn(): Boolean {
        val token = prefStore.getAuthToken()
        val expiresIn = prefStore.getExpiresIn()
        val addedTime = prefStore.getLoggedInAt()
        val currentTime = CalendarHelper.nowInSeconds()

        return currentTime - addedTime + expiresIn > 0 &&
                token != "" &&
                expiresIn > -1 &&
                addedTime > -1
    }

    fun setLoggedIn(accessToken: String, expiresIn: Int) {
        prefStore.setAuthToken(accessToken)
        prefStore.setExpiresIn(expiresIn)
        prefStore.setLoggedInAt()
    }

    fun logout() {
        prefStore.removeAuthToken()
        prefStore.removeExpiresIn()
        prefStore.removeLoggedInAt()
    }

    fun getAuthToken(): String = prefStore.getAuthToken()

    companion object {
        private const val TAG = "AuthGuard"
    }
}
