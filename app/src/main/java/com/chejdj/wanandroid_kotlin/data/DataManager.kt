package com.chejdj.wanandroid_kotlin.data

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.HomeBannerBean
import com.chejdj.wanandroid_kotlin.data.bean.HotKeyBean
import com.chejdj.wanandroid_kotlin.data.bean.LoginBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.data.remote.HttpService

/**
 * Created by zhuyangyang on 2019-08-12
 *
 */
object DataManager {
    suspend fun login(username: String, password: String): BaseRes<LoginBean?> {
        return HttpService.login(username, password)
    }

    suspend fun getHomeBanner(): BaseRes<List<HomeBannerBean>?> {
        return HttpService.getHomeBanner()
    }

    suspend fun getHomeArticles(pageNum: Int): BaseRes<ArticleData?> {
        return HttpService.getHomeArticles(pageNum)
    }

    suspend fun getKnowledgeArchitecture(): BaseRes<List<PrimaryArticleDirectoryBean>?> {
        return HttpService.getKnowledgeArchitecture()
    }

    suspend fun getHotKeys(): BaseRes<List<HotKeyBean>?> {
        return HttpService.getHotKeys()
    }

    suspend fun getSearchResults(pageNum: Int, keywords: String): BaseRes<ArticleData?> {
        return HttpService.getSearchResults(pageNum, keywords)
    }

    suspend fun getCollectedArticle(pageNum: Int): BaseRes<ArticleData?> {
        return HttpService.getCollectedArticle(pageNum)
    }

    fun collectArticle(articleId: Int): BaseRes<ArticleData?> {
        return HttpService.collectArticle(articleId)
    }

    suspend fun getWechatArticleLists(): BaseRes<List<PrimaryArticleDirectoryBean>?> {
        return HttpService.getWechatArticleLists()
    }

    suspend fun getWechatChapterArticles(cid: Int, pageNum: Int): BaseRes<ArticleData?> {
        return HttpService.getWechatChapterArticles(cid, pageNum)
    }

    suspend fun getProjectSorts(): BaseRes<List<PrimaryArticleDirectoryBean>?> {
        return HttpService.getProjectSorts()
    }

    suspend fun getProjectData(pageNum: Int, cid: Int): BaseRes<ArticleData?> {
        return HttpService.getProjectData(pageNum, cid)
    }
}