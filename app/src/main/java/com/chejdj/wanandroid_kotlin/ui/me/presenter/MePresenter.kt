package com.chejdj.wanandroid_kotlin.ui.me.presenter

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.ui.me.contract.MeContract
import com.chejdj.wanandroid_kotlin.ui.me.model.MeModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MePresenter(view: MeContract.View) : MeContract.Presenter {
    private val view = view
    private val model = MeModel()
    override fun getCollectArtilces(pageNum: Int) {
        model.getCollectArticles(pageNum)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BaseRes<ArticleData>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: BaseRes<ArticleData>) {
                    if (view != null && t.data != null) {
                        view.showCollectArticles(t.data!!)
                    }
                }

                override fun onError(e: Throwable) {
                }
            })
    }
}