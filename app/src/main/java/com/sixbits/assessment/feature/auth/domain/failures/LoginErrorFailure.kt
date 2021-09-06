package com.sixbits.assessment.feature.auth.domain.failures

import com.sixbits.extention.Failure

class LoginErrorFailure constructor(private val reason: String?) : Failure.FeatureFailure() {
    override fun toString(): String {
        return "Error Logging In: ${reason ?: ""}"
    }
}
