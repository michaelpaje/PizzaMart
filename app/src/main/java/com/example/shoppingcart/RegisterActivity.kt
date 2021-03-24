package com.example.shoppingcart

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity: AppCompatActivity() {
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var rePassword: EditText
    lateinit var signUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        rePassword = findViewById(R.id.rePassword)
        signUp = findViewById(R.id.btnSignUp)

        signUp.setOnClickListener {

        }

    }
}