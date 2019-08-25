package com.chejdj.wanandroid_kotlin.ui.me.model

import com.chejdj.wanandroid_kotlin.data.DataManager
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.ui.me.contract.MeContract
import io.reactivex.Observable

class MeModel : MeContract.Model {
    override fun getCollectArticles(pageNum: Int): Observable<BaseRes<ArticleData>> {
        return DataManager.getCollectedArticle(pageNum)
    }
}