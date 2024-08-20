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
import com.example.myapplication.ui.cart.CartViewModel

class ProductDetailFragment : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var textViewName: TextView
    private lateinit var textViewPrice: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_detail, container, false)

        imageView = view.findViewById(R.id.imageView_item_product_detail_fragment_home)
        textViewName = view.findViewById(R.id.textViewName)
        textViewPrice = view.findViewById(R.id.textViewPrice)

        // Получаем данные из аргументов
        val product = arguments?.getParcelable<Product>("product")
        product?.let {
            imageView.setImageResource(it.imageResId)
            textViewName.text = it.name
            textViewPrice.text = it.price.toString()
        }

        // Устанавливаем обработчик для кнопки возврата
        val buttonBack: Button = view.findViewById(R.id.buttonBack)
        buttonBack.setOnClickListener {
            // Используем findNavController для возврата к фрагменту Home
            requireActivity().onBackPressed()
        }
        val cartViewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)

        val buttonAddToCart: Button = view.findViewById(R.id.buttonAddToCart)
        buttonAddToCart.setOnClickListener {
            product?.let {
                cartViewModel.addToCart(it)
                Toast.makeText(requireContext(), "${it.name} добавлен в корзину", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
