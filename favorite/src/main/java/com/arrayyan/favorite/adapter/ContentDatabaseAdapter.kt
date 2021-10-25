package com.arrayyan.favorite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arrayyan.core.databinding.ItemContentBinding
import com.arrayyan.core.domain.model.ContentModel
import com.arrayyan.core.utils.loadImage
import com.arrayyan.dicoding.BuildConfig
import com.arrayyan.dicoding.R
import java.util.ArrayList

class ContentDatabaseAdapter: RecyclerView.Adapter<ContentDatabaseAdapter.ListViewHolder>() {

    companion object {
        const val DETAIL_CONTENT = 1
        const val DELETE_CONTENT = 2
    }

    private var listData = ArrayList<ContentModel>()
    var onItemClick: ((ContentModel, Int) -> Unit)? = null

    fun setData(newListData: List<ContentModel>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemContentBinding.bind(itemView)
        fun bind(data: ContentModel) {
            with(binding) {
                loadImage(itemView.context, "${BuildConfig.BASE_URL_POSTER}w185/${data.posterPath}", ivCover, R.drawable.ic_load,
                    pbCoverItem.root)
                tvTitle.text = data.title
                tvDescription.text = data.overview
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition], DETAIL_CONTENT)
            }
            binding.ivDelete.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition], DELETE_CONTENT)
            }
        }
    }
}