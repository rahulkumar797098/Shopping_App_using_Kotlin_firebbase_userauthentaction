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
import com.example.city.Model.BrandModel



class BrandAdapter(val items: MutableList<BrandModel>) : RecyclerView.Adapter<BrandAdapter.viewHolder>() {
    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context
    class viewHolder(val binding : ViewholderBrandBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandAdapter.viewHolder {
      context = parent.context
        val binding = ViewholderBrandBinding.inflate(LayoutInflater.from(context),parent,false)
        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrandAdapter.viewHolder, position: Int) {
       val item = items[position]
        holder.binding.title.text = item.title

        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.pic)

        holder.binding.root.setOnClickListener{
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        holder.binding.title.setTextColor(context.resources.getColor(R.color.white))
        if (selectedPosition == position){
            holder.binding.pic.setBackgroundResource(0)
            holder.binding.viewholderBrandRoot.setBackgroundResource(R.drawable.brand_back)
            ImageViewCompat.setImageTintList(holder.binding.pic , ColorStateList.valueOf(context.getColor(
                R.color.white)))

            holder.binding.title.visibility = View.VISIBLE
        }
        else {
            holder.binding.pic.setBackgroundResource(R.drawable.grey_by)
            holder.binding.viewholderBrandRoot.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(holder.binding.pic , ColorStateList.valueOf(context.getColor(
                R.color.black)))
            holder.binding.title.visibility = View.GONE

        }
    }

    override fun getItemCount(): Int = items.size
}