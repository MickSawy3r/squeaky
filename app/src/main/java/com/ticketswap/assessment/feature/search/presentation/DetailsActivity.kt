package com.ticketswap.assessment.feature.search.presentation

import android.content.Context
import android.content.Intent
import com.ticketswap.assessment.feature.search.domain.datamodel.SearchItemType
import com.ticketswap.assessment.feature.search.domain.datamodel.SpotifyDataModel
import com.ticketswap.platform.core.BaseFragment
import com.ticketswap.platform.core.ContainerActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : ContainerActivity() {
    override fun fragment(): BaseFragment {
        val item: SpotifyDataModel = intent.getParcelableExtra(INTENT_EXTRA_PARAM_MOVIE)
            ?: return ErrorFragment()

        return when (item.type) {
            SearchItemType.ELSE -> ErrorFragment()
            SearchItemType.ARTIST -> ArtistDetailsFragment.forItem(item)
            SearchItemType.TRACK -> TrackDetailsFragment.forItem(item)
        }
    }

    companion object {
        private const val INTENT_EXTRA_PARAM_MOVIE = "INTENT_PARAM_DETAILS"

        fun callingIntent(context: Context, searchItem: SpotifyDataModel) =
            Intent(context, DetailsActivity::class.java).apply {
                putExtra(INTENT_EXTRA_PARAM_MOVIE, searchItem)
            }
    }
}
