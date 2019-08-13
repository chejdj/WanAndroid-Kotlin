package com.chejdj.wanandroid_kotlin.data.remote.cookie

/**
 * Created by zhuyangyang on 2019-08-13
 */
class DiskCookieStore {
    val FILE_NAME: String = "cookies"
    var cookieStore: HashMap<String, Map<String, String>>? = null

    constructor() {
        cookieStore = HashMap()
    }

    fun loadFromDisk() {


    }

}