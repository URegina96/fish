package com.example.myapplication.ui.basket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.cart.BasketViewModel

// Адаптер для управления отображением товаров в корзине.
class BasketAdapter(
    private val basketItems: List<BasketItem>, // Список товаров в корзине
    private val onRemove: (BasketItem) -> Unit, // Обработчик для удаления товара
    private val basketViewModel: BasketViewModel // ViewModel для работы с корзиной
) : RecyclerView.Adapter<BasketAdapter.ViewHolder>() {

    // Внутренний класс для хранения представления элемента списка
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.product_image) // Инициализация ImageView
        val quantityTextView: TextView = itemView.findViewById(R.id.quantity_text) // Инициализация текстового поля для количества
        val deleteButton: Button = itemView.findViewById(R.id.delete_button) // Инициализация кнопки удаления
        val increaseButton: Button = itemView.findViewById(R.id.increase_button) // Кнопка для увеличения количества товара
        val decreaseButton: Button = itemView.findViewById(R.id.decrease_button) // Кнопка для уменьшения количества товара

        // Метод для привязки данных к элементу списка
        fun bind(basketItem: BasketItem) {
            imageView.setImageResource(basketItem.product.imageResId) // Устанавливаем изображение
            quantityTextView.text = basketItem.quantity.toString() // Устанавливаем количество

            // Обработчик клика для кнопки удаления товара
            deleteButton.setOnClickListener { onRemove(basketItem) }

            // Обработчик клика для кнопки увеличения количества
            increaseButton.setOnClickListener {
                basketViewModel.increaseQuantity(basketItem) // Увеличиваем количество в ViewModel
                notifyItemChanged(adapterPosition) // Обновляем текущий элемент
            }

            // Обработчик клика для кнопки уменьшения количества
            decreaseButton.setOnClickListener {
                basketViewModel.decreaseQuantity(basketItem) // Уменьшаем количество в ViewModel
                notifyItemChanged(adapterPosition) // Обновляем текущий элемент
            }
        }
    }

    // Создание нового ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Используем разметку item_basket
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_basket, parent, false)
        return ViewHolder(view)
    }

    // Привязка данных к ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(basketItems[position]) // Передача элемента корзины в ViewHolder
    }

    // Возвращаем общее количество товаров в корзине
    override fun getItemCount(): Int = basketItems.size
}
