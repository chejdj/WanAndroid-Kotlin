package com.chejdj.wanandroid_kotlin.ui.project.model

import com.chejdj.wanandroid_kotlin.data.DataManager
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean

class ProjectModel {
    suspend fun getProjectTags(): BaseRes<List<PrimaryArticleDirectoryBean>?> {
        return DataManager.getProjectSorts()
    }
}