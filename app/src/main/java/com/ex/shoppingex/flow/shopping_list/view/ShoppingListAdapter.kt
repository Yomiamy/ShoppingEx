package com.ex.shoppingex.flow.shopping_list.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ex.shoppingex.R
import com.ex.shoppingex.databinding.ViewShoppingItemBinding
import com.ex.shoppingex.data.ShoppingItemInfo
import com.ex.shoppingex.flow.shopping_list.viewmodel.ShoppingListViewModel
import com.ex.shoppingex.utility.ViewUtil

class ShoppingListAdapter(val mContext: Context, val mViewModel:ShoppingListViewModel):ListAdapter<ShoppingItemInfo, ShoppingListAdapter.ViewHolder>(DiffCallback){

    private val mLayoutInflater:LayoutInflater = LayoutInflater.from(mContext)
    private var mKeyword:String = ""

    fun addItems(keyword:String, itemInfos: List<ShoppingItemInfo>) {
        mKeyword = keyword

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

    inner class ViewHolder(val binding: ViewShoppingItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemInfo: ShoppingItemInfo) {
            binding.apply {
                shoppingItemInfo = itemInfo
                tvMartName.text = ViewUtil.highlightText(itemInfo.martNameDispStr, mKeyword, mContext.getColor(R.color.shopping_item_keyword_highlight_bg))

                Glide.with(binding.root)
                    .load(itemInfo.imageUrl)
                    .fitCenter()
                    .into(ivItemPhoto)

                root.setOnClickListener {
                    mViewModel.setSelectedShoppingItemInfo(itemInfo)
                }

                executePendingBindings()
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<ShoppingItemInfo>() {
        override fun areItemsTheSame(oldItem: ShoppingItemInfo, newItem: ShoppingItemInfo): Boolean = (oldItem === newItem)

        override fun areContentsTheSame(oldItem: ShoppingItemInfo, newItem: ShoppingItemInfo): Boolean = (oldItem == newItem)
    }
}