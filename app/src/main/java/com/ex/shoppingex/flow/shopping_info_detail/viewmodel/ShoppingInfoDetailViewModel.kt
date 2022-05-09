package com.ex.shoppingex.flow.shopping_info_detail.viewmodel

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.ex.shoppingex.data.ShoppingItemInfo

class ShoppingInfoDetailViewModel {

    val clickListener = ObservableField<View.OnClickListener>()
    var martId = MutableLiveData<String>()
    val martName = MutableLiveData<String>()
    val price = MutableLiveData<String>()
    val imageUrl =  MutableLiveData<String>()
    var shoppingItemInfo: ShoppingItemInfo? = null
        set(itemInfo) {
            field = itemInfo
            martId.value = itemInfo?.martIdDispStr
            martName.value =  itemInfo?.martNameDispStr
            price.value = itemInfo?.finalPriceDispStr
            imageUrl.value = itemInfo?.imageUrl
        }
        get() = field
}