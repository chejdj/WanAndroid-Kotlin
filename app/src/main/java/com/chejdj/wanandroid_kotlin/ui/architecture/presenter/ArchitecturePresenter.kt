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
    private val view: ArchitectureContract.View

    init {
        model = ArchitectureModel()
        this.view = view
    }

    override fun getArchitectureData() {
        model.getArchitectureData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BaseRes<List<PrimaryArticleDirectoryBean>>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: BaseRes<List<PrimaryArticleDirectoryBean>>) {
                    if (view !== null && t.data !== null) {
                        view.showArchitectureData(t.data!!)
                    }
                }

                override fun onError(e: Throwable) {
                }

            })
    }
}