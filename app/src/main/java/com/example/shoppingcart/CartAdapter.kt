package com.example.shoppingcart

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CartAdapter(private var title: List<String>, private var price:List<String>, private var image:List<String>, private var fqty:List<String>): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itTitle: TextView = itemView.findViewById(R.id.cFoodName)
        val itPrice: TextView = itemView.findViewById(R.id.cFoodPrice)
        val itImage: ImageView = itemView.findViewById(R.id.cFoodImage)
        val itQty: TextView = itemView.findViewById(R.id.cFoodQty)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cartfood, parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return title.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itTitle.text = title[position]
        holder.itPrice.text = price[position]
        holder.itQty.text = fqty[position]
        Picasso.get()
            .load(image[position])
            .into(holder.itImage)
        holder.itemView.setOnClickListener {
            val dialog = AlertDialog.Builder(holder.itemView.context)
            val inflater: LayoutInflater = LayoutInflater.from(holder.itemView.context)
            val v:View = inflater.inflate(R.layout.dialog_custom,null)
            val etQty: EditText = v.findViewById(R.id.FoodQty)
            dialog.setTitle("Remove Item")
            dialog.setMessage("Do you want to remove ${holder.itTitle.text} to cart?")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.setView(v)
            }
            dialog.setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
                Toast.makeText(holder.itemView.context, "${holder.itTitle.text} x${etQty.text} Successfully removed to cart!", Toast.LENGTH_SHORT).show()
                // REMOVE IN RECYCLERVIEW
            })
            dialog.setNegativeButton("No", DialogInterface.OnClickListener { _, _ ->
            })
            dialog.show()
        }
    }
}