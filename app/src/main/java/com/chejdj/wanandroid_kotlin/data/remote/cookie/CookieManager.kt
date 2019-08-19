package com.chejdj.wanandroid_kotlin.data.remote.cookie

import com.chejdj.wanandroid_kotlin.WanAndroidApplication
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * Created by zhuyangyang on 2019-08-13
 */
class CookieManager : CookieJar {
    private val cookieStore = DiskCookieStore(WanAndroidApplication.application!!.applicationContext)
    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
        if (cookies.isNotEmpty()) {
            for (cookie in cookies) {
                cookieStore.addCookie(url.uri(), cookie)
            }
        }
    }

    override fun loadForRequest(url: HttpUrl): MutableList<Cookie> {
        return cookieStore.queryCookie(url.uri())
    }
}