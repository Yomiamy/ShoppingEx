package com.ex.shoppingex.utility

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object FragmentUtil {

    /* Is fragment exist */
    fun isFragmentExist(fragmentManager: FragmentManager, viewID: Int): Boolean {
        val fragment = fragmentManager.findFragmentById(viewID)

        return fragment != null
    }

    /* Is fragment exist */
    fun isFragmentExist(fragmentManager: FragmentManager, tag: String): Boolean {
        val fragment = fragmentManager.findFragmentByTag(tag)

        return fragment != null
    }

    /* Add fragment */
    fun addFragment(fragmentManager: FragmentManager,
                    viewID: Int,
                    fragment: Fragment,
                    tag: String,
                    isAddToBackStack: Boolean = true,
                    bundle: Bundle? = null
    ) {
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (bundle != null) {
            fragment.arguments = bundle
        }

        fragmentTransaction.add(viewID, fragment, tag)

        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(tag)
        }

        fragmentTransaction.commit()
    }

    /* Hide fragment */
    fun hideFragment(fragmentManager: FragmentManager,
                     fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.hide(fragment)
        fragmentTransaction.commit()
    }

    /* Show fragment */
    fun showFragment(fragmentManager: FragmentManager,
                     fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.show(fragment)
        fragmentTransaction.commit()
    }

    /* Replace fragment */
    fun replaceFragment(
        fragmentManager: FragmentManager,
        viewID: Int,
        fragment: Fragment,
        tag: String,
        isAddToBackStack: Boolean = true,
        bundle: Bundle? = null
    ) {
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (bundle != null) {
            fragment.arguments = bundle
        }

        fragmentTransaction.replace(viewID, fragment, tag)

        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(tag)
        }

        fragmentTransaction.commit()
    }

    /* Remove fragment */
    fun removeFragment(fragmentManager: FragmentManager, fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.remove(fragment)
        fragmentTransaction.commit()
    }
}