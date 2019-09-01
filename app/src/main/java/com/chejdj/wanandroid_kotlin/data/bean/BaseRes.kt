package com.chejdj.wanandroid_kotlin.data.bean

/**
 * Created by zhuyangyang on 2019-08-12
 */
class BaseRes<T> {
    var errorCode: Int = 0
    var errorMsg: String = ""
    var data: T? = null
}