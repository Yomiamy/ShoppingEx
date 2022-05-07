package com.ex.shoppingex.flow.main.model

import android.annotation.SuppressLint
import android.util.Log
import com.ex.shoppingex.data.ShoppingItemInfo
import com.ex.shoppingex.net.api.ApiInstanceBuilder
import com.ex.shoppingex.net.api.IApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ShoppingListModel {

    companion object {
        const val TAG = "ShoppingListModel"
    }

    @SuppressLint("CheckResult")
    fun getShoppingList(callback: (List<ShoppingItemInfo>?, Throwable?) -> Unit) {
        ApiInstanceBuilder.build(IApi::class.java)
            .getShoppingList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "List data size = ${it.data?.size}")
                callback(it.data, null)
            }, {
                Log.d(TAG, "Error = ${it.message}")
                it.printStackTrace()
                callback(null, it)
            })
    }
}