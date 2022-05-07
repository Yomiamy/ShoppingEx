package com.ex.shoppingex

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import com.ex.shoppingex.net.api.ApiInstanceBuilder
import com.ex.shoppingex.net.api.IApi
import com.ex.shoppingex.net.okhttp.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MyApplication: Application() {

    @SuppressLint("CheckResult")
    override fun onCreate() {
        super.onCreate()

        ApiInstanceBuilder.init(applicationContext, Constants.API_DOMAIN)
    }
}