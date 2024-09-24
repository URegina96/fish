package com.example.myapplication.ui.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.cart.BasketViewModel

// Фрагмент для отображения содержимого корзины
class BasketFragment : Fragment() {
    private lateinit var basketViewModel: BasketViewModel // ViewModel для корзины

    // Создание представления
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_basket, container, false)
        // Инициализация BasketViewModel
        basketViewModel = ViewModelProvider(requireActivity()).get(BasketViewModel::class.java)

        val recyclerView: RecyclerView = view.findViewById(R.id.basket_recyclerview)
        val totalTextView: TextView = view.findViewById(R.id.total_text)
        val checkoutButton: Button = view.findViewById(R.id.checkout_button)

        basketViewModel.basketItems.observe(viewLifecycleOwner) { basketItems ->
            recyclerView.adapter = BasketAdapter(basketItems, ::onRemoveFromBasket, basketViewModel)
            totalTextView.text = "Итого: ${basketViewModel.getTotalPrice()} руб."
        }

        // Обработчик нажатия на кнопку
        checkoutButton.setOnClickListener {
            // Проверка на наличие товаров в корзине
            if (basketViewModel.basketItems.value.isNullOrEmpty()) {
                // Если корзина пуста, показываем сообщение
                Toast.makeText(context, "Ваша корзина пуста. Пожалуйста, добавьте товары.", Toast.LENGTH_SHORT).show()
            } else {
                // Если корзина не пуста, переходим к следующему фрагменту
                findNavController().navigate(R.id.action_navigation_basket_to_fragment_order_address)
            }
        }

        return view // Возвращаем созданное представление
    }

    // Метод для удаления товара из корзины
    private fun onRemoveFromBasket(basketItem: BasketItem) {
        basketViewModel.removeFromBasket(basketItem.product) // Удаление товара
    }
}
