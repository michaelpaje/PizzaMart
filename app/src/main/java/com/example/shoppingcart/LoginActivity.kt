package com.example.shoppingcart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity: AppCompatActivity() {
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var signIn: Button
    lateinit var signUp: Button
    lateinit var db:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
        this.supportActionBar?.hide()

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        signIn = findViewById(R.id.btnSignIn)
        signUp = findViewById(R.id.btnSignUp)
        db = DBHelper(this)

        signIn.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()
            if(user =="" || pass =="") {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            }
            else {
                val checkUserPass = this.db.checkUserPass(user,pass)
                if(checkUserPass) {
                    Toast.makeText(this, "Login successfully!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }
                else {
                    val checkUser = this.db.checkUser(user)
                    if (!checkUser) {
                        Toast.makeText(this, "Username does not exist!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Password Incorrect!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        signUp.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}