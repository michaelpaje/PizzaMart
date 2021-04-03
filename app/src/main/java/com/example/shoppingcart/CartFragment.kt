package com.example.shoppingcart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : Fragment() {
    private var titleList = mutableListOf<String>()
    private var priceList = mutableListOf<String>()
    private var imageList = mutableListOf<String>()
    private var qtyList = mutableListOf<String>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCartID.layoutManager = LinearLayoutManager(activity)
        rvCartID.adapter = CartAdapter(titleList,priceList,imageList,qtyList)
    }
}