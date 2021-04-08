package com.example.shoppingcart

import android.app.AlertDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlin.math.log


class CartFragment : Fragment() {
    private var listItems = mutableListOf<Cart>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // delete list
        listItems.clear()
        val sh: SharedPreferences = requireActivity().getSharedPreferences("Cart", 0)
        val check:String? = sh.getString("pTitle0", null)
        var totalPrice = 0.0
        if (check != null) {
            val gSize: String? = sh.getString("pSize", "")
            for(i in 0 until gSize!!.toInt())
            {
                val gTitle: String? = sh.getString("pTitle$i", "")
                val gPrice: String? = sh.getString("pPrice$i", "")
                val gQty: String? = sh.getString("pQty$i", "")
                listItems.add(Cart(gTitle.toString(), gPrice.toString(), gQty.toString()))
                rvCartID.adapter?.notifyDataSetChanged()
                totalPrice += gPrice!!.toDouble()
            }
        }
        rvCartID.layoutManager = LinearLayoutManager(activity)
        rvCartID.adapter = CartAdapter(listItems)
        // checkout button
        checkoutBtn.setOnClickListener{
            // DITO MAGEENTER NG MGA ADDRESS CHUCHU
            val inflater: LayoutInflater = LayoutInflater.from(activity)
            val v:View = inflater.inflate(R.layout.checkout_dialog,null)
            val dialog = AlertDialog.Builder(activity)
            dialog.setView(v)
            dialog.show()
        }
    }
}

