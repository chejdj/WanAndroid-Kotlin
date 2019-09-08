package com.chejdj.wanandroid_kotlin.ui.me.presenter

import com.chejdj.wanandroid_kotlin.ui.me.contract.MeContract
import com.chejdj.wanandroid_kotlin.ui.me.model.MeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MePresenter(private val view: MeContract.View) : MeContract.Presenter {
    private val model = MeModel()
    override fun getCollectArtilces(pageNum: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { model.getCollectArticles(pageNum).await() }
            if (result.errorCode == 0) {
                result.data?.let {
                    view.showCollectArticles(it)
                }
            }
        }
    }
}