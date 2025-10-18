package com.junior.projetomvvmcleanxml.presentation.principal.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.junior.projetomvvmcleanxml.R
import com.junior.projetomvvmcleanxml.databinding.ItemBinding
import com.junior.projetomvvmcleanxml.domain.model.item.Item

class AdapterItem(val context: Context,private val listItem: MutableList<Item>, private val isCloud: Boolean = false):
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

        if (isCloud) {
            holder.binding.txtStatus.text = context.getString(R.string.status_cloud)

        } else {
            if (item.isSynchronized) {
                holder.binding.txtStatus.text = context.getString(R.string.status_ok)
            } else {
                holder.binding.txtStatus.text = context.getString(R.string.status_pending)
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Item>){
        listItem.clear()
        listItem.addAll(newList)
        notifyDataSetChanged()
    }

}