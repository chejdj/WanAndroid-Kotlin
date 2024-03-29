package com.chejdj.wanandroid_kotlin.data.remote.api

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.HomeBannerBean
import com.chejdj.wanandroid_kotlin.data.bean.HotKeyBean
import com.chejdj.wanandroid_kotlin.data.bean.LoginBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by zhuyangyang on 2019-08-12
 */
interface ApiService {
    /**
     * 用户账号密码登陆
     * username: 账号名称
     * password: 密码
     */
    @POST("user/login")
    @FormUrlEncoded
    fun userLogin(
        @Field("username") username: String, @Field("password") password: String
    ): Call<BaseRes<LoginBean?>>

    /**
     *获取首页banner
     */
    @GET("banner/json")
    fun getHomeBanner(): Call<BaseRes<List<HomeBannerBean>?>>

    /**
     *获取首页文章列表
     * pageNum: 数据页数
     */
    @GET("article/list/{pageNum}/json")
    fun getHomeArticles(@Path("pageNum") pageNum: Int): Call<BaseRes<ArticleData?>>

    /**
     *获取知识体系信息
     */
    @GET("tree/json")
    fun getKnowledgeArchitecture(): Call<BaseRes<List<PrimaryArticleDirectoryBean>?>>

    /**
     *获取知识体系下文章详细文章
     * pageNum: 数据页数
     * cid: 二级目录的id
     */
    @GET("article/list/{pageNum}/json")
    fun getKonwledgeArchitectureDetailArticle(
        @Path("pageNum") pageNum: Int, @Query("cid") cid: Int
    ): Call<BaseRes<ArticleData?>>

    /**
     * 获取搜索热词
     */
    @GET("hotkey/json")
    fun getHotKeys(): Call<BaseRes<List<HotKeyBean>?>>

    /**
     * 搜索
     * pageNum: 数据页数
     * keywords: 关键字
     */
    @POST("article/query/{pageNum}/json")
    @FormUrlEncoded
    fun getSearchResults(
        @Path("pageNum") pageNum: Int, @Field("k") keywords: String
    ): Call<BaseRes<ArticleData?>>

    /**
     * 获取收藏的文章
     */
    @GET("lg/collect/list/{pageNum}/json")
    fun getCollectedArticle(@Path("pageNum") pageNum: Int): Call<BaseRes<ArticleData?>>

    /**
     * 收藏站内文章
     */
    @POST("lg/collect/{articleId}/json")
    fun collectArticle(@Path("articleId") articleId: Int): Call<BaseRes<ArticleData?>>

    /**
     * 获取微信公众号的列表
     */
    @GET("wxarticle/chapters/json")
    fun getWechatArticleLists(): Call<BaseRes<List<PrimaryArticleDirectoryBean>?>>

    /**
     * 查看微信公众号历史文章
     */
    @GET("wxarticle/list/{cid}/{pageNum}/json")
    fun getWechatChapterArticles(
        @Path("cid") cid: Int, @Path("pageNum") pageNum: Int
    ): Call<BaseRes<ArticleData?>>

    /**
     * 在某个公众号里面搜索历史文章
     */
    @GET("wxarticle/list/{cid}/{pageNum}/json")
    fun searhArticlesInWechat(
        @Path("cid") cid: Int, @Path("pageNum") pageNum: Int, @Query("k") keywords: String
    ): Call<BaseRes<ArticleData?>>

    /**
     * 获取项目分类列表
     */
    @GET("project/tree/json")
    fun getProjectSorts(): Call<BaseRes<List<PrimaryArticleDirectoryBean>?>>

    /**
     * 获取项目列表数据
     */
    @GET("project/list/{pageNum}/json")
    fun getProjectData(
        @Path("pageNum") pageNum: Int, @Query("cid") cid: Int
    ): Call<BaseRes<ArticleData?>>

}