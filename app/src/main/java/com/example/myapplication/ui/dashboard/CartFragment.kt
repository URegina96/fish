package com.example.myapplication.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.cart.CartViewModel

//  фрагмент для отображения содержимого корзины
class CartFragment : Fragment() {
    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_basket, container, false)
        cartViewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)

        val recyclerView: RecyclerView = view.findViewById(R.id.cart_recyclerview)
        val totalTextView: TextView = view.findViewById(R.id.total_text)

        try {
            cartViewModel.cartItems.observe(viewLifecycleOwner) { cartItems ->
                recyclerView.adapter = CartAdapter(cartItems, ::onRemoveFromCart)
                totalTextView.text = "Итого: ${cartViewModel.getTotalPrice()} руб."
            }
        } catch (e: Exception) {
            Log.e("CartFragment", "Error loading cart items", e)
        }

        return view
    }

    private fun onRemoveFromCart(cartItem: CartItem) {
        cartViewModel.removeFromCart(cartItem.product)
    }
}
