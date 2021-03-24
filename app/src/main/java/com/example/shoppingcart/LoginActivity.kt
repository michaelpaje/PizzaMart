package com.example.shoppingcart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity: AppCompatActivity() {
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var signIn: Button
    lateinit var signUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        signIn = findViewById(R.id.btnSignIn)
        signUp = findViewById(R.id.btnSignUp)

        signIn.setOnClickListener {

        }

        signUp.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }
}