package com.chejdj.wanandroid_kotlin.ui.wechatstub.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.ui.base.BaseTagFragment
import com.chejdj.wanandroid_kotlin.ui.wechatstub.contract.WechatStubContract
import com.chejdj.wanandroid_kotlin.ui.wechatstub.model.WechatStubModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class WechatStubPresenter(view: BaseTagFragment) : WechatStubContract.Presenter {
    private var view = view
    private var model = WechatStubModel()
    private val cidNumbers = ArrayList<Int>()
    private val subTitles = ArrayList<String>()
    @SuppressLint("CheckResult")
    override fun getWechatChapters() {
        model.getWechatChapters().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BaseRes<List<PrimaryArticleDirectoryBean>>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: BaseRes<List<PrimaryArticleDirectoryBean>>) {
                    if (view != null) {
                        for (item in t.data!!) {
                            cidNumbers.add(item.id)
                            subTitles.add(item.name!!)
                        }
                        view.showTags(subTitles, cidNumbers)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.e(this.javaClass.simpleName, e.message)
                }

            })
    }

}