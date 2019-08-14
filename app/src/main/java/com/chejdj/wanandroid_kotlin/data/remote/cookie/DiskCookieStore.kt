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
    val FILE_NAME: String = "cookies"
    var cookieStore: HashMap<String, ConcurrentHashMap<String, Cookie>>? = null
    var context: Context? = null
    var preferences: SharedPreferences? = null
    var mLock: ReentrantLock? = null
    val COOKIE_NAME_PREFIX = "cookie_"

    init {
        this.cookieStore = HashMap()
        this.context = context
        mLock = ReentrantLock(false)
        loadFromDisk()
    }

    private fun loadFromDisk() {
        preferences =
            context?.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val prefersMap = preferences!!.all as Map<String, String>
        for ((key, value) in prefersMap) {
            if (key !== null && !key.startsWith(COOKIE_NAME_PREFIX)) {
                val cookiesName = TextUtils.split(value, ",")
                for (name in cookiesName) {
                    val json = prefersMap.get(COOKIE_NAME_PREFIX + name)
                    if (json !== null) {
                        val cookie = GsonUtils.fromJsonIgnoreException(json, Cookie::class.java)
                        if (cookie != null) {
                            if (!cookieStore!!.containsKey(key)) {
                                cookieStore!!.put(key, ConcurrentHashMap())
                            }
                            cookieStore!!.get(key)!!.put(name, cookie)
                        }
                    }
                }
            }
        }


    }

    fun addCookie(uri: URI, cookie: Cookie) {
        if (cookie.persistent() && cookieIsExpired(uri, cookie)) {
            mLock!!.lock()
            if (!cookieStore!!.contains(uri.host)) {
                cookieStore!!.put(uri.host, ConcurrentHashMap())
            }
            val name = getCookieName(cookie)
            cookieStore!!.get(uri.host)!!.put(name, cookie)
            val preferEditor = preferences!!.edit()
            preferEditor.putString(uri.host, TextUtils.join(",", cookieStore!!.get(uri.host)!!.keys))
            preferEditor.putString(COOKIE_NAME_PREFIX + name, GsonUtils.gson.toJson(cookie))
            preferEditor.commit()
            mLock!!.unlock()
        }
    }

    fun queryCookie(uri: URI): MutableList<Cookie>?{
        mLock!!.lock()
        if (cookieStore!!.containsKey(uri.host)) {
            return cookieStore!!.get(uri.host)!!.values as MutableList<Cookie>
        }
        mLock!!.unlock()
        return null
    }

    fun getCookieName(cookie: Cookie): String {
        return cookie.name() + cookie.domain()
    }

    fun cookieIsExpired(uri: URI, cookie: Cookie): Boolean {
        val name = getCookieName(cookie)
        if (cookieStore!!.contains(uri.host) && cookieStore!!.get(uri.host)!!.contains(name)) {
            val lastCookie = cookieStore!!.get(uri.host)!!.get(name)
            val currentTime = System.currentTimeMillis()
            if (lastCookie!!.expiresAt() > currentTime) {
                return true
            } else false
        }
        return true
    }
}