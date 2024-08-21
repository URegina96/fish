package com.example.myapplication.ui.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemInfoBinding

class InfoAdapter(
    private val items: List<InfoItem>,
    private val itemClickListener: (InfoItem) -> Unit
) : RecyclerView.Adapter<InfoAdapter.InfoViewHolder>() {

    inner class InfoViewHolder(private val binding: ItemInfoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: InfoItem) {
            binding.itemInfoTitle.text = item.name
            binding.root.setOnClickListener {
                itemClickListener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val binding = ItemInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
