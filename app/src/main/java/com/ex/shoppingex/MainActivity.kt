package com.ex.shoppingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ex.shoppingex.databinding.ActivityMainBinding
import com.ex.shoppingex.flow.main.view.ShoppingListFragment
import com.ex.shoppingex.utility.FragmentUtil

class MainActivity : AppCompatActivity() {

    lateinit var mBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        this.mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        FragmentUtil.replaceFragment(supportFragmentManager,
            R.id.fl_content,
            ShoppingListFragment.newInstance(),
            ShoppingListFragment.TAG)
    }
}