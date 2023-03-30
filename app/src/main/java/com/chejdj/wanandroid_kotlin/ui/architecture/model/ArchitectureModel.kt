package com.chejdj.wanandroid_kotlin.ui.architecture.model

import com.chejdj.wanandroid_kotlin.data.DataManager
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.ui.architecture.contract.ArchitectureContract

/**
 * Created by zhuyangyang on 2019-08-20
 */
class ArchitectureModel : ArchitectureContract.Model {
    override suspend fun getArchitectureData(): BaseRes<List<PrimaryArticleDirectoryBean>?> {
        return DataManager.getKnowledgeArchitecture()
    }
}