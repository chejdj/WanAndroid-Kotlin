package com.chejdj.wanandroid_kotlin.ui.search.presenter

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.HotKeyBean
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.ui.search.contract.SearchContract
import com.chejdj.wanandroid_kotlin.ui.search.model.SearchModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SearchPresenter(view: SearchContract.View) : SearchContract.Presenter {
    private val view = view
    private val model = SearchModel()
    override fun getHotKeys() {
        model.getHotKeys().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BaseRes<List<HotKeyBean>>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: BaseRes<List<HotKeyBean>>) {
                    if (t.errorCode == 0 && t.data != null) {
                        view?.showHotKeys(t.data!!)
                    }
                }

                override fun onError(e: Throwable) {
                }

            })
    }

    override fun getSearchResults(key: String, pageNum: Int) {
        model.getSearchResults(key, pageNum)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BaseRes<ArticleData>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: BaseRes<ArticleData>) {
                    if (t.errorCode == 0 && t.data != null) {
                        view?.showSearchResults(t.data!!)
                    }
                }

                override fun onError(e: Throwable) {
                }

            })
    }
}