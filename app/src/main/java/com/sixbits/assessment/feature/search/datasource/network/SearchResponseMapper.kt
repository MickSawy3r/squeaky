package com.sixbits.assessment.feature.search.datasource.network

import com.sixbits.assessment.core.mapper.Mapper
import com.sixbits.assessment.feature.search.domain.datamodel.SearchItemType
import com.sixbits.assessment.feature.search.domain.datamodel.SpotifyDataModel

class SearchResponseMapper : Mapper<SearchResponse, List<SpotifyDataModel>> {
    override fun map(input: SearchResponse): List<SpotifyDataModel> {
        val searchResult = mutableListOf<SpotifyDataModel>()

        input.artists.items.forEach {
            var image = ""
            if (it.images.isNotEmpty()) {
                image = it.images[0].url
            }
            searchResult.add(
                SpotifyDataModel(
                    id = it.id,
                    name = it.name,
                    type = SearchItemType.ARTIST,
                    image = image
                )
            )
        }
        input.tracks.items.forEach {
            var image = ""
            if (it.album.images.isNotEmpty()) {
                image = it.album.images[0].url
            }
            searchResult.add(
                SpotifyDataModel(
                    id = it.id,
                    name = it.name,
                    type = SearchItemType.TRACK,
                    image = image
                )
            )
        }

        return searchResult
    }
}
