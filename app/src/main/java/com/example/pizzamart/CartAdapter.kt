package com.example.pizzamart

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(private var CartItem: MutableList<Cart>): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itTitle: TextView = itemView.findViewById(R.id.cFoodName)
        val itPrice: TextView = itemView.findViewById(R.id.cFoodPrice)
        val itQty: TextView = itemView.findViewById(R.id.cFoodQty)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cartfood, parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return CartItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cItems: Cart = CartItem[position]
        holder.itTitle.text = cItems.cTitle
        holder.itPrice.text = cItems.cPrice
        holder.itQty.text = cItems.cQty
        holder.itemView.setOnClickListener {
            val dialog = AlertDialog.Builder(holder.itemView.context)
            dialog.setTitle("Remove Item")
            dialog.setMessage("Do you want to remove ${holder.itTitle.text} to cart?")
            dialog.setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
                Toast.makeText(holder.itemView.context, "${holder.itTitle.text} Successfully removed to cart!", Toast.LENGTH_SHORT).show()
                // REMOVE IN RECYCLERVIEW
                CartItem.removeAt(holder.adapterPosition)
                notifyItemRemoved(holder.adapterPosition)
                notifyItemRangeChanged(holder.adapterPosition,itemCount)
            })
            dialog.setNegativeButton("No", DialogInterface.OnClickListener { _, _ ->
            })
            dialog.show()
        }
    }


}


