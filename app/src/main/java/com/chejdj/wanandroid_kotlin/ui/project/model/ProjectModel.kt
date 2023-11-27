package com.chejdj.wanandroid_kotlin.ui.project.model

import com.chejdj.wanandroid_kotlin.data.DataManager
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean

class ProjectModel {
    fun getProjectTags(): BaseRes<List<PrimaryArticleDirectoryBean>?> {
        return DataManager.getProjectSorts()
    }
}