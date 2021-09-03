package com.sixbits.assessment.feature.search.datasource.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrackDetailsResponse(
    val album: Album = Album(),
    val artists: List<Artist> = listOf(),
    val id: String = "",
    val name: String = "",
) {
    data class Artist(val name: String = "")

    data class Album(
        val images: List<Image> = listOf(),
        @Json(name = "release_date")
        val releaseDate: String = "",
    ) {
        data class Image(val url: String = "")
    }
}
