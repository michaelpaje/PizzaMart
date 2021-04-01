package com.example.shoppingcart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CartAdapter(private var title: List<String>, private var price:List<String>, private var image:List<String>): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itTitle: TextView = itemView.findViewById(R.id.foodName)
        val itPrice: TextView = itemView.findViewById(R.id.foodPrice)
        val itImage: ImageView = itemView.findViewById(R.id.foodImage)

//        init {
//            itemView.setOnClickListener {
//                val position: Int = adapterPosition
//            }
//        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.food, parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return title.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itTitle.text = title[position]
        holder.itPrice.text = price[position]
        Picasso.get()
            .load(image[position])
            .into(holder.itImage)
    }
}