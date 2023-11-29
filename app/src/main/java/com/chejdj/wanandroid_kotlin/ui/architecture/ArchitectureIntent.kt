package com.chejdj.wanandroid_kotlin.ui.architecture

import com.chejdj.wanandroid_kotlin.base.IUiIntent

sealed class ArchitectureIntent : IUiIntent {
    object GetArchitecture : ArchitectureIntent()
}