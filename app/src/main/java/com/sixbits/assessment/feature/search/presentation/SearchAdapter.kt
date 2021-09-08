package com.sixbits.assessment.feature.search.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sixbits.assessment.core.navigation.Navigator
import com.sixbits.assessment.databinding.ItemSearchBinding
import com.sixbits.assessment.feature.search.domain.datamodel.SpotifyDataModel
import com.sixbits.extention.loadCircularFromUrl
import javax.inject.Inject
import kotlin.properties.Delegates

class SearchAdapter @Inject constructor() :
    ListAdapter<SpotifyDataModel, SearchAdapter.SearchVH>(DIFF_UTILS) {

    internal var clickListener: (SpotifyDataModel, Navigator.Extras) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SearchVH {
        val layoutInflater = LayoutInflater.from(p0.context)
        val searchItem = ItemSearchBinding.inflate(layoutInflater, p0, false)
        return SearchVH(searchItem)
    }

    override fun onBindViewHolder(viewHolder: SearchVH, position: Int) {
        viewHolder.bind(currentList[position], clickListener)
    }

    override fun getItemCount(): Int = currentList.size

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

    class SearchItemDiffUtils : DiffUtil.ItemCallback<SpotifyDataModel>() {
        override fun areItemsTheSame(
            oldItem: SpotifyDataModel,
            newItem: SpotifyDataModel
        ): Boolean {
            return oldItem.name == newItem.name && newItem.type == oldItem.type
        }

        override fun areContentsTheSame(
            oldItem: SpotifyDataModel,
            newItem: SpotifyDataModel
        ): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.image == oldItem.image
        }
    }

    companion object {
        private val DIFF_UTILS = SearchItemDiffUtils()
    }
}
