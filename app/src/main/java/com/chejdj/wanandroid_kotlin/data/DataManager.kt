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
    fun login(username: String, password: String): BaseRes<LoginBean?> {
        return HttpService.login(username, password)
    }

    fun getHomeBanner(): BaseRes<List<HomeBannerBean>?> {
        return HttpService.getHomeBanner()
    }

    fun getHomeArticles(pageNum: Int): BaseRes<ArticleData?> {
        return HttpService.getHomeArticles(pageNum)
    }

    fun getKnowledgeArchitecture(): BaseRes<List<PrimaryArticleDirectoryBean>?> {
        return HttpService.getKnowledgeArchitecture()
    }

    fun getKonwledgeArchitectureDetialArticle(
        pageNum: Int,
        cid: Int
    ): BaseRes<ArticleData?> {
        return HttpService.getKnowledgeArchitectureDetailArticle(pageNum, cid)
    }

    fun getHotKeys(): BaseRes<List<HotKeyBean>?> {
        return HttpService.getHotKeys()
    }

    fun getSearchResults(pageNum: Int, keywords: String): BaseRes<ArticleData?> {
        return HttpService.getSearchResults(pageNum, keywords)
    }

    fun getCollectedArticle(pageNum: Int): BaseRes<ArticleData?> {
        return HttpService.getCollectedArticle(pageNum)
    }

    fun collectArticle(articleId: Int): BaseRes<ArticleData?> {
        return HttpService.collectArticle(articleId)
    }

    fun getWechatArticleLists(): BaseRes<List<PrimaryArticleDirectoryBean>?> {
        return HttpService.getWechatArticleLists()
    }

    fun getWechatChapterArticles(cid: Int, pageNum: Int): BaseRes<ArticleData?> {
        return HttpService.getWechatChapterArticles(cid, pageNum)
    }

    fun getProjectSorts(): BaseRes<List<PrimaryArticleDirectoryBean>?> {
        return HttpService.getProjectSorts()
    }

    fun getProjectData(pageNum: Int, cid: Int): BaseRes<ArticleData?> {
        return HttpService.getProjectData(pageNum, cid)
    }
}