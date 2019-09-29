package com.chejdj.wanandroid_kotlin.ui.me.presenter

import android.util.Log
import com.chejdj.wanandroid_kotlin.commons.ERROR
import com.chejdj.wanandroid_kotlin.data.remote.executeResponse
import com.chejdj.wanandroid_kotlin.ui.me.contract.MeContract
import com.chejdj.wanandroid_kotlin.ui.me.model.MeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MePresenter(private val view: MeContract.View, private val scope: CoroutineScope) :
    MeContract.Presenter, CoroutineScope by scope {
    private val model = MeModel()
    override fun getCollectArticles(pageNum: Int) {
        launch {
            val result = withContext(Dispatchers.IO) { model.getCollectArticles(pageNum) }
            executeResponse(result, {
                result.data?.let {
                    view.showCollectArticles(it)
                }
            }, { Log.d(ERROR, "getCollectArticles fail") })
        }
    }
}