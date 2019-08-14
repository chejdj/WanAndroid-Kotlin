package com.chejdj.wanandroid_kotlin.data.remote.cookie

import android.content.Context
import com.chejdj.wanandroid_kotlin.WanAndroidApplication
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * Created by zhuyangyang on 2019-08-13
 */
class CookieManager : CookieJar {
    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
        if (cookies !== null && !cookies.isEmpty()) {
            for (cookie in cookies) {
                cookieStore.addCookie(url.uri(), cookie)
            }
        }
    }

    override fun loadForRequest(url: HttpUrl): MutableList<Cookie>? {
        if (url !== null) {
            return cookieStore.queryCookie(url.uri())
        }
        return null
    }

    companion object {
        val cookieStore: DiskCookieStore = DiskCookieStore(WanAndroidApplication.application as Context)
    }
}