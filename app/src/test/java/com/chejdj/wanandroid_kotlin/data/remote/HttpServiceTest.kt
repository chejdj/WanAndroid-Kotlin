package com.chejdj.wanandroid_kotlin.data.remote

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.HomeBannerBean
import com.chejdj.wanandroid_kotlin.data.bean.HotKeyBean
import com.chejdj.wanandroid_kotlin.data.bean.LoginBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.utils.GsonUtils
import io.reactivex.Observer
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Created by zhuyangyang on 2019-08-16
 */
@RunWith(RobolectricTestRunner::class)
class HttpServiceTest {
    @Before
    fun setUp() {
        RxJavaPlugins.reset()
        RxJavaPlugins.setIoSchedulerHandler {
            Schedulers.trampoline()
        }
        RxAndroidPlugins.reset()
        RxAndroidPlugins.setMainThreadSchedulerHandler {
            Schedulers.trampoline()
        }
    }

    @Test
    fun getHomeBanner() {
        HttpService.getHomeBanner().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<BaseRes<List<HomeBannerBean>>> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: BaseRes<List<HomeBannerBean>>) {
                    System.out.println("onNext : " + GsonUtils.gson.toJson(t))
                }

                override fun onError(e: Throwable) {
                    System.out.println("onError: " + e.message)
                }

            })
    }

    @Test
    fun getHomeArticles() {
        HttpService.getHomeArticles(0).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<BaseRes<ArticleData>> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: BaseRes<ArticleData>) {
                    System.out.println("onNext" + GsonUtils.gson.toJson(t))
                }

                override fun onError(e: Throwable) {
                    System.out.println("onError" + e.message)
                }

                override fun onComplete() {

                }

            })
    }

    @Test
    fun getKnowledgeArchitecture() {
        HttpService.getKnowledgeArchitecture().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BaseRes<List<PrimaryArticleDirectoryBean>>> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: BaseRes<List<PrimaryArticleDirectoryBean>>) {
                    System.out.println("onNext" + GsonUtils.gson.toJson(t))
                }

                override fun onError(e: Throwable) {
                    System.out.println("onError" + e.message)
                }


            })
    }

    @Test
    fun getKonwledgeArchitectureDetialArticle() {
        HttpService.getKonwledgeArchitectureDetialArticle(0, 60).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BaseRes<ArticleData>> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: BaseRes<ArticleData>) {
                    System.out.println("onNext" + GsonUtils.gson.toJson(t))
                }

                override fun onError(e: Throwable) {
                    System.out.println("onError" + e.message)
                }

            })

    }

    @Test
    fun getHotKeys() {
        HttpService.getHotKeys().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BaseRes<List<HotKeyBean>>> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: BaseRes<List<HotKeyBean>>) {
                    System.out.println("onNext" + GsonUtils.gson.toJson(t))
                }

                override fun onError(e: Throwable) {
                    System.out.println("onError" + e.message)
                }

            })
    }

    @Test
    fun getSearchResults() {

        HttpService.getSearchResults(0, "Android").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BaseRes<ArticleData>> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: BaseRes<ArticleData>) {
                    System.out.println("onNext" + GsonUtils.gson.toJson(t))
                }

                override fun onError(e: Throwable) {
                    System.out.println("onError" + e.message)
                }

            })
    }

    @Test
    fun getCollectedArticle() {
        HttpService.getCollectedArticle(0).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BaseRes<ArticleData>> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: BaseRes<ArticleData>) {
                    System.out.println("onNext" + GsonUtils.gson.toJson(t))
                }

                override fun onError(e: Throwable) {
                    System.out.println("onError" + e.message)
                }

            })

    }

    @Test
    fun collectArticle() {
        HttpService.collectArticle(8932).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BaseRes<ArticleData>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: BaseRes<ArticleData>) {
                    System.out.println("onNext" + GsonUtils.gson.toJson(t))
                }

                override fun onError(e: Throwable) {
                    System.out.println("onError" + e.message)
                }
            })

    }

    @Test
    fun login() {
        HttpService.login("chejdj", "zyy19961027").observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BaseRes<LoginBean>> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: BaseRes<LoginBean>) {
                    System.out.println("onNext" + GsonUtils.gson.toJson(t))
                }

                override fun onError(e: Throwable) {
                    System.out.println("onError" + e.message)
                }

                override fun onComplete() {
                }

            })
    }


}