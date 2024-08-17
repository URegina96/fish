package com.example.myapplication.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

//  новый RecyclerView.Adapter для управления отображением товаров в корзине.
class CartAdapter(
    private val cartItems: List<CartItem>,
    private val onRemove: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.product_image)
        val quantityTextView: TextView = itemView.findViewById(R.id.quantity_text)
        val deleteButton: Button = itemView.findViewById(R.id.delete_button)

        fun bind(cartItem: CartItem) {
            imageView.setImageResource(cartItem.product.imageResId)
            quantityTextView.text = cartItem.quantity.toString()
            deleteButton.setOnClickListener { onRemove(cartItem) }
        }
    }

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return ViewHolder(view)
    }

override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cartItems[position])
    }

override fun getItemCount(): Int = cartItems.size
}
