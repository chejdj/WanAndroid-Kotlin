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


    suspend fun login(username: String, password: String): BaseRes<LoginBean?> {
        return try {
            apiService.userLogin(username, password).await()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    suspend fun getHomeBanner(): BaseRes<List<HomeBannerBean>?> {
        return try {
            apiService.getHomeBanner().await()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    suspend fun getHomeArticles(pageNum: Int): BaseRes<ArticleData?> {
        return try {
            apiService.getHomeArticles(pageNum).await()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    suspend fun getKnowledgeArchitecture(): BaseRes<List<PrimaryArticleDirectoryBean>?> {
        return try {
            apiService.getKnowledgeArchitecture().await()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    suspend fun getKnowledgeArchitectureDetailArticle(
        pageNum: Int,
        cid: Int
    ): BaseRes<ArticleData?> {
        return try {
            apiService.getKonwledgeArchitectureDetailArticle(pageNum, cid).await()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    suspend fun getHotKeys(): BaseRes<List<HotKeyBean>?> {
        return try {
            apiService.getHotKeys().await()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    suspend fun getSearchResults(pageNum: Int, keywords: String): BaseRes<ArticleData?> {
        return try {
            apiService.getSearchResults(pageNum, keywords).await()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    suspend fun getCollectedArticle(pageNum: Int): BaseRes<ArticleData?> {
        return try {
            apiService.getCollectedArticle(pageNum).await()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    suspend fun collectArticle(articleId: Int): BaseRes<ArticleData?> {
        return try {
            apiService.collectArticle(articleId).await()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    suspend fun getWechatArticleLists(): BaseRes<List<PrimaryArticleDirectoryBean>?> {
        return try {
            apiService.getWechatArticleLists().await()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    suspend fun getWechatChapterArticles(cid: Int, pageNum: Int): BaseRes<ArticleData?> {
        return try {
            apiService.getWechatChapterArticles(cid, pageNum).await()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    suspend fun getProjectSorts(): BaseRes<List<PrimaryArticleDirectoryBean>?> {
        return try {
            apiService.getProjectSorts().await()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    suspend fun getProjectData(pageNum: Int, cid: Int): BaseRes<ArticleData?> {
        return try {
            apiService.getProjectData(pageNum, cid).await()
        } catch (e: Exception) {
            BaseRes()
        }
    }
}

fun executeResponse(
    response: BaseRes<out Any?>,
    successBlock: () -> Unit,
    errorBlock: () -> Unit
) {
    if (response.errorCode == 0) successBlock.invoke()
    else errorBlock.invoke()
}