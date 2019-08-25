package com.chejdj.wanandroid_kotlin.account

object AccountManager {
    var isLogin: Boolean = false
    var accountName: String? = null
    fun clearAccount(){
        isLogin=false
        accountName=null
    }
}