package com.example.myapplication.ImageHome // Определяем пакет, в котором находится данный класс

import android.view.LayoutInflater // Импортируем класс для создания представлений
import android.view.View // Импортируем класс для работы с представлениями
import android.view.ViewGroup // Импортируем класс для группировки представлений
import android.widget.ImageView // Импортируем класс для работы с изображениями
import androidx.recyclerview.widget.RecyclerView // Импортируем класс для работы с RecyclerView
import com.example.myapplication.R // Импортируем ресурсы приложения

// Определяем класс адаптера для показа изображений, который наследует от RecyclerView.Adapter
class ImageAdapter(private val images: List<Int>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    // Вложенный класс ViewHolder для управления элементами списка
    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Инициализация ImageView, на который будут загружаться изображения
        val imageView: ImageView = itemView.findViewById(R.id.imageView_item_product_detail_fragment_home)
    }

    // Метод, который создает новый ViewHolder при необходимости
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        // Создаем новое представление из заданного XML-макета
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image_home, parent, false)
        // Возвращаем новый созданный ViewHolder, содержащий это представление
        return ImageViewHolder(view)
    }

    // Метод, который отображает данные в конкретном элементе списка
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        // Устанавливаем изображение в ImageView по позиции, с использованием остатка от деления для зацикливания
        holder.imageView.setImageResource(images[position % images.size])
    }

    // Метод, который возвращает общее количество элементов в адаптере
    override fun getItemCount(): Int {
        return Int.MAX_VALUE // Возвращает максимально возможное значение, обеспечивая зацикленное поведение
    }
}
