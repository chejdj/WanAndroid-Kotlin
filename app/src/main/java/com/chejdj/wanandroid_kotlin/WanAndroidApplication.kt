package com.chejdj.wanandroid_kotlin

import android.app.Application

/**
 * Created by zhuyangyang on 2019-08-14
 */
class WanAndroidApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object {
        var application: WanAndroidApplication? = null
    }
}