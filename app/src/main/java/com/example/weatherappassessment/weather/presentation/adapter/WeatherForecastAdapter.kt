package com.example.weatherappassessment.weather.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappassessment.core.util.formatDate
import com.example.weatherappassessment.core.util.hide
import com.example.weatherappassessment.core.util.show
import com.example.weatherappassessment.databinding.ItemDailyForecastBinding
import com.example.weatherappassessment.weather.data.entity.WeatherItem

class WeatherForecastAdapter() : ListAdapter<WeatherItem,WeatherForecastAdapter.ForecastViewHolder>(ItemCallback){

    object ItemCallback: DiffUtil.ItemCallback<WeatherItem>() {
        override fun areItemsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = ItemDailyForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ForecastViewHolder(val binding: ItemDailyForecastBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WeatherItem){
            binding.apply {
                if(adapterPosition == 0){
                    itemDivider.hide()
                } else{
                    itemDivider.show()
                }
                date.text = formatDate(item.dt!!)
            }
        }

        fun getWeatherConditionIcon(description: String){

        }
    }
}