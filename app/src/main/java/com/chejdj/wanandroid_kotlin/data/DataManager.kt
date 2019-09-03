package com.chejdj.wanandroid_kotlin.data

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.HomeBannerBean
import com.chejdj.wanandroid_kotlin.data.bean.HotKeyBean
import com.chejdj.wanandroid_kotlin.data.bean.LoginBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.data.remote.HttpService
import kotlinx.coroutines.Deferred

/**
 * Created by zhuyangyang on 2019-08-12
 *
 */
object DataManager {
    fun login(username: String, password: String): Deferred<BaseRes<LoginBean>> {
        return HttpService.login(username, password)
    }

    fun getHomeBanner(): Deferred<BaseRes<List<HomeBannerBean>>> {
        return HttpService.getHomeBanner()
    }

    fun getHomeArticles(pageNum: Int): Deferred<BaseRes<ArticleData>> {
        return HttpService.getHomeArticles(pageNum)
    }

    fun getKnowledgeArchitecture(): Deferred<BaseRes<List<PrimaryArticleDirectoryBean>>> {
        return HttpService.getKnowledgeArchitecture()
    }

    fun getKonwledgeArchitectureDetialArticle(pageNum: Int, cid: Int): Deferred<BaseRes<ArticleData>> {
        return HttpService.getKnowledgeArchitectureDetailArticle(pageNum, cid)
    }

    fun getHotKeys(): Deferred<BaseRes<List<HotKeyBean>>> {
        return HttpService.getHotKeys()
    }

    fun getSearchResults(pageNum: Int, keywords: String): Deferred<BaseRes<ArticleData>> {
        return HttpService.getSearchResults(pageNum, keywords)
    }

    fun getCollectedArticle(pageNum: Int): Deferred<BaseRes<ArticleData>> {
        return HttpService.getCollectedArticle(pageNum)
    }

    fun collectArticle(articleId: Int): Deferred<BaseRes<ArticleData>> {
        return HttpService.collectArticle(articleId)
    }

    fun getWechatArticleLists(): Deferred<BaseRes<List<PrimaryArticleDirectoryBean>>> {
        return HttpService.getWechatArticleLists()
    }

    fun getWechatChapterArticles(cid: Int, pageNum: Int): Deferred<BaseRes<ArticleData>> {
        return HttpService.getWechatChapterArticles(cid, pageNum)
    }

    fun getProjectSorts(): Deferred<BaseRes<List<PrimaryArticleDirectoryBean>>> {
        return HttpService.getProjectSorts()
    }

    fun getProjectData(pageNum: Int, cid: Int): Deferred<BaseRes<ArticleData>> {
        return HttpService.getProjectData(pageNum, cid)
    }

}