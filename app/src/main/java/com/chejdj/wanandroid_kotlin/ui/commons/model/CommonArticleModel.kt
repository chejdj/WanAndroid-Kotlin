package com.chejdj.wanandroid_kotlin.ui.commons.model

import com.chejdj.wanandroid_kotlin.data.DataManager
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData

// 0 代表了 微信公众号
// 1 代表了 项目中文章
class CommonArticleModel {
     fun getArticleData(cid: Int, pageNum: Int, type: Int): BaseRes<ArticleData?> {
        return when (type) {
            0 -> DataManager.getWechatChapterArticles(cid, pageNum)
            else ->
                DataManager.getProjectData(pageNum, cid)
        }
    }
}