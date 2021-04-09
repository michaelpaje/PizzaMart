package com.example.pizzamart

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class HomeFragment : Fragment(R.layout.fragment_home) {
    //lateinit var db:DBHelper
    lateinit var ACT: LoginActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item1 = activity?.findViewById<ImageView>(R.id.item1)
        val item2 = activity?.findViewById<ImageView>(R.id.item2)
        val item3 = activity?.findViewById<ImageView>(R.id.item3)
        val item4 = activity?.findViewById<ImageView>(R.id.item4)
        val item5 = activity?.findViewById<ImageView>(R.id.item5)
        val item6 = activity?.findViewById<ImageView>(R.id.item6)
        val item1txt = activity?.findViewById<TextView>(R.id.item1_txt)
        val item2txt = activity?.findViewById<TextView>(R.id.item2_txt)
        val item3txt = activity?.findViewById<TextView>(R.id.item3_txt)
        val item4txt = activity?.findViewById<TextView>(R.id.item4_txt)
        val item5txt = activity?.findViewById<TextView>(R.id.item5_txt)
        val item6txt = activity?.findViewById<TextView>(R.id.item6_txt)
        val welcome = activity?.findViewById<TextView>(R.id.welcome)
        val popular =activity?.findViewById<ImageView>(R.id.Popular_img)
        val sh: SharedPreferences = requireActivity().getSharedPreferences("User", 0)
        val username = sh.getString("username","0")
        welcome?.text = "Welcome $username!"

        val itemarr = arrayOf(item1,item2,item3,item4,item5,item6)
        val itemarrtxt = arrayOf(item1txt,item2txt,item3txt,item4txt,item5txt,item6txt)
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(activity)
        val url = "https://guarded-citadel-93720.herokuapp.com/pizza"
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                try {
                    val json_obj = JSONArray(response.toString())
                    for(i in 0 until json_obj.length()) {
                        val items: JSONObject = json_obj.getJSONObject(i)
                        val item_name = items.getString("name")
                        val item_image = items.getString("image")
                        //listItems.add(Food(item_image))
                        if(i<6){
                            //Picasso.get().load(item_name).into(itemarrtxt[i])
                            itemarrtxt[i]?.setText(item_name)
                            Picasso.get().load(item_image).into(itemarr[i])
                        }
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener {
                fun onErrorResponse(error: VolleyError) {
                    Log.e("Volley", error.toString());
                }
            })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)

        item1?.setOnClickListener{
            setFragment()
            Log.d("message","item 1 clicked!")
        }
        item2?.setOnClickListener{
            setFragment()
            Log.d("message","item 2 clicked!")
        }
        item3?.setOnClickListener{
            setFragment()
            Log.d("message","item 3 clicked!")
        }
        item4?.setOnClickListener{
            setFragment()
            Log.d("message","item 4 clicked!")
        }
        item5?.setOnClickListener{
            setFragment()
            Log.d("message","item 5 clicked!")
        }
        item6?.setOnClickListener {
            setFragment()
            Log.d("message", "item 6 clicked!")
        }
    }
    private fun setFragment() {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fl_wrapper,MenuFragment())
            commit()
        }
    }
}

