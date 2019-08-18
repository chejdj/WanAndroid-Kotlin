package com.chejdj.wanandroid_kotlin.utils.glide

import android.content.Context
import android.os.Environment
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory


@GlideModule
class WanAndroidGlideModule : AppGlideModule() {
    private val diskSize: Long = 1024 * 1024 * 50  //50M
    private val memorySize = Runtime.getRuntime().maxMemory() / 8 //最大内存的10分之一
    private val fileName = Environment.getExternalStorageDirectory().absolutePath + "/wanandroid/image/"
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDiskCache(DiskLruCacheFactory(fileName, diskSize))
            .setMemoryCache(LruResourceCache(memorySize))
            .setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_RGB_565))
    }
}