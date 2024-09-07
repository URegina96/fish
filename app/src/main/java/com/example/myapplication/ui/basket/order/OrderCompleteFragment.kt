package com.example.myapplication.ui.basket.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class OrderCompleteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order_complete, container, false)
        val thanksTextView: TextView = view.findViewById(R.id.thanks_text)
        val waitTextView: TextView = view.findViewById(R.id.wait_text)

        thanksTextView.text = "Спасибо за заказ!"
        waitTextView.text = "Ожидайте звонка."

        return view
    }
}
