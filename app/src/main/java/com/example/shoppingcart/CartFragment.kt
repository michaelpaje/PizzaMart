package com.example.shoppingcart

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_cart.*


class CartFragment : Fragment() {
    private var listItems = mutableListOf<Cart>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sh: SharedPreferences = activity!!.getSharedPreferences("Cart", 0)
        val check:String? = sh.getString("pTitle", null)
        if (check != null) {
            val gTitle: String? = sh.getString("pTitle", "")
            val gPrice: String? = sh.getString("pPrice", "")
            val gQty: String? = sh.getString("pQty", "")
            listItems.add(Cart(gTitle.toString(), gPrice.toString(), gQty.toString()))
            activity!!.getSharedPreferences("Cart", 0).edit().clear().apply()
            rvCartID.adapter?.notifyDataSetChanged()
        }

        rvCartID.layoutManager = LinearLayoutManager(activity)
        rvCartID.adapter = CartAdapter(listItems)
    }
}

