package com.chejdj.wanandroid_kotlin.ui.project.contract

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import io.reactivex.Observable

interface ProjectContract {
    interface Presenter {
        fun getProjectTags()
    }

    interface Model {
        fun getProjectTags(): Observable<BaseRes<List<PrimaryArticleDirectoryBean>>>
    }
}