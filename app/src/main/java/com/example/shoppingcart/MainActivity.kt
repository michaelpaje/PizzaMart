package com.example.shoppingcart

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private val homeFragment = HomeFragment()
    private val contactFragment = ContactFragment()
    private val aboutFragment = AboutFragment()
    private val menuFragment = MenuFragment()
    private val cartFragment = CartFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        applicationContext.getSharedPreferences("Cart", 0).edit().clear().apply()

        // DEFAULT FRAGMENT
        setFragment(homeFragment)
        // BURGER MENU
        val burgerImg:ImageView = findViewById(R.id.burgerMenu)
        burgerImg.setOnClickListener{
            showPopupMenu(it)
        }
        // GO TO CART FRAGMENT
        val cartImg:ImageView = findViewById(R.id.cartImage)
        cartImg.setOnClickListener {
            setFragment(cartFragment)
        }
        // BOTTOM NAVIGATION BAR
//        val bottomNav = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation)
//        bottomNav.setOnNavigationItemSelectedListener {
//            when(it.itemId) {
//                R.id.nav_home -> {
//                    setFragment(homeFragment)
//                }
//                R.id.nav_menu -> {
//                    setFragment(menuFragment)
//                }
//            }
//            true
//        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }
    }

    private fun showPopupMenu(view: View) {
        // inflate menu and attach it to a view onClick of which you want to display menu
        val popup = PopupMenu(this, view)
        val inflater = popup.menuInflater
        //inflate menu items to popup menu
        inflater.inflate(R.menu.burger_menu, popup.menu)
        popup.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.home_item -> setFragment(homeFragment)
                R.id.contact_item -> setFragment(contactFragment)
                R.id.about_item -> setFragment(aboutFragment)
                R.id.menu_item -> setFragment(menuFragment)
                R.id.cart_item -> setFragment(cartFragment)
                R.id.logout_item -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
            true
        }
        //Show Popup.
        popup.show()
    }
}