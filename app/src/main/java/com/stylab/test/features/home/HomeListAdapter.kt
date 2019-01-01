package com.stylab.test.features.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stylab.test.data.list.model.ListModel
import com.stylab.test.databinding.GridListItemBinding

class HomeListAdapter : ListAdapter<ListModel, HomeListAdapter.ViewHolder>(HomeListDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemList = getItem(position)
        holder.apply {
            bind(itemList)
            //itemView.tag = itemList
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(GridListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    class ViewHolder(
        private val binding: GridListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ListModel) {
            binding.apply {
                Log.e("Bind","call")
                listItem = item
                executePendingBindings()
            }
        }
    }
}


class HomeListDiffCallback : DiffUtil.ItemCallback<ListModel>() {

    override fun areItemsTheSame(oldItem: ListModel, newItem: ListModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ListModel, newItem: ListModel): Boolean {
        return oldItem == newItem
    }
}