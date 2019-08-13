package com.chejdj.wanandroid_kotlin.data.remote.cookie

import okhttp3.Cookie

class DiskCookie {
    fun getCookie(): Cookie {
        return Cookie.Builder().build()
    }
}