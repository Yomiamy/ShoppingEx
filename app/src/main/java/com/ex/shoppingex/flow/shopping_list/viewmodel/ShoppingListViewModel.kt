package com.ex.shoppingex.flow.shopping_list.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ex.shoppingex.data.ShoppingItemInfo
import com.ex.shoppingex.flow.shopping_list.model.ShoppingListModel

class ShoppingListViewModel(application: Application):AndroidViewModel(application) {

    private val mShoppingList = MutableLiveData<List<ShoppingItemInfo>>()
    private val mSelectedShoppingItemInfo = MutableLiveData<ShoppingItemInfo>()
    private val mModel:ShoppingListModel = ShoppingListModel()
    private val mContext: Context
        get() = getApplication<Application>().applicationContext

    fun getShoppingList(keyword:String): MutableLiveData<List<ShoppingItemInfo>> {
        mModel.getShoppingList(keyword) { list, error ->
            mShoppingList.value = list

            if (error != null) {
                Toast.makeText(mContext, "Something error, No data fetched!", LENGTH_SHORT).show()
            }
        }
        return mShoppingList
    }

    fun obsSelectedShoppingItemInfo():MutableLiveData<ShoppingItemInfo> = mSelectedShoppingItemInfo

    fun setSelectedShoppingItemInfo(itemInfo:ShoppingItemInfo) {
        mSelectedShoppingItemInfo.value = itemInfo
    }

    fun clear() {
        mModel.clear()
    }
}