package com.example.myapplication.ui.basket.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R

class ContactNumberFragment : Fragment() {
    private lateinit var contactEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact_number, container, false)
        contactEditText = view.findViewById(R.id.edit_contact_number)
        val confirmButton: Button = view.findViewById(R.id.btn_confirm_order)

        confirmButton.setOnClickListener {
            val inputText = contactEditText.text.toString().trim() // Убираем пробелы в начале и конце
            if (isValidPhoneNumber(inputText)) {
                // Если номер корректный, переход к следующему фрагменту
                findNavController().navigate(R.id.action_fragmen_contact_number_to_fragment_order_complete)
            } else {
                // Если номер некорректный, показываем сообщение
                Toast.makeText(context, "Пожалуйста, введите корректный номер телефона, начинающийся с +7.", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        // Проверяем, что номер начинается с +7 и состоит только из цифр
        return phoneNumber.startsWith("+7") && phoneNumber.substring(2).all { it.isDigit() }
    }
}
