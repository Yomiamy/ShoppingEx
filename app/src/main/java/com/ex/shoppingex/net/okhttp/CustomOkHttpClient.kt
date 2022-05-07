package com.ex.shoppingex.net.okhttp

import android.content.Context
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import java.io.File
import java.security.cert.CertificateException
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object CustomOkHttpClient {

    // 50 MB
    private const val CACHE_DIR_NAME = "http_cache"
    private const val CACHE_SIZE = 50L * 1024L * 1024L
    private const val CONNECTION_MAX_IDLE_COUNT = 10
    // In Minutes
    private const val CONNECTION_MAX_ALIVE_DURATION = 10L

    fun getOkHttpClient(context: Context): OkHttpClient.Builder {
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) = Unit

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) = Unit

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> = arrayOf()
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL").apply {
                init(null, trustAllCerts, java.security.SecureRandom())
            }
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            val httpClient = OkHttpClient.Builder().apply {
                cache(
                    Cache(
                        File(context.cacheDir, CACHE_DIR_NAME),
                        CACHE_SIZE
                    )
                )
                connectionPool(
                    ConnectionPool(
                        CONNECTION_MAX_IDLE_COUNT,
                        CONNECTION_MAX_ALIVE_DURATION,
                        TimeUnit.MINUTES
                    )
                )
                sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                hostnameVerifier { _, _ -> true }
            }

            return httpClient
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}