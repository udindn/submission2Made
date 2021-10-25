package com.arrayyan.dicoding.fragment.adapter

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

class ContentAdapter : RecyclerView.Adapter<ContentAdapter.ListViewHolder>() {

    private var listData = ArrayList<ContentModel>()
    var onItemClick: ((ContentModel) -> Unit)? = null

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
                ivDelete.visibility = View.GONE
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}