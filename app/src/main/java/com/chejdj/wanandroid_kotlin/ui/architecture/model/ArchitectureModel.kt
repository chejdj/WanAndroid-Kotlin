package com.chejdj.wanandroid_kotlin.ui.architecture.model

import com.chejdj.wanandroid_kotlin.data.DataManager
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean

/**
 * Created by zhuyangyang on 2019-08-20
 */
class ArchitectureModel {
    suspend fun getArchitectureData(): BaseRes<List<PrimaryArticleDirectoryBean>?> {
        return DataManager.getKnowledgeArchitecture()
    }
}