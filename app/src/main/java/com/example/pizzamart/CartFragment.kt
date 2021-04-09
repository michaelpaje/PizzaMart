package com.example.pizzamart

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
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
        // delete list
        listItems.clear()
        val sh: SharedPreferences = requireActivity().getSharedPreferences("Cart", 0)
        val check:String? = sh.getString("pTitle0", null)
        var totalPrice: Double? = sh.getString("pTotal","0.00")?.toDouble()
        //var totalPrice = 0.00
        if (check != null) {
            val gSize: String? = sh.getString("pSize", "")
            val inflater: LayoutInflater = LayoutInflater.from(activity)
            val v:View = inflater.inflate(R.layout.checkout_dialog,null)
            val totalP:TextView = v.findViewById(R.id.price)
            for(i in 0 until gSize!!.toInt())
            {
                val gTitle: String? = sh.getString("pTitle$i", "")
                val gPrice: String? = sh.getString("pPrice$i", "")
                val gQty: String? = sh.getString("pQty$i", "")
                listItems.add(Cart(gTitle.toString(), gPrice.toString(), gQty.toString()))
                rvCartID.adapter?.notifyDataSetChanged()
                totalPrice = totalPrice?.plus(gPrice!!.toDouble())
                totalP.text = totalPrice.toString()
            }
        }
        rvCartID.layoutManager = LinearLayoutManager(activity)
        rvCartID.adapter = CartAdapter(listItems)
        // checkout button
        checkoutBtn.setOnClickListener{
            val inflater: LayoutInflater = LayoutInflater.from(activity)
            val v:View = inflater.inflate(R.layout.checkout_dialog,null)
            val totalP:TextView = v.findViewById(R.id.price)
            val dialog = AlertDialog.Builder(activity)
            val alert = dialog.create()
            totalP.text = totalPrice.toString()
            alert.setView(v)
            alert.setTitle("Checkout")
            alert.show()

            val fm = fragmentManager
            val f: Fragment = CheckoutFragment()
            val btnP: Button = v.findViewById(R.id.btnProceed)
            btnP.setOnClickListener {
                alert.cancel()
                fm!!.beginTransaction().apply {
                    replace(R.id.fl_wrapper, f)
                    commit()
                }
            }
        }
    }
}



