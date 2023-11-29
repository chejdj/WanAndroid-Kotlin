package com.chejdj.wanandroid_kotlin.ui.wechatstub

import com.chejdj.wanandroid_kotlin.base.IUiIntent

sealed class WechatIntent : IUiIntent {
    object GetChapters : WechatIntent()
}