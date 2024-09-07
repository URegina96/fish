package com.example.myapplication.ui.home // Определяем пакет, в котором находится данный класс

import android.os.Bundle // Импортируем класс для работы с Bundle
import android.view.LayoutInflater // Импортируем класс для создания представлений
import android.view.View // Импортируем класс для работы с представлениями
import android.view.ViewGroup // Импортируем класс для работы с группами представлений
import androidx.fragment.app.Fragment // Импортируем класс Fragment для создания фрагмента
import androidx.navigation.fragment.findNavController // Импортируем метод для навигации между фрагментами
import androidx.recyclerview.widget.GridLayoutManager // Импортируем менеджер макета для сеточного представления
import androidx.recyclerview.widget.LinearLayoutManager // Импортируем менеджер макета для линейного представления
import androidx.recyclerview.widget.RecyclerView // Импортируем компонент для представления списка
import com.example.myapplication.Product.Product // Импортируем класс продукта
import com.example.myapplication.Product.ProductAdapter // Импортируем адаптер для продуктов
import com.example.myapplication.R // Импортируем ресурсный файл

import androidx.lifecycle.ViewModelProvider // Импортируем класс для создания ViewModel
import com.example.myapplication.databinding.FragmentHomeBinding // Импортируем класс для работы с привязкой представлений
import com.example.myapplication.ui.cart.BasketViewModel // Импортируем ViewModel для управления корзиной

class HomeFragment : Fragment() { // Класс HomeFragment, наследующий от Fragment
    private var _binding: FragmentHomeBinding? = null // Переменная для хранения привязки представления
    private val binding get() = _binding!! // Геттер для безопасного доступа к привязке
    private lateinit var basketViewModel: BasketViewModel // Объявляем переменную для ViewModel корзины

    override fun onCreateView( // Переопределяем метод onCreateView для создания представления фрагмента
        inflater: LayoutInflater, // Параметр для создания представлений
        container: ViewGroup?, // Параметр для группировки представлений
        savedInstanceState: Bundle? // Параметр для передачи данных при пересоздании
    ): View {
        basketViewModel = ViewModelProvider(requireActivity()).get(BasketViewModel::class.java) // Получаем экземпляр BasketViewModel

        _binding = FragmentHomeBinding.inflate(inflater, container, false) // Инициализируем привязку представления
        val root: View = binding.root // Получаем корневое представление

        setupRecyclerViews() // Настраиваем RecyclerView
        return root // Возвращаем корневое представление
    }

    private fun setupRecyclerViews() { // Метод для настройки RecyclerView
        // Настройка RecyclerView для изображений
        val imageRecyclerView: RecyclerView = binding.imageRecyclerView // Получаем RecyclerView для изображений
        imageRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) // Устанавливаем линейный менеджер для горизонтального списка

        // Пример данных для изображений
        val imageList = listOf( // Создаем список изображений
            R.drawable.image1, // Добавляем ресурсы изображений
            R.drawable.image2,
            R.drawable.image1,
            R.drawable.image2,
            // и так далее...
        )

        imageRecyclerView.adapter = ImageAdapter(imageList) // Устанавливаем адаптер для RecyclerView изображений

        // Настройка RecyclerView для продуктов
        val productRecyclerView: RecyclerView = binding.recyclerView // Получаем RecyclerView для продуктов
        productRecyclerView.layoutManager = GridLayoutManager(context, 2) // Устанавливаем сеточный менеджер для 2-х колонок

        val productList = listOf( // Создаем список продуктов
            Product("Product 1", 10.00, R.drawable.image1), // Добавляем продукты с названием, ценой и изображением
            Product("Product 2", 20.00, R.drawable.image2),
            Product("Product 2", 20.00, R.drawable.image2),Product("Product 2", 20.00, R.drawable.image2),Product("Product 2", 20.00, R.drawable.image2),Product("Product 2", 20.00, R.drawable.image2),Product("Product 2", 20.00, R.drawable.image2),Product("Product 2", 20.00, R.drawable.image2),Product("Product 2", 20.00, R.drawable.image2),Product("Product 2", 20.00, R.drawable.image2),Product("Product 2", 20.00, R.drawable.image2),Product("Product 2", 20.00, R.drawable.image2),Product("Product 2", 20.00, R.drawable.image2),
        )

        // Лямбда-функция, вызываемая при нажатии на продукт
        val onItemClick: (Product) -> Unit = { product ->
            val bundle = Bundle().apply { putParcelable("product", product) } // Создаем Bundle для передачи данных о продукте
            findNavController().navigate(R.id.fragment_product_detail, bundle) // Переход к фрагменту деталей продукта
        }

        productRecyclerView.adapter = ProductAdapter(productList, onItemClick, basketViewModel) // Устанавливаем адаптер для RecyclerView продуктов
    }

    override fun onDestroyView() { // Переопределяем метод onDestroyView для освобождения ресурсов
        super.onDestroyView() // Вызываем метод родительского класса
        _binding = null // Обнуляем привязку представления
    }
}
