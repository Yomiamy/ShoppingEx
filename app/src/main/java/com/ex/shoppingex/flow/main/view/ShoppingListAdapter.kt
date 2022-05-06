package com.ex.shoppingex.flow.main.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ex.shoppingex.R
import com.ex.shoppingex.databinding.ViewShoppingItemBinding
import com.ex.shoppingex.data.ShoppingItemInfo

class ShoppingListAdapter(context: Context):ListAdapter<ShoppingItemInfo, ShoppingListAdapter.ViewHolder>(DiffCallback){

    private val mLayoutInflater:LayoutInflater = LayoutInflater.from(context)

    fun addItems(itemInfos: List<ShoppingItemInfo>) {
        submitList(itemInfos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:ViewShoppingItemBinding = DataBindingUtil.inflate(mLayoutInflater, R.layout.view_shopping_item, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemInfo: ShoppingItemInfo = getItem(position)

        holder.bind(itemInfo)
    }

    class ViewHolder(val binding: ViewShoppingItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemInfo: ShoppingItemInfo) {
            binding.apply {
                executePendingBindings()
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<ShoppingItemInfo>() {
        override fun areItemsTheSame(oldItem: ShoppingItemInfo, newItem: ShoppingItemInfo): Boolean = (oldItem === newItem)

        override fun areContentsTheSame(oldItem: ShoppingItemInfo, newItem: ShoppingItemInfo): Boolean = (oldItem == newItem)
    }
}