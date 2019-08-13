package com.chejdj.wanandroid_kotlin.data.remote.cookie

import android.content.Context
import android.content.SharedPreferences
import okhttp3.Cookie
import java.io.ByteArrayInputStream
import java.io.ObjectInputStream
import java.util.*
import kotlin.collections.HashMap

/**
 * Created by zhuyangyang on 2019-08-13
 */
class DiskCookieStore(context: Context) {
    val FILE_NAME: String = "cookies"
    var cookieStore: HashMap<String, Cookie>? = null
    var context: Context? = null

    init {
        this.cookieStore = HashMap()
        this.context = context
    }

    fun loadFromDisk() {
        val preferenceFile: SharedPreferences? = context?.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val preferenceData: Map<String, String> = preferenceFile?.all as Map<String, String>
        for ((url, token) in preferenceData) {
            if (!(cookieStore?.contains(url)!!)) {

            }
        }

    }

    fun decodeCookie(cookieString: String): Cookie {
        val bytes: ByteArray = hexStringToByteArray(cookieString)
        val byteArrayInputStream: ByteArrayInputStream = ByteArrayInputStream(bytes)
        var cookie: Cookie? = null
        val objectInputStream: ObjectInputStream = ObjectInputStream(byteArrayInputStream)
        cookie = ((objectInputStream.readObject()) as DiskCookie).getCookie()
        return cookie
    }


    //字节数组转String
    private fun byteArrayToHexString(bytes: ByteArray): String {
        val sb = StringBuilder(bytes.size * 2)
        for (element in bytes) {
            val v = element.toInt() and 0xff
            if (v < 16) {
                sb.append('0')
            }
            sb.append(Integer.toHexString(v))
        }

        return sb.toString().toUpperCase(Locale.US)
    }

    //String转成字节数组
    private fun hexStringToByteArray(hexString: String): ByteArray {
        val len: Int = hexString.length
        var data = ByteArray(len / 2)
        for (i in 0 until len step 2) {
            data[i / 2] = ((Character.digit(hexString[i], 16)).shl(4) + Character.digit(hexString[i + 1], 16)).toByte()
        }
        return data
    }
}