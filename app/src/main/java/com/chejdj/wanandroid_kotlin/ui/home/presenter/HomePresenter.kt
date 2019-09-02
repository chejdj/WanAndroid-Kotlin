package com.chejdj.wanandroid_kotlin.ui.home.presenter

import android.util.Log
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.ui.home.contract.HomeContract
import com.chejdj.wanandroid_kotlin.ui.home.model.HomeModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomePresenter(private var view: HomeContract.View?) : HomeContract.Presenter {
    private val model: HomeContract.Model
    private val TAG: String = "HomePresenter"

    init {
        this.model = HomeModel()
    }

    fun start() {
        getBannerData()
        getArticlesData(0)
    }

    override fun getBannerData() {
        /*model.getBannerData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BaseRes<List<HomeBannerBean>>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: BaseRes<List<HomeBannerBean>>) {
                    view?.showBannerData(t.data)
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "getBannerData error: "+e.message)
                }

            })*/
        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { model.getBannerData().await() }
            if (result.errorCode == 0) {
                view?.showBannerData(result.data)
            }
        }
    }

    override fun getArticlesData(pageNum: Int) {
        model.getArticlesData(pageNum)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BaseRes<ArticleData>> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: BaseRes<ArticleData>) {
                    view?.showArticlesData(t.data)
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "getArticlesData error:" + e.message)
                }

            })
    }

    fun destory() {
        view = null
    }
}