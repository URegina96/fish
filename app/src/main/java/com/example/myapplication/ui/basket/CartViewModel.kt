package com.example.myapplication.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.Product.Product
import com.example.myapplication.ui.basket.CartItem

//  новый ViewModel для управления состоянием корзины и общей суммы.
class CartViewModel : ViewModel() {

    private val _cartItems = MutableLiveData<MutableList<CartItem>>().apply { value = mutableListOf() }
    val cartItems: LiveData<MutableList<CartItem>> = _cartItems

    fun addToCart(product: Product) {
        val currentItems = _cartItems.value ?: mutableListOf()
        val existingItem = currentItems.find { it.product.name == product.name }

        if (existingItem != null) {
            existingItem.quantity++
        } else {
            currentItems.add(CartItem(product, 1))
        }

        _cartItems.value = currentItems // Убедитесь, что вы обновляете значение LiveData
    }

    fun removeFromCart(product: Product) {
        val currentItems = _cartItems.value?.filter { it.product.name != product.name }
        _cartItems.value = currentItems?.toMutableList() // Обновите состояние LiveData
    }

    fun getTotalPrice(): Double {
        return _cartItems.value?.sumOf { it.totalPrice } ?: 0.0
    }
    fun increaseQuantity(cartItem: CartItem) {
        cartItem.increaseQuantity()
        _cartItems.value = _cartItems.value  // Обновляем состояние LiveData
    }

    fun decreaseQuantity(cartItem: CartItem) {
        cartItem.decreaseQuantity()
        _cartItems.value = _cartItems.value  // Обновляем состояние LiveData
    }

}
