package com.example.shoppingcart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val menuFragment = MenuFragment()
        val favoriteFragment = FavoriteFragment()

        setFragment(homeFragment)

        val bottomNav = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_home -> setFragment(homeFragment)
                R.id.nav_menu -> setFragment(menuFragment)
                R.id.nav_fav -> setFragment(favoriteFragment)
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