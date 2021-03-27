package com.example.shoppingcart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity: AppCompatActivity() {
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var rePassword: EditText
    lateinit var signUp: Button
    lateinit var db:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)
        this.supportActionBar?.hide()

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        rePassword = findViewById(R.id.rePassword)
        signUp = findViewById(R.id.btnSignUp)
        db = DBHelper(this)

        signUp.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()
            val rePass = rePassword.text.toString()

            if(user == ""  || pass == "") {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            } else {
                if(pass == rePass) {
                    val checkUser = this.db.checkUser(user)
                    if(!checkUser) {
                        val insert = this.db.insertUserTB(user, pass)
                        if (insert) {
                            Toast.makeText(this, "Registered Successfully!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, LoginActivity::class.java))
                        } else {
                            Toast.makeText(this, "Registration Failed!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else {
                        Toast.makeText(this, "Username already exist!", Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    Toast.makeText(this, "Password does not match!", Toast.LENGTH_SHORT).show()

                }
            }
        }

    }
}