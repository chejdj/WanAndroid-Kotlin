package com.chejdj.wanandroid_kotlin.ui.project.presenter

import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.ui.base.BaseTagFragment
import com.chejdj.wanandroid_kotlin.ui.project.contract.ProjectContract
import com.chejdj.wanandroid_kotlin.ui.project.model.ProjectModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProjectPresenter(view: BaseTagFragment) : ProjectContract.Presenter {
    private val view: BaseTagFragment = view
    private val model = ProjectModel()
    private val cidNumbers = ArrayList<Int>()
    private val subTitles = ArrayList<String>()

    override fun getProjectTags() {
        model.getProjectTags().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                object : Observer<BaseRes<List<PrimaryArticleDirectoryBean>>> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: BaseRes<List<PrimaryArticleDirectoryBean>>) {
                        if (view != null) {
                            for (item in t.data!!) {
                                cidNumbers.add(item.id)
                                subTitles.add(item.name)
                            }
                            view.showTags(subTitles, cidNumbers)
                        }
                    }

                    override fun onError(e: Throwable) {
                    }

                }
            )
    }
}