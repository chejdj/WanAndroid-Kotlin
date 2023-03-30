package com.chejdj.wanandroid_kotlin.ui.project.contract

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean

interface ProjectContract {
    interface Presenter {
        fun getProjectTags()
    }

    interface Model {
        suspend fun getProjectTags(): BaseRes<List<PrimaryArticleDirectoryBean>?>
    }
}