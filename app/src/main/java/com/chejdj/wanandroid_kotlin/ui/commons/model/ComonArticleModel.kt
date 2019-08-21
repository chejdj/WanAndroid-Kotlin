package com.chejdj.wanandroid_kotlin.ui.commons.model

import com.chejdj.wanandroid_kotlin.data.DataManager
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.ui.commons.contract.CommonArticleContract
import io.reactivex.Observable

// 0 代表了 微信公众号
// 1 代表了 项目中文章
class ComonArticleModel : CommonArticleContract.Model {
    override fun getArticleData(cid: Int, pageNum: Int, type: Int): Observable<BaseRes<ArticleData>> {
        when (type) {
            0 -> return DataManager.getWechatChapterArticles(cid, pageNum)
            else ->
                return DataManager.getProjectData(pageNum, cid)
        }
    }
}