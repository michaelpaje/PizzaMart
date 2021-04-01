package com.example.shoppingcart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val homeFragment = HomeFragment()
        val menuFragment = MenuFragment()
        val cartFragment = CartFragment()

        val cartImg:ImageView = findViewById(R.id.cartImage)
        cartImg.setOnClickListener {
            setFragment(cartFragment)
        }

        setFragment(homeFragment)
        val bottomNav = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_home -> setFragment(homeFragment)
                R.id.nav_menu -> setFragment(menuFragment)
            }
            true
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }
    }


}