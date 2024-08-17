package com.example.myapplication.Product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class ProductDetailFragment : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var textViewName: TextView
    private lateinit var textViewPrice: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_detail, container, false)

        imageView = view.findViewById(R.id.imageView)
        textViewName = view.findViewById(R.id.textViewName)
        textViewPrice = view.findViewById(R.id.textViewPrice)

        // Получаем данные из аргументов
        val product = arguments?.getParcelable<Product>("product")
        product?.let {
            imageView.setImageResource(it.imageResId)
            textViewName.text = it.name
            textViewPrice.text = it.price
        }

        // Устанавливаем обработчик для кнопки возврата
        val buttonBack: Button = view.findViewById(R.id.buttonBack)
        buttonBack.setOnClickListener {
            // Используем findNavController для возврата к фрагменту Home
            requireActivity().onBackPressed()
        }

        return view
    }
}
