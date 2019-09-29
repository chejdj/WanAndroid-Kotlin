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
            OkHttpClient.Builder().connectTimeout(3, TimeUnit.SECONDS).cookieJar(CookieManager())
                .build()
        val retrofit: Retrofit =
            Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
        apiService = retrofit.create(ApiService::class.java)
    }


    suspend fun login(username: String, password: String): BaseRes<LoginBean> {
        return apiService.userLogin(username, password).await()
    }

    suspend fun getHomeBanner(): BaseRes<List<HomeBannerBean>> {
        return apiService.getHomeBanner().await()
    }

    suspend fun getHomeArticles(pageNum: Int): BaseRes<ArticleData> {
        return apiService.getHomeArticles(pageNum).await()
    }

    suspend fun getKnowledgeArchitecture(): BaseRes<List<PrimaryArticleDirectoryBean>> {
        return apiService.getKnowledgeArchitecture().await()
    }

    suspend fun getKnowledgeArchitectureDetailArticle(
        pageNum: Int,
        cid: Int
    ): BaseRes<ArticleData> {
        return apiService.getKonwledgeArchitectureDetailArticle(pageNum, cid).await()
    }

    suspend fun getHotKeys(): BaseRes<List<HotKeyBean>> {
        return apiService.getHotKeys().await()
    }

    suspend fun getSearchResults(pageNum: Int, keywords: String): BaseRes<ArticleData> {
        return apiService.getSearchResults(pageNum, keywords).await()
    }

    suspend fun getCollectedArticle(pageNum: Int): BaseRes<ArticleData> {
        return apiService.getCollectedArticle(pageNum).await()
    }

    suspend fun collectArticle(articleId: Int): BaseRes<ArticleData> {
        return apiService.collectArticle(articleId).await()
    }

    suspend fun getWechatArticleLists(): BaseRes<List<PrimaryArticleDirectoryBean>> {
        return apiService.getWechatArticleLists().await()
    }

    suspend fun getWechatChapterArticles(cid: Int, pageNum: Int): BaseRes<ArticleData> {
        return apiService.getWechatChapterArticles(cid, pageNum).await()
    }

    suspend fun getProjectSorts(): BaseRes<List<PrimaryArticleDirectoryBean>> {
        return apiService.getProjectSorts().await()
    }

    suspend fun getProjectData(pageNum: Int, cid: Int): BaseRes<ArticleData> {
        return apiService.getProjectData(pageNum, cid).await()
    }
}

fun executeResponse(
    response: BaseRes<out Any>,
    successBlock: () -> Unit,
    errorBlock: () -> Unit
) {
    if (response.errorCode == 0) successBlock.invoke()
    else errorBlock.invoke()
}