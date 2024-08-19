package com.example.myapplication.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.Product.Product
import com.example.myapplication.ui.basket.CartItem

// ViewModel для управления состоянием корзины
class CartViewModel : ViewModel() {

    // MutableLiveData для отслеживания состояния корзины
    private val _cartItems = MutableLiveData<MutableList<CartItem>>().apply { value = mutableListOf() }
    val cartItems: LiveData<MutableList<CartItem>> = _cartItems // Доступ к корзине

    // Метод для добавления товара в корзину
    fun addToCart(product: Product) {
        val currentItems = _cartItems.value ?: mutableListOf()
        // Проверка, существует ли продукт в корзине
        val existingItem = currentItems.find { it.product.name == product.name }

        if (existingItem != null) {
            existingItem.increaseQuantity() // Увеличиваем количество, если продукт уже есть
        } else {
            currentItems.add(CartItem(product, 1)) // Добавляем новый товар
        }

        _cartItems.value = currentItems // Обновляем LiveData
    }

    // Метод для удаления товара из корзины
    fun removeFromCart(product: Product) {
        // Фильтрация списка товаров
        val currentItems = _cartItems.value?.filter { it.product.name != product.name }
        _cartItems.value = currentItems?.toMutableList() // Обновление состояния LiveData
    }

    // Метод для получения общей стоимости корзины
    fun getTotalPrice(): Double {
        return _cartItems.value?.sumOf { it.totalPrice } ?: 0.0 // Подсчет общей стоимости
    }

    // Метод для увеличения количества в CartItem
    fun increaseQuantity(cartItem: CartItem) {
        cartItem.increaseQuantity() // Увеличиваем количество
        _cartItems.value = _cartItems.value // Обновляем состояние LiveData
    }

    // Метод для уменьшения количества в CartItem
    fun decreaseQuantity(cartItem: CartItem) {
        cartItem.decreaseQuantity() // Уменьшаем количество
        _cartItems.value = _cartItems.value // Обновляем состояние LiveData
    }
}
