package com.example.pizzamart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast

class ContactFragment : Fragment(R.layout.fragment_contact) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val llC = activity?.findViewById<LinearLayout>(R.id.llContact)
        val contactName = llC?.findViewById<EditText>(R.id.input_name)
        val contactEmail = llC?.findViewById<EditText>(R.id.input_email)
        val contactMessage = llC?.findViewById<EditText>(R.id.input_message)
        val contactSend = activity?.findViewById<Button>(R.id.contact_btn)
        contactSend?.setOnClickListener {
            val gCN = contactName?.text.toString()
            val gCE = contactEmail?.text.toString()
            val gCM = contactMessage?.text.toString()
            if(gCN == "" || gCE == "" || gCM == ""){
                Toast.makeText(activity, "Please enter all fields!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(activity, "Message sent successfully!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}