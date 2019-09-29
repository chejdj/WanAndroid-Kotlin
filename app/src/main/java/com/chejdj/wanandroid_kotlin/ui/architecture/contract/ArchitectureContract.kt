package com.chejdj.wanandroid_kotlin.ui.architecture.contract

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import kotlinx.coroutines.Deferred

/**
 * Created by zhuyangyang on 2019-08-20
 */
interface ArchitectureContract {
    interface View {
        fun showArchitectureData(data: List<PrimaryArticleDirectoryBean>)
    }

    interface Presenter {
        fun getArchitectureData()
    }

    interface Model {
        suspend fun getArchitectureData(): BaseRes<List<PrimaryArticleDirectoryBean>>
    }
}