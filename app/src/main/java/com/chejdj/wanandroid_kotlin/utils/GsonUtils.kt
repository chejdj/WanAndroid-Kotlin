package com.chejdj.wanandroid_kotlin.utils

import com.google.gson.Gson
import okhttp3.Cookie

/**
 * Created by zhuyangyang on 2019-08-14
 */
class GsonUtils {
    companion object {
        val gson: Gson = Gson()
        fun <T> fromJsonIgnoreException(json: String, classOfT: Class<T>): T? {
            try {
                return gson.fromJson(json, classOfT)
            } catch (ignore: Throwable) {
                return null
            }
        }
    }
}