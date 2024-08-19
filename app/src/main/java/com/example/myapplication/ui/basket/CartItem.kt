package com.example.myapplication.ui.basket

import com.example.myapplication.Product.Product

// новый data class для представления товаров в корзине.
// Этот класс будет содержать информацию о продукте, количестве и стоимости.
data class CartItem(
    val product: Product,
    var quantity: Int
) {
    val totalPrice: Double
        get() = product.price * quantity

    fun increaseQuantity() {
        quantity++
    }

    fun decreaseQuantity() {
        if (quantity > 1) {
            quantity--
        }
    }

}
