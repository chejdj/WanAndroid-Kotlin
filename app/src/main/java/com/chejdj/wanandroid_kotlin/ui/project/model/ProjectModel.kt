package com.chejdj.wanandroid_kotlin.ui.project.model

import com.chejdj.wanandroid_kotlin.data.DataManager
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.ui.project.contract.ProjectContract
import kotlinx.coroutines.Deferred

class ProjectModel : ProjectContract.Model {
    override fun getProjectTags(): Deferred<BaseRes<List<PrimaryArticleDirectoryBean>>> {
        return DataManager.getProjectSorts()
    }
}