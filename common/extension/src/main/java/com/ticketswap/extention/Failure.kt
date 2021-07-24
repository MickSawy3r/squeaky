package com.ticketswap.extention

sealed class Failure : Exception() {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    object UnauthorizedError : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()
}
