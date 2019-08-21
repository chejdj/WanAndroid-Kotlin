package com.chejdj.wanandroid_kotlin.data.remote

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.HomeBannerBean
import com.chejdj.wanandroid_kotlin.data.bean.HotKeyBean
import com.chejdj.wanandroid_kotlin.data.bean.LoginBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.data.remote.api.ApiService
import com.chejdj.wanandroid_kotlin.data.remote.cookie.CookieManager
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * Created by zhuyangyang on 2019-08-12
 */
object HttpService {
    private var apiService: ApiService? = null
    private val baseUrl: String = "https://www.wanandroid.com"

    init {
        val okHttpClient: OkHttpClient =
            OkHttpClient.Builder().connectTimeout(3, TimeUnit.SECONDS).cookieJar(CookieManager()).build()
        val retrofit: Retrofit =
            Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        apiService = retrofit.create(ApiService::class.java)
    }


    fun login(username: String, password: String): Observable<BaseRes<LoginBean>> {
        return apiService!!.userLogin(username, password)
    }

    fun getHomeBanner(): Observable<BaseRes<List<HomeBannerBean>>> {
        return apiService!!.getHomeBanner()
    }

    fun getHomeArticles(pageNum: Int): Observable<BaseRes<ArticleData>> {
        return apiService!!.getHomeArticles(pageNum)
    }

    fun getKnowledgeArchitecture(): Observable<BaseRes<List<PrimaryArticleDirectoryBean>>> {
        return apiService!!.getKnowledgeArchitecture()
    }

    fun getKonwledgeArchitectureDetialArticle(pageNum: Int, cid: Int): Observable<BaseRes<ArticleData>> {
        return apiService!!.getKonwledgeArchitectureDetialArticle(pageNum, cid)
    }

    fun getHotKeys(): Observable<BaseRes<List<HotKeyBean>>> {
        return apiService!!.getHotKeys()
    }

    fun getSearchResults(pageNum: Int, keywords: String): Observable<BaseRes<ArticleData>> {
        return apiService!!.getSearchResults(pageNum, keywords)
    }

    fun getCollectedArticle(pageNum: Int): Observable<BaseRes<ArticleData>> {
        return apiService!!.getCollectedArticle(pageNum)
    }

    fun collectArticle(articleId: Int): Observable<BaseRes<ArticleData>> {
        return apiService!!.collectArticle(articleId)
    }

    fun getWechatArticleLists(): Observable<BaseRes<PrimaryArticleDirectoryBean>> {
        return apiService!!.getWechatArticleLists()
    }

    fun getWechatChapterArticles(cid: Int, pageNum: Int): Observable<BaseRes<ArticleData>> {
        return apiService!!.getWechatChapterArticles(cid, pageNum)
    }

    fun getProjectSorts(): Observable<BaseRes<PrimaryArticleDirectoryBean>> {
        return apiService!!.getProjectSorts()
    }

    fun getProjectData(pageNum: Int, cid: Int): Observable<BaseRes<ArticleData>> {
        return apiService!!.getProjectData(pageNum, cid)
    }

}