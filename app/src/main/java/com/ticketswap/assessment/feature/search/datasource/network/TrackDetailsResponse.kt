package com.ticketswap.assessment.feature.search.datasource.network


import com.squareup.moshi.Json

data class TrackDetailsResponse(
    val album: Album = Album(),
    val artists: List<Artist> = listOf(),
    @Json(name = "disc_number")
    val discNumber: Int = 0,
    @Json(name = "duration_ms")
    val durationMs: Int = 0,
    val explicit: Boolean = false,
    @Json(name = "external_ids")
    val externalIds: ExternalIds = ExternalIds(),
    @Json(name = "external_urls")
    val externalUrls: ExternalUrls = ExternalUrls(),
    val href: String = "",
    val id: String = "",
    @Json(name = "is_local")
    val isLocal: Boolean = false,
    @Json(name = "is_playable")
    val isPlayable: Boolean = false,
    @Json(name = "linked_from")
    val linkedFrom: LinkedFrom = LinkedFrom(),
    val name: String = "",
    val popularity: Int = 0,
    @Json(name = "preview_url")
    val previewUrl: Any? = Any(),
    @Json(name = "track_number")
    val trackNumber: Int = 0,
    val type: String = "",
    val uri: String = ""
) {
    data class Album(
        @Json(name = "album_type")
        val albumType: String = "",
        val artists: List<Artist> = listOf(),
        @Json(name = "external_urls")
        val externalUrls: ExternalUrls = ExternalUrls(),
        val href: String = "",
        val id: String = "",
        val images: List<Image> = listOf(),
        val name: String = "",
        @Json(name = "release_date")
        val releaseDate: String = "",
        @Json(name = "release_date_precision")
        val releaseDatePrecision: String = "",
        @Json(name = "total_tracks")
        val totalTracks: Int = 0,
        val type: String = "",
        val uri: String = ""
    ) {
        data class Artist(
            @Json(name = "external_urls")
            val externalUrls: ExternalUrls = ExternalUrls(),
            val href: String = "",
            val id: String = "",
            val name: String = "",
            val type: String = "",
            val uri: String = ""
        ) {
            data class ExternalUrls(
                val spotify: String = ""
            )
        }

        data class ExternalUrls(
            val spotify: String = ""
        )

        data class Image(
            val height: Int = 0,
            val url: String = "",
            val width: Int = 0
        )
    }

    data class Artist(
        @Json(name = "external_urls")
        val externalUrls: ExternalUrls = ExternalUrls(),
        val href: String = "",
        val id: String = "",
        val name: String = "",
        val type: String = "",
        val uri: String = ""
    ) {
        data class ExternalUrls(
            val spotify: String = ""
        )
    }

    data class ExternalIds(
        val isrc: String = ""
    )

    data class ExternalUrls(
        val spotify: String = ""
    )

    data class LinkedFrom(
        @Json(name = "external_urls")
        val externalUrls: ExternalUrls = ExternalUrls(),
        val href: String = "",
        val id: String = "",
        val type: String = "",
        val uri: String = ""
    ) {
        data class ExternalUrls(
            val spotify: String = ""
        )
    }
}