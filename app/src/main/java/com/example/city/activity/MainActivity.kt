package com.example.city.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.city.Adapter.BrandAdapter
import com.example.city.Adapter.RecommendationAdapter
import com.example.city.Adapter.SliderAdapter
import com.example.city.Model.SliderModel
import com.example.city.ViewModel.MainViewModel
import com.example.city.databinding.ActivityMainBinding




class MainActivity : AppCompatActivity() {
    private val viewModel = MainViewModel()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
        iniBrand()
        iniRecommendation()
    }

    ///////////...........banner............/////////
    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.banners.observe(this, Observer { items ->
            banners(items)
            binding.progressBarBanner.visibility = View.GONE

        })
        viewModel.loadBanners()
    }
    private fun banners(images : List<SliderModel>){
        binding.viewPagerSlider.adapter = SliderAdapter(images,binding.viewPagerSlider)
        binding.viewPagerSlider.clipToPadding = false
        binding.viewPagerSlider.clipChildren = false
        binding.viewPagerSlider.offscreenPageLimit = 3
        binding.viewPagerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPagerSlider.setPageTransformer(compositePageTransformer)
        if (images.size>1){
            binding.dotsIndicator.visibility = View.VISIBLE
            binding.dotsIndicator.attachTo(binding.viewPagerSlider)
        }
    }

    /////........Brand.........////////
    private fun iniBrand(){
        binding.progressBarBrand.visibility = View.VISIBLE
        viewModel.brands.observe(this, Observer {
            binding.viewBrandRecycler.layoutManager = LinearLayoutManager(this@MainActivity,
                LinearLayoutManager.HORIZONTAL,false)
            binding.viewBrandRecycler.adapter = BrandAdapter(it)
            binding.progressBarBrand.visibility = View.GONE
        })
        viewModel.loadBrand()
    }

    ////// Recommendation
    private fun iniRecommendation(){
        binding.progressBarRecommendation.visibility = View.VISIBLE
        viewModel.recommendation.observe(this, Observer {
            binding.viewRecommendationRecycler.layoutManager = GridLayoutManager(this@MainActivity,2)
            binding.viewRecommendationRecycler.adapter = RecommendationAdapter(it)
            binding.progressBarRecommendation.visibility = View.GONE
        })
        viewModel.loadRecommendation()

    }
}