package com.chejdj.wanandroid_kotlin.data.remote.cookie

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.chejdj.wanandroid_kotlin.utils.GsonUtils
import okhttp3.Cookie
import java.net.URI
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.ReentrantLock

/**
 * Created by zhuyangyang on 2019-08-13
 */
class DiskCookieStore(context: Context) {
    private val FILE_NAME = "cookies"
    private val COOKIE_NAME_PREFIX = "cookie_"
    private var cookieStore: HashMap<String, ConcurrentHashMap<String, Cookie>> = HashMap()
    private var preferences: SharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    private var mLock: ReentrantLock = ReentrantLock(false)

    init {
        loadFromDisk()
    }

    private fun loadFromDisk() {
        val prefersMap: Map<String, *> = preferences.all
        for ((key, value) in prefersMap) {
            if (!key.startsWith(COOKIE_NAME_PREFIX)) {
                val cookiesName = TextUtils.split(value as String, ",")
                for (name in cookiesName) {
                    val json = prefersMap[COOKIE_NAME_PREFIX + name] as String
                    if (!TextUtils.isEmpty(json)) {
                        val cookie = GsonUtils.fromJsonIgnoreException(json!!, Cookie::class.java)
                        if (cookie != null) {
                            if (!cookieStore.containsKey(key)) {
                                cookieStore[key] = ConcurrentHashMap()
                            }
                            cookieStore[key]!![name] = cookie
                        }
                    }
                }
            }
        }
    }

    fun addCookie(uri: URI, cookie: Cookie) {
        if (cookie.persistent() && cookieIsExpired(uri, cookie)) {
            mLock.lock()
            if (!cookieStore.contains(uri.host)) {
                cookieStore[uri.host] = ConcurrentHashMap()
            }
            val name = getCookieName(cookie)
            cookieStore[uri.host]!![name] = cookie
            val preferEditor = preferences.edit()
            preferEditor.putString(uri.host, TextUtils.join(",", cookieStore[uri.host]!!.keys))
            preferEditor.putString(COOKIE_NAME_PREFIX + name, GsonUtils.gson.toJson(cookie))
            preferEditor.commit()
            mLock.unlock()
        }
    }

    fun queryCookie(uri: URI): MutableList<Cookie>? {
        mLock.lock()
        if (cookieStore.containsKey(uri.host)) {
            return cookieStore[uri.host]!!.values as MutableList<Cookie>
        }
        mLock.unlock()
        return null
    }

    private fun getCookieName(cookie: Cookie): String {
        return cookie.name() + cookie.domain()
    }

    private fun cookieIsExpired(uri: URI, cookie: Cookie): Boolean {
        val name = getCookieName(cookie)
        if (cookieStore.contains(uri.host) && cookieStore[uri.host]!!.contains(name)) {
            val lastCookie = cookieStore[uri.host]!![name]
            val currentTime = System.currentTimeMillis()
            if (lastCookie!!.expiresAt() > currentTime) {
                return true
            } else false
        }
        return true
    }
}