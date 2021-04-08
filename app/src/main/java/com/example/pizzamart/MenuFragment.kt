package com.example.pizzamart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_menu.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class MenuFragment : Fragment() {
    private var listItems = mutableListOf<Food>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Clear data
        listItems.clear()
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(activity)
        val url = "https://guarded-citadel-93720.herokuapp.com/pizza"
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    try {
                        val json_obj = JSONArray(response.toString())
                        for(i in 0 until json_obj.length()) {
                                val items: JSONObject = json_obj.getJSONObject(i)
                                val item_name =  items.getString("name")
                                val item_price = items.getString("price")
                                val item_image = items.getString("image")
                                listItems.add(Food(item_name,item_price,item_image))
                                rvID.adapter?.notifyDataSetChanged()
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
        rvID.layoutManager = GridLayoutManager(activity, 2)
        rvID.adapter = FoodAdapter(listItems)
    }
}