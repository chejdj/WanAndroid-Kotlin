package com.chejdj.wanandroid_kotlin.ui.architecture.presenter

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.ui.architecture.contract.ArchitectureContract
import com.chejdj.wanandroid_kotlin.ui.architecture.model.ArchitectureModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by zhuyangyang on 2019-08-20
 */
class ArchitecturePresenter(view: ArchitectureContract.View) : ArchitectureContract.Presenter {
    private val model: ArchitectureContract.Model

    init {
        model = ArchitectureModel()
    }

    override fun getArchitectureData() {
        model.getArchitectureData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Observer<BaseRes<List<PrimaryArticleDirectoryBean>>>{
                override fun onComplete() {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onSubscribe(d: Disposable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onNext(t: BaseRes<List<PrimaryArticleDirectoryBean>>) {
                }

                override fun onError(e: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })
    }
}