package com.sixbits.assessment.feature.search.datasource.network

data class ArtistDetailsResponse(
    val followers: Followers = Followers(),
    val genres: List<String> = listOf(),
    val id: String = "",
    val images: List<Image> = listOf(),
    val name: String = "",
) {

    data class Followers(
        val href: Any? = Any(),
        val total: Int = 0
    )

    data class Image(
        val height: Int = 0,
        val url: String = "",
        val width: Int = 0
    )
}
