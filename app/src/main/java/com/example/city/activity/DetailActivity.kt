package com.example.city.activity

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.city.Adapter.ColorAdapter
import com.example.city.Adapter.SizeAdapter
import com.example.city.Adapter.SliderAdapter
import com.example.city.Model.RecommendationModel
import com.example.city.Model.SliderModel
import com.example.city.R
import com.example.city.databinding.ActivityDetailBinding
import com.example.project1762.Helper.ManagmentCart

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item : RecommendationModel
    private var numberOrder  = 1
    private lateinit var managmentCart: ManagmentCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ///////////////////////////
        managmentCart = ManagmentCart(this)
        getBundle()
        banners()
        initLists()

    }

    private fun initLists() {
        val sizeList = ArrayList<String>()
        for (size in item.size){
            sizeList.add(size.toString())
        }
        binding.sizeList.adapter = SizeAdapter(sizeList)
        binding.sizeList.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,false)

        val colorList =ArrayList<String>()
        for (imageUrl in item.picUrl){
            colorList.add(imageUrl)
        }
        binding.colorList.adapter = ColorAdapter(colorList)
        binding.colorList.layoutManager = LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL,false)
    }

    private fun banners() {
        val sliderItems = ArrayList<SliderModel>()
        for (imageUrl in item.picUrl){
            sliderItems.add(SliderModel(imageUrl))
        }
        binding.slider.adapter = SliderAdapter(sliderItems ,binding.slider)
        binding.slider.clipToPadding = true
        binding.slider.clipChildren = true
        binding.slider.offscreenPageLimit = 1

        if (sliderItems.size > 1){
            binding.dotsIndicator.visibility = View.VISIBLE
            binding.dotsIndicator.attachTo(binding.slider)
        }
    }

    private fun getBundle() {
        item  = intent.getParcelableExtra("object")!!
        binding.titleTxt.text = item.title
        binding.descriptionTxt.text = item.description
        binding.priceTxt.text = "$" + item.price
        binding.ratingTxt.text = "${item.rating} Rating"
        binding.addToCartBtnDE.setOnClickListener {
            item.numberInCart = numberOrder
            managmentCart.insertFood(item)
            showCustomDialog()

            ////
        }
        binding.backBtn.setOnClickListener { finish()

        }
        binding.cartBtnDE.setOnClickListener {
//           startActivity(Intent(this,CartActivity::class.java))
            showCustomDialogCart()
        }

    }

    //////////////////////  Dialog Box Show When Click on Buy Button
    private fun showCustomDialog() {
        // Create a dialog object
        val dialog = Dialog(this)

        // Set the custom dialog layout
        dialog.setContentView(R.layout.activity_order_success_custom_dialog_box)

        // Display the dialog
        dialog.show()


        }

    ///////////////////

    private fun showCustomDialogCart() {
        // Create a dialog object
        val dialog = Dialog(this)

        // Set the custom dialog layout
        dialog.setContentView(R.layout.activity_cart)
        // Display the dialog
        dialog.show()
    }

    }