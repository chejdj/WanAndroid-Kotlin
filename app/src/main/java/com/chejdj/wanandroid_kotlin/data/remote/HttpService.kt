package com.chejdj.wanandroid_kotlin.data.remote

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.HomeBannerBean
import com.chejdj.wanandroid_kotlin.data.bean.HotKeyBean
import com.chejdj.wanandroid_kotlin.data.bean.LoginBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.data.remote.api.ApiService
import com.chejdj.wanandroid_kotlin.data.remote.cookie.CookieManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by zhuyangyang on 2019-08-12
 */
object HttpService {
    private val apiService: ApiService
    private const val baseUrl: String = "https://www.wanandroid.com"

    init {
        val okHttpClient: OkHttpClient =
            OkHttpClient.Builder().connectTimeout(3, TimeUnit.SECONDS).cookieJar(CookieManager()).build()
        val retrofit: Retrofit =
            Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
        apiService = retrofit.create(ApiService::class.java)
    }


    fun login(username: String, password: String): Deferred<BaseRes<LoginBean>> {
        return apiService.userLogin(username, password)
    }

    fun getHomeBanner(): Deferred<BaseRes<List<HomeBannerBean>>> {
        return apiService.getHomeBanner()
    }

    fun getHomeArticles(pageNum: Int): Deferred<BaseRes<ArticleData>> {
        return apiService.getHomeArticles(pageNum)
    }

    fun getKnowledgeArchitecture(): Deferred<BaseRes<List<PrimaryArticleDirectoryBean>>> {
        return apiService.getKnowledgeArchitecture()
    }

    fun getKnowledgeArchitectureDetailArticle(pageNum: Int, cid: Int): Deferred<BaseRes<ArticleData>> {
        return apiService.getKonwledgeArchitectureDetailArticle(pageNum, cid)
    }

    fun getHotKeys(): Deferred<BaseRes<List<HotKeyBean>>> {
        return apiService.getHotKeys()
    }

    fun getSearchResults(pageNum: Int, keywords: String): Deferred<BaseRes<ArticleData>> {
        return apiService.getSearchResults(pageNum, keywords)
    }

    fun getCollectedArticle(pageNum: Int): Deferred<BaseRes<ArticleData>> {
        return apiService.getCollectedArticle(pageNum)
    }

    fun collectArticle(articleId: Int): Deferred<BaseRes<ArticleData>> {
        return apiService.collectArticle(articleId)
    }

    fun getWechatArticleLists(): Deferred<BaseRes<List<PrimaryArticleDirectoryBean>>> {
        return apiService.getWechatArticleLists()
    }

    fun getWechatChapterArticles(cid: Int, pageNum: Int): Deferred<BaseRes<ArticleData>> {
        return apiService.getWechatChapterArticles(cid, pageNum)
    }

    fun getProjectSorts(): Deferred<BaseRes<List<PrimaryArticleDirectoryBean>>> {
        return apiService.getProjectSorts()
    }

    fun getProjectData(pageNum: Int, cid: Int): Deferred<BaseRes<ArticleData>> {
        return apiService.getProjectData(pageNum, cid)
    }

}