package com.junior.projetomvvmcleanxml.presentation.principal.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.junior.projetomvvmcleanxml.databinding.ItemBinding
import com.junior.projetomvvmcleanxml.domain.model.item.Item

class AdapterItem(val context: Context,private val listItem: MutableList<Item>):
    RecyclerView.Adapter<AdapterItem.ItemViewHolder>(){
    inner class ItemViewHolder(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
       val binding = ItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int  = listItem.size

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
       val item = listItem[position]
        holder.binding.txtName.text = item.name
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Item>){
        listItem.clear()
        listItem.addAll(newList)
        notifyDataSetChanged()
    }

}