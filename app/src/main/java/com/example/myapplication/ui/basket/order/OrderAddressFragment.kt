package com.example.myapplication.ui.basket.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import androidx.navigation.fragment.findNavController


class OrderAddressFragment : Fragment() {
    private lateinit var addressEditText: EditText
    private lateinit var commentEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order_address, container, false)
        addressEditText = view.findViewById(R.id.edit_address)
        commentEditText = view.findViewById(R.id.edit_comment)
        val completeOrderButton: Button = view.findViewById(R.id.btn_complete_order)

        completeOrderButton.setOnClickListener {
            // Получение данных из EditText
            val address = addressEditText.text.toString()
            val comment = commentEditText.text.toString()

            // Можно добавить дополнительную логику, например, валидацию данных

            // Переход к ContactNumberFragment
            findNavController().navigate(R.id.action_fragment_order_address_to_fragmen_contact_number)
        }

        return view
    }
}

