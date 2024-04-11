package com.example.city.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.city.Model.RecommendationModel
import com.example.city.activity.DetailActivity
import com.example.city.databinding.ViewholderRecommenderBinding


class RecommendationAdapter(val items : MutableList<RecommendationModel>) :RecyclerView.Adapter<RecommendationAdapter.viewHolder>() {
    private var context :Context? = null
    class viewHolder(val binding: ViewholderRecommenderBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): RecommendationAdapter.viewHolder {
       context = parent.context
        val binding = ViewholderRecommenderBinding.inflate(LayoutInflater.from(context),parent,false)
        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendationAdapter.viewHolder, position: Int) {
        holder.binding.titleTxt.text = items[position].title
        holder.binding.priceTxt.text="$"+items[position].price.toString()
        holder.binding.ratingTxt.text = items[position].rating.toString()

        val requestOptions = RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .apply (requestOptions)
            .into(holder.binding.pic)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("object" , items[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}