package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ImageHome.ImageAdapter
import com.example.myapplication.Product.Product
import com.example.myapplication.Product.ProductAdapter
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Инициализация RecyclerView для изображений с горизонтальной ориентацией
        val imageRecyclerView: RecyclerView = binding.imageRecyclerView
        imageRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        // Пример данных для изображений
        val imageList = listOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image1,
            R.drawable.image2,            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image1,
            R.drawable.image2,            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image1,
            R.drawable.image2,            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image1,
            R.drawable.image2,            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image1,
            R.drawable.image2,
            // и так далее...
        )

        imageRecyclerView.adapter = ImageAdapter(imageList)

        // Настройка RecyclerView для отображения продуктов в 2 колонки
        val productRecyclerView: RecyclerView = binding.recyclerView
        productRecyclerView.layoutManager = GridLayoutManager(context, 2)

        // Пример данных для продуктов
        val productList = listOf(
            Product("Product 1", "$10.00", R.drawable.image1),
            Product("Product 2", "$20.00", R.drawable.image2),            Product("Product 1", "$10.00", R.drawable.image1),
            Product("Product 2", "$20.00", R.drawable.image2),            Product("Product 1", "$10.00", R.drawable.image1),
            Product("Product 2", "$20.00", R.drawable.image2),            Product("Product 1", "$10.00", R.drawable.image1),
            Product("Product 2", "$20.00", R.drawable.image2),            Product("Product 1", "$10.00", R.drawable.image1),
            Product("Product 2", "$20.00", R.drawable.image2),            Product("Product 1", "$10.00", R.drawable.image1),
            Product("Product 2", "$20.00", R.drawable.image2),
            // и так далее...
        )

        // Обработчик нажатия на элемент
        val onItemClick: (Product) -> Unit = { product ->
            val bundle = Bundle().apply {
                putParcelable("product", product)
            }
            findNavController().navigate(R.id.fragment_product_detail, bundle)
        }

        productRecyclerView.adapter = ProductAdapter(productList, onItemClick)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}