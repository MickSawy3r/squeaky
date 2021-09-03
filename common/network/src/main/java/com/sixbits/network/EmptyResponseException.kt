package com.sixbits.network

class EmptyResponseException constructor(url: String) : UnsuccessfulRequest(url, "Empty Response")
