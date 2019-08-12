package com.chejdj.wanandroid_kotlin.data.remote.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by zhuyangyang on 2019-08-12
 */
interface ApiService {
    @POST("user/login")
    @FormUrlEncoded
    fun userLogin(@Field("username") username: String, @Field("password") password: String)

}