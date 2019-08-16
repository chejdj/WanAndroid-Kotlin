package com.chejdj.wanandroid_kotlin.data.remote

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.HomeBannerBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
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
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onNext(t: BaseRes<ArticleData>) {
                    System.out.println("onNext" + GsonUtils.gson.toJson(t))
                }

                override fun onError(e: Throwable) {
                    System.out.println("onError" + e.message)
                }

                override fun onComplete() {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })
    }

    @Test
    fun getKnowledgeArchitecture() {
    }

    @Test
    fun getKonwledgeArchitectureDetialArticle() {
    }

    @Test
    fun getHotKeys() {
    }

    @Test
    fun getSearchResults() {
    }

    @Test
    fun getCollectedArticle() {
    }

    @Test
    fun collectArticle() {
    }
}