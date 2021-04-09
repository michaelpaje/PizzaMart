package com.example.pizzamart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ContactFragment : Fragment(R.layout.fragment_contact) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contactname = activity?.findViewById<EditText>(R.id.input_name)?.text.toString()
        val contactemail = activity?.findViewById<EditText>(R.id.input_email)?.text.toString()
        val contactmessage = activity?.findViewById<EditText>(R.id.input_message)?.text.toString()
        val contactSend = activity?.findViewById<Button>(R.id.contact_btn)
        contactSend?.setOnClickListener {
            if(contactname == "" || contactemail == "" || contactmessage == ""){
                Toast.makeText(activity, "Please enter all fields!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(activity, "Message sent successfully!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}