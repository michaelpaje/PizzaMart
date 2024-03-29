package com.example.pizzamart

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
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
        var check = false
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
                val sh: SharedPreferences = holder.itemView.context.getSharedPreferences("Cart", 0)
                // GET SIZE
                val gSize: String? = sh.getString("pSize", "0")
                // CHECK IF THE ITEM ALREADY EXIST
                var getPos = 0
                for(i in 0 until gSize!!.toInt())
                {
                    val gTitle: String? = sh.getString("pTitle$i", "")
                    // CHECK IF TITLE ALREADY EXIST
                    if(gTitle == holder.itTitle.text.toString())
                    {
                        check=true
                        getPos = i
                        break
                    }
                }
                // ADD ITEM TO CART
                holder.itemView.context.getSharedPreferences("Cart", Context.MODE_PRIVATE).edit().apply(){
                    if(check) {
                        val gQty: String? = sh.getString("pQty$getPos", "1")
                        val totalQty = gQty?.toInt()?.plus(etQty.text.toString().toInt())
                        val totalPrice = (holder.itPrice.text as String).toDouble().times(totalQty!!)
                        putString("pTitle$getPos", holder.itTitle.text.toString())
                        // price * quantity
                        putString("pPrice$getPos", String.format("%.2f", totalPrice))
                        putString("pQty$getPos", totalQty.toString())
                        putString("pSize", gSize.toString())
                        check=false
                    }
                    else {
                        val totalPrice = (holder.itPrice.text as String).toDouble().times(etQty.text.toString().toInt())
                        putString("pTitle$gSize", holder.itTitle.text.toString())
                        putString("pPrice$gSize", String.format("%.2f", totalPrice))
                        putString("pQty$gSize", etQty.text.toString())
                        var temp = gSize.toInt()
                        temp++
                        putString("pSize", temp.toString())
                    }
                }.apply()
            })
            dialog.setNegativeButton("No", DialogInterface.OnClickListener { _, _ ->
            })
            dialog.show()
        }
    }
}


