package com.example.weatherappassessment.weather.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weatherappassessment.databinding.ItemCitySearchBinding
import com.example.weatherappassessment.weather.data.entity.Location

class LocationAdapter() : ListAdapter<Location, LocationAdapter.LocationViewHolder>(ItemCallback) {

    object ItemCallback: DiffUtil.ItemCallback<Location>(){
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = ItemCitySearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class LocationViewHolder(val binding: ItemCitySearchBinding): ViewHolder(binding.root) {

        fun bind(item: Location) {
            binding.apply {

            }
        }
    }
}