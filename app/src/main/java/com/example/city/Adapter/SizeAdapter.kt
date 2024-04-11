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
import com.example.city.databinding.ViewholdersizeBinding


class SizeAdapter(val items: MutableList<String>) : RecyclerView.Adapter<SizeAdapter.viewHolder>() {
    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context
    class viewHolder(val binding : ViewholdersizeBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeAdapter.viewHolder {
      context = parent.context
        val binding = ViewholdersizeBinding.inflate(LayoutInflater.from(context),parent,false)
        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: SizeAdapter.viewHolder, position: Int) {
     holder.binding.sizeTxt.text = items[position]


        holder.binding.root.setOnClickListener{
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
  
        if (selectedPosition == position){
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.grey_by_selected)
            holder.binding.sizeTxt.setTextColor(context.resources.getColor(R.color.purpal))
        }
        else {
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.grey_by)
            holder.binding.sizeTxt.setTextColor(context.resources.getColor(R.color.black))


        }
    }

    override fun getItemCount(): Int = items.size
}