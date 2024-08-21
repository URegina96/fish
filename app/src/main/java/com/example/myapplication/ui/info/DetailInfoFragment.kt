package com.example.myapplication.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentDetailInfoBinding

class DetailInfoFragment : Fragment() {
    private lateinit var binding: FragmentDetailInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemName = arguments?.getString("itemName") ?: ""
        val itemDetails = arguments?.getString("itemDetails") ?: ""

        binding.itemInfoTitle.text = itemName
        binding.itemInfoText.text = itemDetails

//        // Закрытие фрагмента при нажатии на область фрагмента
//        binding.root.setOnClickListener {
//            findNavController().popBackStack()
//        }

        // Добавляем обработчик нажатия на кнопку
        binding.closeTextInfo.setOnClickListener {
            // Закрываем этот фрагмент
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}
