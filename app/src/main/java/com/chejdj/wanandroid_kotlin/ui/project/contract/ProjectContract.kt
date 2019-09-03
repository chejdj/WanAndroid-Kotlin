package com.chejdj.wanandroid_kotlin.ui.project.contract

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import kotlinx.coroutines.Deferred

interface ProjectContract {
    interface Presenter {
        fun getProjectTags()
    }

    interface Model {
        fun getProjectTags(): Deferred<BaseRes<List<PrimaryArticleDirectoryBean>>>
    }
}