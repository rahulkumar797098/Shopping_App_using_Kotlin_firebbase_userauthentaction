package com.example.city.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.city.Model.SliderModel
import com.example.city.R



class SliderAdapter(private var sliderItem:List<SliderModel>, private val viewPager2: ViewPager2):RecyclerView.Adapter<SliderAdapter.sliderViewHolder>() {
    private lateinit var context: Context
    private val runnable = Runnable{
        sliderItem = sliderItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): sliderViewHolder {
       context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slider_item_container,parent,false)
        return sliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: sliderViewHolder, position: Int) {
        holder.setImage(sliderItem[position] , context)
        if (position == sliderItem.lastIndex - 1){
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int = sliderItem.size

    class sliderViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val imageView:ImageView = itemView.findViewById(R.id.imageSlider)

        fun setImage(sliderItems : SliderModel, context: Context){
            val requestOptions = RequestOptions().transform(CenterCrop())
            Glide.with(context)
                .load(sliderItems.url)
                .apply (requestOptions)
                .into(imageView)
        }
    }
}