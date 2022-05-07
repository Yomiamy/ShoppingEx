package com.ex.shoppingex

import android.annotation.SuppressLint
import android.app.Application
import com.ex.shoppingex.net.api.ApiInstanceBuilder
import com.ex.shoppingex.net.okhttp.Constants

class MyApplication: Application() {

    @SuppressLint("CheckResult")
    override fun onCreate() {
        super.onCreate()

        ApiInstanceBuilder.init(applicationContext, Constants.API_DOMAIN)
    }
}