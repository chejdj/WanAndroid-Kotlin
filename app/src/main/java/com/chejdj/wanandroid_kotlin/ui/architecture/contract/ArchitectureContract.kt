package com.chejdj.wanandroid_kotlin.ui.architecture.contract

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import io.reactivex.Observable

/**
 * Created by zhuyangyang on 2019-08-20
 */
interface ArchitectureContract {
    interface View {
        fun showArchitectureData()
    }

    interface Presenter {
        fun getArchitectureData()
    }

    interface Model {
        fun getArchitectureData(): Observable<BaseRes<List<PrimaryArticleDirectoryBean>>>
    }
}