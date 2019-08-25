package com.chejdj.wanandroid_kotlin.utils

import com.chejdj.wanandroid_kotlin.WanAndroidApplication

class StringUtils {
    companion object {
        fun getString(resId: Int): String {
            return WanAndroidApplication.application!!.getString(resId)
        }
    }
}