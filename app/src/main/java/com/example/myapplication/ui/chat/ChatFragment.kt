package com.example.myapplication.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentChatBinding
import com.example.myapplication.ui.chat.message.Message
import com.example.myapplication.ui.chat.message.MessageAdapter

class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private lateinit var messageAdapter: MessageAdapter
    private val messageList = mutableListOf<Message>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)

        setupRecyclerView()

        // Обработчик нажатия кнопки "Отправить"
        binding.buttonSend.setOnClickListener {
            sendMessage()
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        messageAdapter = MessageAdapter(messageList, "Вы") // Передаем имя текущего пользователя
        binding.recyclerViewChat.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewChat.adapter = messageAdapter
        loadSampleMessages()
    }

    private fun loadSampleMessages() {
        messageList.add(Message("Пользователь 1", "Привет!"))
        messageList.add(Message("Пользователь 2", "Как дела?"))
        messageList.add(Message("Пользователь 1", "Отлично, спасибо!"))
        messageAdapter.notifyDataSetChanged() // Обновляем адаптер
    }

    private fun sendMessage() {
        val messageText = binding.editTextMessage.text.toString()
        if (messageText.isNotBlank()) {
            messageList.add(Message("Вы", messageText))
            binding.editTextMessage.text.clear() // Очищаем поле ввода
            messageAdapter.notifyItemInserted(messageList.size - 1)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
