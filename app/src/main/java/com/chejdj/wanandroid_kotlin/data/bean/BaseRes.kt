package com.chejdj.wanandroid_kotlin.data.bean

/**
 * Created by zhuyangyang on 2019-08-12
 */
class BaseRes<T> {
    var errorCode: Int = -1
    var errorMsg: String = ""
    var data: T? = null
}