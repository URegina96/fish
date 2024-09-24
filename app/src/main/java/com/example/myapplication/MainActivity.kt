package com.example.myapplication // Определяем пакет, в котором находится данный класс

import android.os.Bundle // Импортируем класс для работы с Bundle
import android.view.View // Импортируем класс для работы с представлениями
import com.google.android.material.bottomnavigation.BottomNavigationView // Импортируем класс нижнего навигационного меню
import androidx.appcompat.app.AppCompatActivity // Импортируем класс для создания активити приложения
import androidx.navigation.findNavController // Импортируем метод для поиска NavController
import androidx.navigation.ui.setupWithNavController // Импортируем метод для настройки нижнего навигационного меню
import com.example.myapplication.databinding.ActivityMainBinding // Импортируем класс для работы с привязкой представлений

class MainActivity : AppCompatActivity() { // Класс MainActivity, наследующий от AppCompatActivity

    private lateinit var binding: ActivityMainBinding // Объявляем переменную для привязки представления

    override fun onCreate(savedInstanceState: Bundle?) { // Переопределяем метод onCreate для создания активности
        super.onCreate(savedInstanceState) // Вызываем метод родительского класса

        binding = ActivityMainBinding.inflate(layoutInflater) // Инициализируем привязку представления
        setContentView(binding.root) // Устанавливаем корневое представление

        // Устанавливаем ActionBar
        setSupportActionBar(findViewById(R.id.top_toolbar)) // Настраиваем панель инструментов

        val navView: BottomNavigationView = binding.navView // Получаем нижнее навигационное меню
        val navController = findNavController(R.id.nav_host_fragment_activity_main) // Получаем NavController для навигации

        // Устанавливаем NavController для BottomNavigationView
        navView.setupWithNavController(navController) // Настраиваем навигацию для нижнего меню

        // Устанавливаем слушатель для обработки нажатий на элементы нижнего меню
        navView.setOnNavigationItemSelectedListener { item -> // Устанавливаем слушатель для нажатий
            when (item.itemId) { // Проверяем id нажатого элемента
                R.id.navigation_home -> { // Если выбрана Home
                    navController.navigate(R.id.navigation_home) // Переход к фрагменту Home
                    true // Возвращаем true для подтверждения обработки события
                }
                R.id.navigation_basket -> { // Если выбрана Корзина
                    navController.navigate(R.id.navigation_basket) // Переход к фрагменту Корзины
                    true // Возвращаем true для подтверждения обработки события
                }
                R.id.navigation_chat -> { // Если выбрана Чат
                    navController.navigate(R.id.navigation_chat) // Переход к фрагменту Чата
                    true // Возвращаем true для подтверждения обработки события
                }
                R.id.navigation_info -> { // Если выбрана Меню
                    navController.navigate(R.id.navigation_info) // Переход к фрагменту Информации
                    true // Возвращаем true для подтверждения обработки события
                }
                else -> false // Если элемент не найден, возвращаем false
            }
        }

        supportActionBar?.setDisplayShowTitleEnabled(false) // Отключаем отображение заголовка в ActionBar
    }

    // Метод для обработки клика на профиль
    fun onProfileClicked(view: View) { // Метод, вызываемый при нажатии на профиль
        // Получаем NavController из фрагмента
        val navController = findNavController(R.id.nav_host_fragment_activity_main) // Получаем NavController

        // Используем NavController для перехода на фрагмент профиля
        navController.navigate(R.id.user_profile_fragment) // Переход к фрагменту профиля пользователя
    }
}
