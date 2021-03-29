package com.example.shoppingcart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_menu.*


class MenuFragment : Fragment(R.layout.fragment_menu) {
    private var imageList = mutableListOf<Int>()
    private var titleList = mutableListOf<String>()
    private var priceList = mutableListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addToList("Example", "500", R.drawable.ic_baseline_favorite_24)
        rvID.layoutManager = LinearLayoutManager(activity)
        rvID.adapter = FoodAdapter(titleList, priceList, imageList)
    }

    private fun addToList(title: String, price: String, image: Int) {
        titleList.add(title)
        priceList.add(price)
        imageList.add(image)
    }

}