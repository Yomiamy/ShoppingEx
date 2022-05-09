package com.ex.shoppingex.flow.shopping_info_detail.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ex.shoppingex.R
import com.ex.shoppingex.data.ShoppingItemInfo
import com.ex.shoppingex.databinding.FragmentShoppingInfoDetailBinding
import com.ex.shoppingex.flow.shopping_info_detail.viewmodel.ShoppingInfoDetailViewModel

class ShoppingInfoDetailFragment : Fragment() {
    companion object {
        const val TAG:String = "ShoppingListFragment"
        const val BUNDLE_KEY_SHOPPING_ITEM_INFO = "shopping_item_info"

        fun newInstance():ShoppingInfoDetailFragment = ShoppingInfoDetailFragment()
    }

    private lateinit var mBinding:FragmentShoppingInfoDetailBinding
    private lateinit var mViewModel:ShoppingInfoDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shopping_info_detail, container, false)

        initData()
        initView()

        return mBinding.root
    }

    private fun initData() {
         ShoppingInfoDetailViewModel().run {
            mViewModel = this
            mBinding.viewModel = this
        }

        arguments?.run {
            mViewModel.shoppingItemInfo = getParcelable(BUNDLE_KEY_SHOPPING_ITEM_INFO)!!
        }
    }

    private fun initView() {
        mViewModel.clickListener.set {
            when(it.id) {
                R.id.iv_back -> requireActivity().onBackPressed()
            }
        }
    }
}