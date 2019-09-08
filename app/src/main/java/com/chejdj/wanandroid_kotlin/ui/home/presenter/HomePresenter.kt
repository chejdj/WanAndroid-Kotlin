package com.chejdj.wanandroid_kotlin.ui.home.presenter

import com.chejdj.wanandroid_kotlin.ui.home.contract.HomeContract
import com.chejdj.wanandroid_kotlin.ui.home.model.HomeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomePresenter(private var view: HomeContract.View?) : HomeContract.Presenter {
    private val model: HomeContract.Model

    init {
        this.model = HomeModel()
    }

    fun start() {
        getBannerData()
        getArticlesData(0)
    }

    override fun getBannerData() {
        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { model.getBannerData().await() }
            if (result.errorCode == 0) {
                view?.showBannerData(result.data)
            }
        }
    }

    override fun getArticlesData(pageNum: Int) {

        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { model.getArticlesData(pageNum).await() }
            if (result.errorCode == 0) {
                view?.showArticlesData(result.data)
            }
        }
    }

    fun destory() {
        view = null
    }
}