package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ImageHome.ImageAdapter
import com.example.myapplication.Product.Product
import com.example.myapplication.Product.ProductAdapter
import com.example.myapplication.R


import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.cart.CartViewModel // Импортируйте ваш CartViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cartViewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java) // Инициализация CartViewModel

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerViews()
        return root
    }

    private fun setupRecyclerViews() {
        // Настройка RecyclerView для изображений
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

        // Настройка RecyclerView для продуктов
        val productRecyclerView: RecyclerView = binding.recyclerView
        productRecyclerView.layoutManager = GridLayoutManager(context, 2)

        val productList = listOf(
            Product("Product 1", 10.00, R.drawable.image1),
            Product("Product 2", 20.00, R.drawable.image2),
            Product("Product 1", 10.00, R.drawable.image1),
            Product("Product 2", 20.00, R.drawable.image2),
            Product("Product 1", 10.00, R.drawable.image1),
            Product("Product 2", 20.00, R.drawable.image2),
            Product("Product 1", 10.00, R.drawable.image1),
            Product("Product 2", 20.00, R.drawable.image2),
        )

        val onItemClick: (Product) -> Unit = { product ->
            val bundle = Bundle().apply { putParcelable("product", product) }
            findNavController().navigate(R.id.fragment_product_detail, bundle)
        }

        productRecyclerView.adapter = ProductAdapter(productList, onItemClick, cartViewModel)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
