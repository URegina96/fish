package com.example.myapplication.ui.dashboard

import com.example.myapplication.Product.Product

// новый data class для представления товаров в корзине.
// Этот класс будет содержать информацию о продукте, количестве и стоимости.
data class CartItem(
    val product: Product,
    var quantity: Int
) {
    val totalPrice: Double
        get() = product.price.toDouble() * quantity
}
