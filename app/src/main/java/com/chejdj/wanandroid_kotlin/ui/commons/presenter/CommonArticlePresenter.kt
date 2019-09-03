package com.chejdj.wanandroid_kotlin.ui.commons.presenter

import com.chejdj.wanandroid_kotlin.ui.commons.contract.CommonArticleContract
import com.chejdj.wanandroid_kotlin.ui.commons.model.CommonArticleModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CommonArticlePresenter(view: CommonArticleContract.View) : CommonArticleContract.Presenter {

    private var view: CommonArticleContract.View? = view
    private val model: CommonArticleContract.Model = CommonArticleModel()

    override fun getArticleData(cid: Int, pageCount: Int, type: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { model.getArticleData(cid, pageCount, type).await() }
            if (result.errorCode == 0) {
                result.data?.let {
                    view?.showArticleDatas(it)
                }
            }
        }
    }

    override fun releaseResource() {
        view = null
    }

}