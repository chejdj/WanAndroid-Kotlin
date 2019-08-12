package com.chejdj.wanandroid_kotlin.data.data

/**
 * Created by zhuyangyang on 2019-08-12
 */
class BaseRecBean<T> {
    var errorCode: Int = 0
    var errorMsg: String? = null
    var data: T? = null
}