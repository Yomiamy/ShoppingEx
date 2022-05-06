package com.ex.shoppingex.data

data class ShoppingItemInfo(val price:Int?,
                            val martShortName:String?,
                            val imageUrl:String?,
                            val finalPrice:Int?,
                            val martName:String?,
                            val stockAvailable:Int?,
                            val martId:Int?) {

    val finalPriceDispStr:String
        get() = "$${finalPrice ?: 0}"

    val martNameDispStr:String
        get() = "$${finalPrice ?: 0}"

    override fun equals(other: Any?): Boolean {
        if(other == null || other !is ShoppingItemInfo) {
            return false
        }

        return price == other.price
                && martShortName.equals(other.martShortName)
                && imageUrl.equals(other.imageUrl)
                && finalPrice == other.finalPrice
                && martName.equals(other.martName)
                && stockAvailable == other.stockAvailable
                && martId == other.martId
    }
}
