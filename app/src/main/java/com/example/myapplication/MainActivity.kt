package com.example.myapplication

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Устанавливаем ActionBar
        setSupportActionBar(findViewById(R.id.top_toolbar))

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        // Устанавливаем NavController для BottomNavigationView
        navView.setupWithNavController(navController)

        // Устанавливаем слушатель для обработки нажатий на элементы нижнего меню
        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    navController.navigate(R.id.navigation_home) // Переход к HomeFragment
                    true
                }
                R.id.navigation_basket -> {
                    navController.navigate(R.id.navigation_basket) // Переход к BasketFragment
                    true
                }
                R.id.navigation_chat -> {
                    navController.navigate(R.id.navigation_chat) // Переход к ChatFragment
                    true
                }
                R.id.navigation_menu -> {
                    navController.navigate(R.id.navigation_info) // Переход к InfoFragment
                    true
                }
                else -> false
            }
        }

        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    // Метод для обработки клика на профиль
    fun onProfileClicked(view: View) {
        // Получаем NavController из фрагмента
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        // Используем NavController для перехода на фрагмент профиля
        navController.navigate(R.id.user_profile)
    }
}
