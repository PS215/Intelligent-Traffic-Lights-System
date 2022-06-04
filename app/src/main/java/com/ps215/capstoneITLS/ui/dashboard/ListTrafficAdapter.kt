package com.ps215.capstoneITLS.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ps215.capstoneITLS.database.model.TrafficList
import com.ps215.capstoneITLS.databinding.ItemCardBinding

class ListTrafficAdapter :
    ListAdapter<TrafficList, ListTrafficAdapter.ListViewHolder>(DiffCallback()) {
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
        fun bind(data: TrafficList) {
            binding.trafficNameTv.text = data.name
            binding.roadNameTv.text = data.address
            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(data)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<TrafficList>() {
        override fun areItemsTheSame(oldItem: TrafficList, newItem: TrafficList): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: TrafficList, newItem: TrafficList): Boolean {
            return oldItem._id == newItem._id
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: TrafficList)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}

