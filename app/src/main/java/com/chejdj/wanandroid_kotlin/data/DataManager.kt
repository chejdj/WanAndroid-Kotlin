package com.chejdj.wanandroid_kotlin.data

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.HomeBannerBean
import com.chejdj.wanandroid_kotlin.data.bean.HotKeyBean
import com.chejdj.wanandroid_kotlin.data.bean.LoginBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.data.remote.HttpService
import io.reactivex.Observable

/**
 * Created by zhuyangyang on 2019-08-12
 *
 */
object DataManager {
    fun login(username: String, password: String): Observable<BaseRes<LoginBean>> {
        return HttpService.login(username, password)
    }

    fun getHomeBanner(): Observable<BaseRes<List<HomeBannerBean>>> {
        return HttpService.getHomeBanner()
    }

    fun getHomeArticles(pageNum: Int): Observable<BaseRes<ArticleData>> {
        return HttpService.getHomeArticles(pageNum)
    }

    fun getKnowledgeArchitecture(): Observable<BaseRes<List<PrimaryArticleDirectoryBean>>> {
        return HttpService.getKnowledgeArchitecture()
    }

    fun getKonwledgeArchitectureDetialArticle(pageNum: Int, cid: Int): Observable<BaseRes<ArticleData>> {
        return HttpService.getKonwledgeArchitectureDetialArticle(pageNum, cid)
    }

    fun getHotKeys(): Observable<BaseRes<List<HotKeyBean>>> {
        return HttpService.getHotKeys()
    }

    fun getSearchResults(pageNum: Int, keywords: String): Observable<BaseRes<ArticleData>> {
        return HttpService.getSearchResults(pageNum, keywords)
    }

    fun getCollectedArticle(pageNum: Int): Observable<BaseRes<ArticleData>> {
        return HttpService.getCollectedArticle(pageNum)
    }

    fun collectArticle(articleId: Int): Observable<BaseRes<ArticleData>> {
        return HttpService.collectArticle(articleId)
    }

    fun getWechatArticleLists(): Observable<BaseRes<PrimaryArticleDirectoryBean>> {
        return HttpService.getWechatArticleLists()
    }

    fun getWechatChapterArticles(cid: Int, pageNum: Int): Observable<BaseRes<ArticleData>> {
        return HttpService.getWechatChapterArticles(cid, pageNum)
    }

    fun getProjectSorts(): Observable<BaseRes<PrimaryArticleDirectoryBean>> {
        return HttpService.getProjectSorts()
    }

    fun getProjectData(pageNum: Int, cid: Int): Observable<BaseRes<ArticleData>> {
        return HttpService.getProjectData(pageNum, cid)
    }

}