package com.chejdj.wanandroid_kotlin.data.remote.api

import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.HomeBannerBean
import com.chejdj.wanandroid_kotlin.data.bean.LoginBean
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by zhuyangyang on 2019-08-12
 */
interface ApiService {
    @POST("user/login")
    @FormUrlEncoded
    fun userLogin(@Field("username") username: String, @Field("password") password: String): Observable<BaseRes<LoginBean>>

    /**
     *获取首页banner
     */
    @GET("banner/json")
    fun getHomeBanner(): Observable<BaseRes<List<HomeBannerBean>>>

    /**
     *获取首页文章列表
     */
    @GET("article/list/{pageNum}/json")
    fun getHomeArticles(@Path("pageNum") pageNum: Int): Observable<BaseRes<ArticleData>>
}