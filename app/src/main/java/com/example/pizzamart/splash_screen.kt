package com.example.pizzamart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class splash_screen: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        this.supportActionBar?.hide()
    }
}