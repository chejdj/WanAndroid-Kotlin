package com.chejdj.wanandroid_kotlin.ui.commons.presenter

import android.util.Log
import com.chejdj.wanandroid_kotlin.commons.ERROR
import com.chejdj.wanandroid_kotlin.data.remote.executeResponse
import com.chejdj.wanandroid_kotlin.ui.commons.contract.CommonArticleContract
import com.chejdj.wanandroid_kotlin.ui.commons.model.CommonArticleModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CommonArticlePresenter(view: CommonArticleContract.View, private val scope: CoroutineScope) :
    CommonArticleContract.Presenter, CoroutineScope by scope {

    private var view: CommonArticleContract.View? = view
    private val model: CommonArticleContract.Model = CommonArticleModel()

    override fun getArticleData(cid: Int, pageCount: Int, type: Int) {
        launch {
            val result = withContext(Dispatchers.IO) { model.getArticleData(cid, pageCount, type) }
            executeResponse(result, {
                result.data?.let {
                    view?.showArticleDatas(it)
                }
            }, { Log.d(ERROR, "getArticleData fail") })
        }
    }

    override fun releaseResource() {
        view = null
    }

}