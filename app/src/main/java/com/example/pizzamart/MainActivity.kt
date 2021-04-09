package com.example.pizzamart


import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.nav_header.*
import org.w3c.dom.Text


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val homeFragment = HomeFragment()
    private val contactFragment = ContactFragment()
    private val aboutFragment = AboutFragment()
    private val menuFragment = MenuFragment()
    private val cartFragment = CartFragment()
    private lateinit var drawer:DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        applicationContext.getSharedPreferences("Cart", 0).edit().clear().apply()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawer = findViewById(R.id.drawer_layout)
        val navView = findViewById<NavigationView>(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)
        
        //get username of user and display on nav header
        val sh: SharedPreferences = this.getSharedPreferences("User", 0)
        val username = sh.getString("username", "")
        val hv = navView.getHeaderView(0)
        val nnav = hv.findViewById<TextView>(R.id.navName)
        nnav.text = "$username"

        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        toggle.syncState()

        // DEFAULT FRAGMENT
        setFragment(homeFragment)


    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
         when(item.itemId){
             R.id.home_item -> setFragment(homeFragment)
             R.id.contact_item -> setFragment(contactFragment)
             R.id.about_item -> setFragment(aboutFragment)
             R.id.menu_item -> setFragment(menuFragment)
             R.id.cart_item -> setFragment(cartFragment)
             R.id.logout_item -> finish()
         }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.cart_img -> setFragment(cartFragment)
            R.id.menu_img -> setFragment(menuFragment)
        }
        return true
    }
}