package com.example.pizzamart

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

class CheckoutFragment2 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkout2, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val t:Long = 5000
        val fm = fragmentManager
        val f: Fragment = HomeFragment()
        Handler().postDelayed({
            val dialog = AlertDialog.Builder(activity)
            dialog.setIcon(R.drawable.pizzamart_logo)
            dialog.setTitle("Did you receive your order?")
            dialog.setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
                Toast.makeText(view.context, "Thank you, Order Again!", Toast.LENGTH_SHORT).show()
            })
            dialog.setNegativeButton("No", DialogInterface.OnClickListener { _, _ ->
            })
            dialog.show()
            fm!!.beginTransaction().apply {
                replace(R.id.fl_wrapper, f)
                commit()
            }
        },t)
    }
}