package com.ps215.capstoneITLS.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ps215.capstoneITLS.database.Traffic
import com.ps215.capstoneITLS.databinding.ItemCardBinding

class ListTrafficAdapter :
    ListAdapter<Traffic, ListTrafficAdapter.ListViewHolder>(DiffCallback()) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ListViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Traffic) {
            binding.trafficNameTv.text = data.name
            binding.roadNameTv.text = data.road
            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(data)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Traffic>() {
        override fun areItemsTheSame(oldItem: Traffic, newItem: Traffic): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Traffic, newItem: Traffic): Boolean {
            return oldItem.id == newItem.id
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Traffic)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}

