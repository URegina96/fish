package com.example.myapplication.ui.basket

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

// Фрагмент для отображения содержимого корзины
class CartFragment : Fragment() {
    private lateinit var cartViewModel: CartViewModel // ViewModel для корзины

    // Создание представления
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_basket, container, false)
        // Инициализация CartViewModel
        cartViewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)

        val recyclerView: RecyclerView = view.findViewById(R.id.cart_recyclerview) // Инициализация RecyclerView
        val totalTextView: TextView = view.findViewById(R.id.total_text) // Инициализация текстового поля для общей суммы

        try {
            // Наблюдение за изменениями в корзине
            cartViewModel.cartItems.observe(viewLifecycleOwner) { cartItems ->
                recyclerView.adapter = CartAdapter(cartItems, ::onRemoveFromCart, cartViewModel) // Установка адаптера
                totalTextView.text = "Итого: ${cartViewModel.getTotalPrice()} руб." // Обновление суммы
            }
        } catch (e: Exception) {
            Log.e("CartFragment", "Error loading cart items", e) // Логгирование ошибок
        }

        return view // Возвращаем созданное представление
    }

    // Метод для удаления товара из корзины
    private fun onRemoveFromCart(cartItem: CartItem) {
        cartViewModel.removeFromCart(cartItem.product) // Удаление товара
    }
}
