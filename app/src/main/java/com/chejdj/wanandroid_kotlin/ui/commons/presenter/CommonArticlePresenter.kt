package com.chejdj.wanandroid_kotlin.ui.commons.presenter

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.ui.commons.contract.CommonArticleContract
import com.chejdj.wanandroid_kotlin.ui.commons.model.CommonArticleModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CommonArticlePresenter(view: CommonArticleContract.View) : CommonArticleContract.Presenter {

    private var view: CommonArticleContract.View? = view
    private val model: CommonArticleContract.Model = CommonArticleModel()

    override fun getArticleData(cid: Int, pageCount: Int, type: Int) {
        model.getArticleData(cid, pageCount, type).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BaseRes<ArticleData>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: BaseRes<ArticleData>) {
                    if (t.errorCode <= 0 && t.data!!.datas != null) {
                        view?.showArticleDatas(t.data!!)
                    }
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    override fun releaseResource() {
        view = null
    }

}