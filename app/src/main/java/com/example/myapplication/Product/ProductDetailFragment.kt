package com.example.myapplication.Product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.ui.cart.BasketViewModel

// Класс ProductDetailFragment, который наследуется от Fragment
class ProductDetailFragment : Fragment() {

    // Объявляем переменные для элементов интерфейса
    private lateinit var imageView: ImageView
    private lateinit var textViewName: TextView
    private lateinit var textViewPrice: TextView

    // Переопределяем метод onCreateView, который создает интерфейс фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Создаем представление фрагмента из XML-макета
        val view = inflater.inflate(R.layout.fragment_product_detail, container, false)

        // Находим элементы интерфейса по их идентификаторам
        imageView = view.findViewById(R.id.imageView_item_product_detail_fragment_home)
        textViewName = view.findViewById(R.id.textViewName_product_detail_fragment_home)
        textViewPrice = view.findViewById(R.id.textViewPrice_product_detail_fragment_home)

        // Получаем объект продукта из аргументов фрагмента
        val product = arguments?.getParcelable<Product>("product")
        // Если продукт существует, устанавливаем данные в элементы интерфейса
        product?.let {
            imageView.setImageResource(it.imageResId) // Устанавливаем изображение
            textViewName.text = it.name // Устанавливаем название продукта
            textViewPrice.text = it.price.toString() // Устанавливаем цену продукта
        }

        // Находим кнопку возврата и устанавливаем обработчик клика
        val buttonBack: Button = view.findViewById(R.id.buttonBack_product_detail_fragment_home)
        buttonBack.setOnClickListener {
            // Возвращаемся на предыдущий экран при нажатии на кнопку
            requireActivity().onBackPressed()
        }

        // Получаем экземпляр ViewModel корзины
        val basketViewModel = ViewModelProvider(requireActivity()).get(BasketViewModel::class.java)

        // Находим кнопку "Добавить в корзину" и устанавливаем обработчик клика
        val buttonAddToCart: Button = view.findViewById(R.id.buttonAddToBasket_product_detail_fragment_home)
        buttonAddToCart.setOnClickListener {
            // Если продукт существует, добавляем его в корзину и показываем уведомление
            product?.let {
                basketViewModel.addToBasket(it) // Добавляем продукт в корзину
                // Показываем сообщение с информацией о добавлении продукта
                Toast.makeText(requireContext(), "${it.name} добавлен в корзину", Toast.LENGTH_SHORT).show()
            }
        }

        // Возвращаем созданное представление
        return view
    }
}
