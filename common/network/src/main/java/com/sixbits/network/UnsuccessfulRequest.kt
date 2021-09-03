package com.sixbits.network

open class UnsuccessfulRequest constructor(private val url: String, private val error: String?) : Exception() {
    override fun toString(): String {
        return "Bad Response to URL $url: $error"
    }
}
