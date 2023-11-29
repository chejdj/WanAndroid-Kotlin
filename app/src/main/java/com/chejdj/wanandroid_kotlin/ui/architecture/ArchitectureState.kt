package com.chejdj.wanandroid_kotlin.ui.architecture

import com.chejdj.wanandroid_kotlin.base.IUiState
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean

data class ArchitectureState(val data: List<PrimaryArticleDirectoryBean>) : IUiState