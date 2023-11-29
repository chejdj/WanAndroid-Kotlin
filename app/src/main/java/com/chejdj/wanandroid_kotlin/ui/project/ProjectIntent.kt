package com.chejdj.wanandroid_kotlin.ui.project

import com.chejdj.wanandroid_kotlin.base.IUiIntent

sealed class ProjectIntent : IUiIntent {
    object GetTags : ProjectIntent()
}