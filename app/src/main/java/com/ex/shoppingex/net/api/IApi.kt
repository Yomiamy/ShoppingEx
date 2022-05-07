package com.ex.shoppingex.net.api

import com.ex.shoppingex.data.ShoppingListInfo
import io.reactivex.Observable
import retrofit2.http.*

interface IApi {

    /**
     * Get shopping list
     */
    @GET("/apis2/test/marttest.jsp")
    fun getShoppingList(): Observable<ShoppingListInfo>
}