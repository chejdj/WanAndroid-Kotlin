package com.chejdj.wanandroid_kotlin.ui.me

import com.chejdj.wanandroid_kotlin.base.IUiIntent

sealed class MeIntent : IUiIntent {
    data class GetCollect(val pageNum: Int) : MeIntent()
}