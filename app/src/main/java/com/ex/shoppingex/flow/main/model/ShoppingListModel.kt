package com.ex.shoppingex.flow.main.model

import android.annotation.SuppressLint
import android.util.Log
import com.ex.shoppingex.data.ShoppingItemInfo
import com.ex.shoppingex.net.api.ApiInstanceBuilder
import com.ex.shoppingex.net.api.IApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ShoppingListModel {

    companion object {
        const val TAG = "ShoppingListModel"
    }

    private lateinit var mDisposable: Disposable
    private var mShoppingList:List<ShoppingItemInfo> = emptyList()

    @SuppressLint("CheckResult")
    fun getShoppingList(keyword:String, callback: (List<ShoppingItemInfo>?, Throwable?) -> Unit) {
        if(keyword.isNullOrEmpty() && mShoppingList.isEmpty()) {
            mDisposable = ApiInstanceBuilder.build(IApi::class.java)
                .getShoppingList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mShoppingList = it.data ?: emptyList()

                    Log.d(TAG, "List data size = ${it.data?.size}")
                    callback(mShoppingList, null)
                }, {
                    Log.d(TAG, "Error = ${it.message}")
                    it.printStackTrace()
                    callback(emptyList(), it)
                })
        } else {
            val filteredShoppingList = mShoppingList.filter { it.martName?.contains(keyword) ?: false }

            callback(filteredShoppingList, null)
        }
    }

    fun clear() {
        if(!mDisposable.isDisposed) {
            mDisposable.dispose()
        }
    }
}