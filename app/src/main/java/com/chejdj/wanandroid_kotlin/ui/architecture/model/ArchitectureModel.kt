package com.chejdj.wanandroid_kotlin.ui.architecture.model

import com.chejdj.wanandroid_kotlin.data.DataManager
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.ui.architecture.contract.ArchitectureContract
import io.reactivex.Observable

/**
 * Created by zhuyangyang on 2019-08-20
 */
class ArchitectureModel : ArchitectureContract.Model {
    override fun getArchitectureData(): Observable<BaseRes<List<PrimaryArticleDirectoryBean>>> {
        return DataManager.getKnowledgeArchitecture()
    }
}