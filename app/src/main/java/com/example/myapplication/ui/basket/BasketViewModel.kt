package com.example.myapplication.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.Product.Product
import com.example.myapplication.ui.basket.BasketItem

// ViewModel для управления состоянием корзины
class BasketViewModel : ViewModel() {

    // MutableLiveData для отслеживания состояния корзины
    private val _basketItems = MutableLiveData<MutableList<BasketItem>>().apply { value = mutableListOf() }
    val basketItems: LiveData<MutableList<BasketItem>> = _basketItems // Доступ к корзине

    // Метод для добавления товара в корзину
    fun addToCart(product: Product) {
        val currentItems = _basketItems.value ?: mutableListOf()
        // Проверка, существует ли продукт в корзине
        val existingItem = currentItems.find { it.product.name == product.name }

        if (existingItem != null) {
            existingItem.increaseQuantity() // Увеличиваем количество, если продукт уже есть
        } else {
            currentItems.add(BasketItem(product, 1)) // Добавляем новый товар
        }

        _basketItems.value = currentItems // Обновляем LiveData
    }

    // Метод для удаления товара из корзины
    fun removeFromCart(product: Product) {
        // Фильтрация списка товаров
        val currentItems = _basketItems.value?.filter { it.product.name != product.name }
        _basketItems.value = currentItems?.toMutableList() // Обновление состояния LiveData
    }

    // Метод для получения общей стоимости корзины
    fun getTotalPrice(): Double {
        return _basketItems.value?.sumOf { it.totalPrice } ?: 0.0 // Подсчет общей стоимости
    }

    // Метод для увеличения количества в CartItem
    fun increaseQuantity(basketItem: BasketItem) {
        basketItem.increaseQuantity() // Увеличиваем количество
        _basketItems.value = _basketItems.value // Обновляем состояние LiveData
    }

    // Метод для уменьшения количества в CartItem
    fun decreaseQuantity(basketItem: BasketItem) {
        basketItem.decreaseQuantity() // Уменьшаем количество
        _basketItems.value = _basketItems.value // Обновляем состояние LiveData
    }
}
