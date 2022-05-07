package com.ex.shoppingex.net.api

import android.annotation.SuppressLint
import android.content.Context
import com.ex.shoppingex.net.okhttp.CustomOkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.NullPointerException

@SuppressLint("StaticFieldLeak")
object ApiInstanceBuilder {

    var mContext:Context? = null
    var mBaseUrl:String? = null
    val mApiMap:HashMap<Class<*>, Any> = HashMap()

    /**
     *  Init Config
     * */
    fun init(context: Context, baseUrl:String) {
        mBaseUrl = baseUrl
        mContext = context
    }

    /**
     *  Init Api instance
     * */
    @Throws(NullPointerException::class)
    inline fun <reified T> build(apiInterfaceClz: Class<T>): T {
        if(mBaseUrl.isNullOrEmpty() || mContext == null) {
            throw NullPointerException("mBaseUrl and mContext must be initialized by init function")
        }

        if(mApiMap.containsKey(apiInterfaceClz)) {
            return mApiMap[apiInterfaceClz] as T
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(mBaseUrl!!)
            .client(CustomOkHttpClient.getOkHttpClient(mContext!!).build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiInterface = retrofit.create(apiInterfaceClz)!!
        mApiMap[apiInterfaceClz] = apiInterface

        return apiInterface
    }
}