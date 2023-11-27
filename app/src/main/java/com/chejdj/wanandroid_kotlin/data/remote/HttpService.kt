package com.chejdj.wanandroid_kotlin.data.remote

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.HomeBannerBean
import com.chejdj.wanandroid_kotlin.data.bean.HotKeyBean
import com.chejdj.wanandroid_kotlin.data.bean.LoginBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.data.remote.api.ApiService
import com.chejdj.wanandroid_kotlin.data.remote.cookie.CookieManager
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
                .build()
        apiService = retrofit.create(ApiService::class.java)
    }


    fun login(username: String, password: String): BaseRes<LoginBean?> {
        return try {
            apiService.userLogin(username, password).execute().body() ?: BaseRes()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    fun getHomeBanner(): BaseRes<List<HomeBannerBean>?> {
        return try {
            apiService.getHomeBanner().execute().body() ?: BaseRes()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    fun getHomeArticles(pageNum: Int): BaseRes<ArticleData?> {
        return try {
            apiService.getHomeArticles(pageNum).execute().body() ?: BaseRes()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    fun getKnowledgeArchitecture(): BaseRes<List<PrimaryArticleDirectoryBean>?> {
        return try {
            apiService.getKnowledgeArchitecture().execute().body() ?: BaseRes()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    fun getKnowledgeArchitectureDetailArticle(
        pageNum: Int,
        cid: Int
    ): BaseRes<ArticleData?> {
        return try {
            apiService.getKonwledgeArchitectureDetailArticle(pageNum, cid).execute().body()
                ?: BaseRes()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    fun getHotKeys(): BaseRes<List<HotKeyBean>?> {
        return try {
            apiService.getHotKeys().execute().body() ?: BaseRes()
        } catch (e: Exception) {
            BaseRes()
        }
    }

     fun getSearchResults(pageNum: Int, keywords: String): BaseRes<ArticleData?> {
        return try {
            apiService.getSearchResults(pageNum, keywords).execute().body() ?: BaseRes()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    fun getCollectedArticle(pageNum: Int): BaseRes<ArticleData?> {
        return try {
            apiService.getCollectedArticle(pageNum).execute().body() ?: BaseRes()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    fun collectArticle(articleId: Int): BaseRes<ArticleData?> {
        return try {
            apiService.collectArticle(articleId).execute().body() ?: BaseRes()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    fun getWechatArticleLists(): BaseRes<List<PrimaryArticleDirectoryBean>?> {
        return try {
            apiService.getWechatArticleLists().execute().body() ?: BaseRes()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    fun getWechatChapterArticles(cid: Int, pageNum: Int): BaseRes<ArticleData?> {
        return try {
            apiService.getWechatChapterArticles(cid, pageNum).execute().body() ?: BaseRes()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    fun getProjectSorts(): BaseRes<List<PrimaryArticleDirectoryBean>?> {
        return try {
            apiService.getProjectSorts().execute().body() ?: BaseRes()
        } catch (e: Exception) {
            BaseRes()
        }
    }

    fun getProjectData(pageNum: Int, cid: Int): BaseRes<ArticleData?> {
        return try {
            apiService.getProjectData(pageNum, cid).execute().body() ?: BaseRes()
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