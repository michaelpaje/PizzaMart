package com.example.shoppingcart

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class FoodAdapter(private var FoodItem: MutableList<Food>): RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itTitle:TextView = itemView.findViewById(R.id.foodName)
        val itPrice:TextView = itemView.findViewById(R.id.foodPrice)
        val itImage: ImageView = itemView.findViewById(R.id.foodImage)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.food, parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return FoodItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fItems: Food = FoodItem[position]
        holder.itTitle.text = fItems.fTitle
        holder.itPrice.text = fItems.fPrice
        Picasso.get()
            .load(fItems.fImage)
            .into(holder.itImage)
        holder.itemView.setOnClickListener {
            val dialog = AlertDialog.Builder(holder.itemView.context)
            val inflater: LayoutInflater = LayoutInflater.from(holder.itemView.context)
            val v:View = inflater.inflate(R.layout.dialog_custom,null)
            val etQty: EditText = v.findViewById(R.id.FoodQty)
            dialog.setTitle("Add to Cart")
            dialog.setMessage("Do you want to add ${holder.itTitle.text} to cart?")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.setView(v)
            }
            dialog.setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
                Toast.makeText(holder.itemView.context, "${holder.itTitle.text} x${etQty.text} Successfully added to cart!", Toast.LENGTH_SHORT).show()
                // ADD ITEM TO CART
                holder.itemView.context.getSharedPreferences("Cart", Context.MODE_PRIVATE).edit().apply(){
                    putString("pTitle", holder.itTitle.text.toString())
                    putString("pPrice", holder.itPrice.text.toString())
                    putString("pQty", etQty.text.toString())
                }.apply()
            })
            dialog.setNegativeButton("No", DialogInterface.OnClickListener { _, _ ->
            })
            dialog.show()
        }
    }
}


