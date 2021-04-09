package com.example.pizzamart

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction

class CheckoutFragment1 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkout1, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val t:Long = 5000
        val fm = fragmentManager
        val f: Fragment = CheckoutFragment2()
        Handler().postDelayed({
            fm!!.beginTransaction().apply {
                replace(R.id.fl_wrapper, f)
                commit()
            }
        },t)
    }
}