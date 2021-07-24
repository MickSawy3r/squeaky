package com.ticketswap.assessment.feature.search.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ticketswap.assessment.core.navigation.Navigator
import com.ticketswap.assessment.databinding.ItemSearchBinding
import com.ticketswap.assessment.feature.search.domain.datamodel.SpotifyDataModel
import com.ticketswap.extention.loadCircularFromUrl
import javax.inject.Inject
import kotlin.properties.Delegates

class SearchAdapter @Inject constructor() : RecyclerView.Adapter<SearchAdapter.SearchVH>() {

    internal var collection: List<SpotifyDataModel> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (SpotifyDataModel, Navigator.Extras) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SearchVH {
        val layoutInflater = LayoutInflater.from(p0.context)
        val searchItem = ItemSearchBinding.inflate(layoutInflater, p0, false)
        return SearchVH(searchItem)
    }

    override fun onBindViewHolder(viewHolder: SearchVH, position: Int) {
        viewHolder.bind(collection[position], clickListener)
    }

    override fun getItemCount(): Int = collection.size

    class SearchVH(private val itemRow: ItemSearchBinding) :
        RecyclerView.ViewHolder(itemRow.root) {
        fun bind(
            searchItem: SpotifyDataModel,
            clickListener: (SpotifyDataModel, Navigator.Extras) -> Unit
        ) {
            itemRow.name.text = searchItem.name
            itemRow.ivImage.loadCircularFromUrl(searchItem.image)
            itemView.setOnClickListener {
                clickListener(
                    searchItem,
                    Navigator.Extras(itemView)
                )
            }
        }
    }
}
