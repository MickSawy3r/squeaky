package com.sixbits.network

class UnauthorizedException constructor(url: String) : UnsuccessfulRequest(
    url, "Unauthorized access to url: $url"
)
