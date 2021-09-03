@file:Suppress("ParameterListWrapping", "LongParameterList", "ConstructorParameterNaming")

package com.sixbits.assessment.feature.search.datasource.network

data class SearchResponse(
    val artists: Artists = Artists(),
    val tracks: Tracks = Tracks()
) {
    data class Artists(
        val items: List<Item> = listOf(),
    ) {
        data class Item(
            val id: String = "",
            val images: List<Image> = listOf(),
            val name: String = ""
        ) {

            data class Image(
                val url: String = "",
            )
        }
    }

    data class Tracks(
        val items: List<Item> = listOf(),
    ) {
        data class Item(
            val album: Album = Album(),
            val id: String = "",
            val name: String = "",
        ) {
            data class Album(
                val images: List<Image> = listOf(),
            ) {
                data class Image(
                    val url: String = "",
                )
            }
        }
    }
}
