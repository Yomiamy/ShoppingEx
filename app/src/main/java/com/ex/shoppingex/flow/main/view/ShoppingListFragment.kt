package com.ex.shoppingex.flow.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ex.shoppingex.R
import com.ex.shoppingex.databinding.FragmentShoppingListBinding
import com.ex.shoppingex.data.ShoppingItemInfo

class ShoppingListFragment : Fragment() {

    companion object {
        const val TAG:String = "ShoppingListFragment"

        fun newInstance() = ShoppingListFragment()
    }

    private lateinit var mBinding:FragmentShoppingListBinding
    private lateinit var mShoppingListAdapter: ShoppingListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        this.mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shopping_list, container, false)

        initView()
        return this.mBinding.root
    }

    private fun initView() {
        this.mBinding.rvShoppingList.apply {
            layoutManager = LinearLayoutManager(context)
            mShoppingListAdapter = ShoppingListAdapter(context)
            adapter = mShoppingListAdapter

            setHasFixedSize(true)
        }

    }
}