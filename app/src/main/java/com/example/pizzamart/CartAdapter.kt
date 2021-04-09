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
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
        val inflater: LayoutInflater = LayoutInflater.from(holder.itemView.context)
        val v:View = inflater.inflate(R.layout.dialog_custom,null)
        val etQty: EditText = v.findViewById(R.id.FoodQty)
        holder.itTitle.text = cItems.cTitle
        holder.itPrice.text = cItems.cPrice
        holder.itQty.text = cItems.cQty
        holder.itemView.setOnClickListener {
            val dialog = AlertDialog.Builder(holder.itemView.context)
            dialog.setCancelable(false)
            dialog.setTitle("Remove Item")
            dialog.setMessage("Do you want to remove ${holder.itTitle.text} to cart?")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.setView(v)
            }
            dialog.setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
                // IF ENTERED QTY IS LOWER THAN FOOD QUANTITY
                if (etQty.text.toString().toInt() <= (holder.itQty.text as String).toInt()) {
                    val sh: SharedPreferences = holder.itemView.context.getSharedPreferences("Cart", 0)
                    // GET SIZE
                    val gSize: String? = sh.getString("pSize", "0")
                    val total: String? = sh.getString("pTotal", "0.00")
                    var visit = false
                    var tempj = 0
                    // get name of pizza
                    for (i in 0 until gSize!!.toInt()) {
                        val gTitle: String? = sh.getString("pTitle$i", "")
                        if (holder.itTitle.text == gTitle) {
                            val cgQty: String? = sh.getString("pQty$i", "")
                            holder.itemView.context.getSharedPreferences("Cart", Context.MODE_PRIVATE).edit().apply()
                            {
                                if (etQty.text.toString() == cgQty.toString()) // IF SAME QUANTITY
                                {
                                    // REMOVE IN RECYCLERVIEW
                                    CartItem.removeAt(holder.adapterPosition)
                                    notifyItemRemoved(holder.adapterPosition)
                                    notifyItemRangeChanged(holder.adapterPosition, itemCount)
                                    Toast.makeText(holder.itemView.context, "${holder.itTitle.text} Successfully removed to cart!", Toast.LENGTH_SHORT).show()
                                    // remove in shared pref
                                    for (j in 0 until gSize.toInt()) {
                                        val tTitle: String? = sh.getString("pTitle$j", "")
                                        val tPrice: String? = sh.getString("pPrice$j", "")
                                        val tQty: String? = sh.getString("pQty$j", "")
                                        if (holder.itTitle.text == tTitle) {
                                            visit = true
                                        } else {
                                            if (visit) {
                                                tempj = j - 1
                                                putString("pTitle$tempj", tTitle)
                                                putString("pPrice$tempj", tPrice)
                                                putString("pQty$tempj", tQty)
                                            } else {
                                                putString("pTitle$j", tTitle)
                                                putString("pPrice$j", tPrice)
                                                putString("pQty$j", tQty)
                                            }
                                        }
                                    }
                                    visit = false
                                    // new size
                                    var temp = gSize.toInt()
                                    temp--
                                    putString("pSize", temp.toString())
                                    notifyItemChanged(holder.adapterPosition)
                                    val activity =
                                        v.context as AppCompatActivity
                                    val fragment: Fragment = CartFragment()
                                    activity.supportFragmentManager.beginTransaction().apply {
                                        replace(com.example.pizzamart.R.id.fl_wrapper, fragment)
                                        commit()
                                    }
                                } else { // IF QUANTITY IS NOT SAME
                                    Toast.makeText(holder.itemView.context, "Successfully removed x${etQty.text} ${holder.itTitle.text} to cart!", Toast.LENGTH_SHORT).show()
                                    val gQty: String? = sh.getString("pQty$i", "1")
                                    val gPrice: String? = sh.getString("pPrice$i", "")
                                    val totalQty = gQty?.toInt()?.minus(etQty.text.toString().toInt())
                                    //val ptotal = total?.toDouble()?.minus(gPrice?.toDouble())
                                    val totalPrice = gPrice?.toDouble()?.div(gQty?.toDouble()!!)?.times(totalQty!!)

                                    // overwrite price and quantity
                                    putString("pPrice$i", String.format("%.2f", totalPrice))
                                    putString("pQty$i", totalQty.toString())
                                    //putString("pTotal",String.format("%.2f", totalPrice))
                                    cItems.cPrice = String.format("%.2f", totalPrice)
                                    cItems.cQty = totalQty.toString()
                                    notifyItemChanged(holder.adapterPosition)
                                    val activity =
                                        v.context as AppCompatActivity
                                    val fragment: Fragment = CartFragment()
                                    activity.supportFragmentManager.beginTransaction().apply {
                                        replace(com.example.pizzamart.R.id.fl_wrapper, fragment)
                                        commit()
                                    }
                                }
                            }.apply()
                            break
                        }
                    }
                } else { // IF ENTERED QUANTITY IS ABOVE FOOD QUANTITY
                    Toast.makeText(holder.itemView.context, "Entered Quantity is too high!", Toast.LENGTH_SHORT).show()
                    notifyItemChanged(holder.adapterPosition)
                }
            })
            dialog.setNegativeButton("No", DialogInterface.OnClickListener { _, _ ->

                notifyItemChanged(holder.adapterPosition)
            })
            dialog.show()
        }
    }


}


