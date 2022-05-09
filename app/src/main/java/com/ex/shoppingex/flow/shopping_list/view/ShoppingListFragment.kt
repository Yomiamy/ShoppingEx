package com.ex.shoppingex.flow.shopping_list.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ex.shoppingex.R
import com.ex.shoppingex.databinding.FragmentShoppingListBinding
import com.ex.shoppingex.flow.shopping_info_detail.view.ShoppingInfoDetailFragment
import com.ex.shoppingex.flow.shopping_info_detail.view.ShoppingInfoDetailFragment.Companion.BUNDLE_KEY_SHOPPING_ITEM_INFO
import com.ex.shoppingex.flow.shopping_list.viewmodel.ShoppingListViewModel
import com.ex.shoppingex.utility.FragmentUtil

class ShoppingListFragment : Fragment() {

    companion object {
        const val TAG:String = "ShoppingListFragment"

        fun newInstance() = ShoppingListFragment()
    }

    private lateinit var mBinding:FragmentShoppingListBinding
    private lateinit var mViewModel: ShoppingListViewModel
    private lateinit var mShoppingListAdapter: ShoppingListAdapter
    private var mKeyword:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_shopping_list, null, false)

        initView()
        initData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return mBinding.root
    }

    override fun onDestroyView() {
        mViewModel.clear()

        super.onDestroyView()
    }

    private fun initView() {
        mBinding.rvShoppingList.apply {
            mViewModel = ViewModelProvider(this@ShoppingListFragment).get(ShoppingListViewModel::class.java)
            layoutManager = LinearLayoutManager(context)
            mShoppingListAdapter = ShoppingListAdapter(context, mViewModel)
            adapter = mShoppingListAdapter

            setHasFixedSize(true)
        }

        mBinding.etSearch.addTextChangedListener {
            val keyword:String = it.toString()

            mKeyword = keyword
            mViewModel.getShoppingList(keyword)
        }
    }

    private fun initData() {
        mViewModel.getShoppingList(mKeyword).observe(requireActivity()) {
            this.mShoppingListAdapter.addItems(mKeyword, it)
        }

        mViewModel.obsSelectedShoppingItemInfo().observe(requireActivity()) {
            Log.d(TAG, "Selected item = ${it?.martName}")

            val fragmentManager = requireActivity().supportFragmentManager
            val bundle = Bundle().apply {
                putParcelable(BUNDLE_KEY_SHOPPING_ITEM_INFO, it)
            }
            FragmentUtil.replaceFragment(fragmentManager,
                R.id.fl_content,
                ShoppingInfoDetailFragment.newInstance(),
                ShoppingInfoDetailFragment.TAG,
            true,
                bundle)
        }
    }
}