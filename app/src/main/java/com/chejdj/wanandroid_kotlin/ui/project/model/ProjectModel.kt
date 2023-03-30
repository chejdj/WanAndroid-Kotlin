package com.chejdj.wanandroid_kotlin.ui.project.model

import com.chejdj.wanandroid_kotlin.data.DataManager
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.ui.project.contract.ProjectContract

class ProjectModel : ProjectContract.Model {
    override suspend fun getProjectTags(): BaseRes<List<PrimaryArticleDirectoryBean>?> {
        return DataManager.getProjectSorts()
    }
}