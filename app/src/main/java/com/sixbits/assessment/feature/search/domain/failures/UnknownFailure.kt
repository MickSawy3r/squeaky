package com.sixbits.assessment.feature.search.domain.failures

import com.sixbits.extention.Failure

class UnknownFailure constructor(private val reason: String) : Failure.FeatureFailure() {
    override fun toString(): String {
        return "Error: $reason"
    }
}
