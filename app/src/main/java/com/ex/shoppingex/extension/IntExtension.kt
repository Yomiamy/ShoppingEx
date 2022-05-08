package com.ex.shoppingex.extension

fun Int?.formattedPrice() = String.format("%,d", this ?: 0)