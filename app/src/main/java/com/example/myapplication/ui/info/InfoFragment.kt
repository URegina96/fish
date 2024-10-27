package com.example.myapplication.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding
    private lateinit var adapter: InfoAdapter
    private val items = listOf(
        InfoItem("Первые впечатления.", "Переночевав в гостинице в Гуаякиле, мы сели к агенту в машину и поехали " +
                "на судно в Пуэрто Боливар. Доехали вопреки ожиданиям быстро, примерно за 3-4 часа. Погода была пасмурная и даже " +
                "не смотря на то, что мы находимся недалеко от экватора, было прохладно. Почти все время, пока мы ехали, по обе " +
                "стороны дороги были банановые плантации, но все равно в голове не укладывается: эти бананы грузят на суда в " +
                "нескольких портах Эквадора десятками тысяч тонн каждый день, круглый год. Это ж несчастные бананы должны расти " +
                "быстрее чем грибы."),
        InfoItem("Дороги.", "Дороги в Эквадоре практически идеальные, хотя населенные пункты выглядят очень бедно. " +
                "На дорогах много интересных машин, например очень много грузовиков - древних Фордов, которые я никогда раньше не " +
                "видел. А еще несколько раз на глаза попадались старенькие Жигули :) А еще если кого-то обгоняешь и есть встречная " +
                "машина, она обязательно включает фары. На больших машинах - грузовиках и автобусах, обязательно красуется местный " +
                "тюнинг: машины разукрашенные, либо в наклейках, и обязательно везде огромное множество светодиодов, как будто " +
                "новогодние елки едут и переливаются всеми цветами."),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = InfoAdapter(items) { selectedItem ->
            val bundle = Bundle().apply {
                putString("itemName", selectedItem.name)
                putString("itemDetails", selectedItem.details)
            }

            // Навигация к DetailInfoFragment
            findNavController().navigate(R.id.info_detail_fragment, bundle)
        }

        binding.infoRecyclerview.adapter = adapter
    }
}
