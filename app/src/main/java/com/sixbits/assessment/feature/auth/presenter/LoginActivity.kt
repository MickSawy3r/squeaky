package com.sixbits.assessment.feature.auth.presenter

import android.content.Context
import android.content.Intent
import com.sixbits.platform.core.BaseFragment
import com.sixbits.platform.core.ContainerActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ContainerActivity() {
    override fun fragment(): BaseFragment = LoginFragment()

    companion object {
        fun callingIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}
