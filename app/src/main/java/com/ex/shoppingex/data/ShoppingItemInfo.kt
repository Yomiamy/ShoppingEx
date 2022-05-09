package com.ex.shoppingex.data

import android.os.Parcel
import android.os.Parcelable
import com.ex.shoppingex.extension.formattedPrice

data class ShoppingItemInfo(val price:Int?,
                            val martShortName:String?,
                            val imageUrl:String?,
                            val finalPrice:Int?,
                            val martName:String?,
                            val stockAvailable:Int?,
                            val martId:Int?): Parcelable {
    val finalPriceDispStr:String
        get() = "$${finalPrice?.formattedPrice()}"

    val martNameDispStr:String
        get() = "${martName ?: 0}"

    val martIdDispStr:String
        get() = "${martId ?: 0}"

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

    constructor(source: Parcel) : this(
        source.readValue(Int::class.java.classLoader) as? Int,
        source.readString(),
        source.readString(),
        source.readValue(Int::class.java.classLoader) as? Int,
        source.readString(),
        source.readValue(Int::class.java.classLoader) as? Int,
        source.readValue(Int::class.java.classLoader) as? Int
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(price)
        parcel.writeString(martShortName)
        parcel.writeString(imageUrl)
        parcel.writeValue(finalPrice)
        parcel.writeString(martName)
        parcel.writeValue(stockAvailable)
        parcel.writeValue(martId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ShoppingItemInfo> {
        override fun createFromParcel(parcel: Parcel): ShoppingItemInfo {
            return ShoppingItemInfo(parcel)
        }

        override fun newArray(size: Int): Array<ShoppingItemInfo?> {
            return arrayOfNulls(size)
        }
    }
}
