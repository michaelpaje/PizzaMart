package com.example.pizzamart

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(R.layout.fragment_home) {
    //lateinit var db:DBHelper
    lateinit var ACT: LoginActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item1 = activity?.findViewById<ImageView>(R.id.item1)
        val item2 = activity?.findViewById<ImageView>(R.id.item2)
        val item3 = activity?.findViewById<ImageView>(R.id.item3)
        val item4 = activity?.findViewById<ImageView>(R.id.item4)
        val item5 = activity?.findViewById<ImageView>(R.id.item5)
        val item6 = activity?.findViewById<ImageView>(R.id.item6)
        val welcome = activity?.findViewById<TextView>(R.id.welcome)
        //TODO username
        //db = DBHelper(this)
        val sh: SharedPreferences = requireActivity().getSharedPreferences("User", 0)
        val username = sh.getString("username","0")
        welcome?.text = "Welcome $username!"

        item1?.setOnClickListener{
            setFragment()
            Log.d("message","item 1 clicked!")
        }
        item2?.setOnClickListener{
            setFragment()
            Log.d("message","item 2 clicked!")
        }
        item3?.setOnClickListener{
            setFragment()
            Log.d("message","item 3 clicked!")
        }
        item4?.setOnClickListener{
            setFragment()
            Log.d("message","item 4 clicked!")
        }
        item5?.setOnClickListener{
            setFragment()
            Log.d("message","item 5 clicked!")
        }
        item6?.setOnClickListener {
            setFragment()
            Log.d("message", "item 6 clicked!")
        }
    }
    private fun setFragment() {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fl_wrapper,MenuFragment())
            commit()
        }
    }
}