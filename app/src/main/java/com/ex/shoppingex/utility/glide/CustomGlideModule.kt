package com.ex.shoppingex.utility.glide

import android.content.Context
import android.graphics.drawable.PictureDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.engine.executor.GlideExecutor
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.ex.shoppingex.net.okhttp.CustomOkHttpClient
import okhttp3.OkHttpClient
import java.io.InputStream

@GlideModule
class CustomGlideModule : AppGlideModule() {

    companion object {
        private const val THREAD_SOURCE_NAME = "General"
        private var sOkHttpClient: OkHttpClient? = null
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        builder.setSourceExecutor(
            GlideExecutor.newSourceBuilder().setName(THREAD_SOURCE_NAME).build()
        )
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        sOkHttpClient = if(sOkHttpClient == null) CustomOkHttpClient.getOkHttpClient(context).build() else sOkHttpClient

        registry.replace(
            GlideUrl::class.java,
            InputStream::class.java, OkHttpUrlLoader.Factory(sOkHttpClient!!)
        )
    }

    override fun isManifestParsingEnabled(): Boolean = false
}