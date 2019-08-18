package com.chejdj.wanandroid_kotlin.ui.home

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chejdj.wanandroid_kotlin.data.bean.HomeBannerBean
import com.youth.banner.loader.ImageLoader

class HomeImageLoader : ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        Glide.with(context!!).load((path as HomeBannerBean).imagePath).into(imageView!!)
    }
}