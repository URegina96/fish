package com.example.myapplication.Product

// Импортируем необходимые библиотеки и классы для работы с UI и RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R // Импортируем ресурсы приложения
import com.example.myapplication.ui.cart.BasketViewModel // Импортируем ViewModel для работы с корзиной

// Класс адаптера для RecyclerView, отвечающий за отображение продуктов
class ProductAdapter(
    private val productList: List<Product>, // Список продуктов для отображения
    private val onItemClick: (Product) -> Unit, // Лямбда-функция для обработки клика по элементуA
    private val basketViewModel: BasketViewModel // ViewModel для взаимодействия с корзиной
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() { // Указываем, что адаптер будет использовать наш ViewHolder

    // Вложенный класс ViewHolder, который содержит ссылки на элементы UI для одного товара
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView_item_product_Fragment_home) // Ссылка на ImageView товара
        val textViewName: TextView = itemView.findViewById(R.id.textViewName_Fragment_home) // Ссылка на TextView с именем товара
        val textViewPrice: TextView = itemView.findViewById(R.id.textViewPrice_Fragment_home) // Ссылка на TextView с ценой товара
        val buttonAddToBasket: Button = itemView.findViewById(R.id.buttonAddToBasket_Fragment_home) // Ссылка на кнопку "Добавить в корзину"

        // Инициализация обработчиков событий для каждого элемента ViewHolder
        init {
            // Обработчик клика по элементу списка
            itemView.setOnClickListener {
                val position = adapterPosition // Получаем текущую позицию элемента
                if (position != RecyclerView.NO_POSITION) { // Проверяем, что позиция валидна
                    val product = productList[position] // Получаем продукт по позиции
                    onItemClick(product) // Вызываем лямбда-функцию для обработки клика
                }
            }

            // Обработчик клика на кнопку "Добавить в корзину"
            buttonAddToBasket.setOnClickListener {
                val product = productList[adapterPosition] // Получаем продукт по позиции
                basketViewModel.addToBasket(product) // Добавляем продукт в корзину через ViewModel
                // Показываем пользовательское уведомление о добавлении товара в корзину
                Toast.makeText(itemView.context, "${product.name} добавлен в корзину", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Метод для привязки данных к ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position] // Получаем продукт по позиции
        holder.imageView.setImageResource(product.imageResId) // Устанавливаем изображение товара
        holder.textViewName.text = product.name // Устанавливаем имя товара
        holder.textViewPrice.text = product.price.toString() // Устанавливаем цену товара
    }

    // Метод для создания нового ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Создаем новый View из XML-структуры
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_fargment_home, parent, false)
        return ViewHolder(view) // Возвращаем новый ViewHolder
    }

    // Метод для получения общего количества элементов в списке продуктов
    override fun getItemCount(): Int {
        return productList.size // Возвращаем размер списка продуктов
    }
}
