package com.example.city.Adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.city.R
import com.example.city.databinding.ViewholderBrandBinding
import com.example.city.databinding.ViewholdercolorBinding


class ColorAdapter(val items: MutableList<String>) : RecyclerView.Adapter<ColorAdapter.viewHolder>() {
    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context

    class viewHolder(val binding :ViewholdercolorBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorAdapter.viewHolder {
        context = parent.context
        val binding = ViewholdercolorBinding.inflate(LayoutInflater.from(context),parent,false)
        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorAdapter.viewHolder, position: Int) {

        Glide.with(holder.itemView.context)
            .load(items[position])
            .into(holder.binding.pic)

        holder.binding.root.setOnClickListener{
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        if (selectedPosition == position){
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_by_selected)
        }
        else {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_by)
        }
    }

    override fun getItemCount(): Int = items.size
}