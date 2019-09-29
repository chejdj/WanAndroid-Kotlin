package com.chejdj.wanandroid_kotlin.ui.home.presenter

import android.util.Log
import com.chejdj.wanandroid_kotlin.commons.ERROR
import com.chejdj.wanandroid_kotlin.data.remote.executeResponse
import com.chejdj.wanandroid_kotlin.ui.home.contract.HomeContract
import com.chejdj.wanandroid_kotlin.ui.home.model.HomeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomePresenter(private var view: HomeContract.View?, private val scope: CoroutineScope) :
    HomeContract.Presenter, CoroutineScope by scope {
    private val model: HomeContract.Model

    init {
        this.model = HomeModel()
    }

    fun start() {
        getBannerData()
        getArticlesData(0)
    }

    override fun getBannerData() {
        launch {
            val result = withContext(Dispatchers.IO) { model.getBannerData() }
            executeResponse(result, {
                view?.showBannerData(result.data)
            }, { Log.d(ERROR, "getBannerData fail") })
        }
    }

    override fun getArticlesData(pageNum: Int) {

        launch {
            val result = withContext(Dispatchers.IO) { model.getArticlesData(pageNum) }
            executeResponse(result, {
                view?.showArticlesData(result.data)
            }, { Log.d(ERROR, "getArticlesData fail") })
        }
    }

    fun destory() {
        view = null
    }
}