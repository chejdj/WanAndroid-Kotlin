package com.chejdj.wanandroid_kotlin.ui.project.presenter

import android.util.Log
import com.chejdj.wanandroid_kotlin.commons.ERROR
import com.chejdj.wanandroid_kotlin.data.remote.executeResponse
import com.chejdj.wanandroid_kotlin.ui.base.BaseTagFragment
import com.chejdj.wanandroid_kotlin.ui.project.contract.ProjectContract
import com.chejdj.wanandroid_kotlin.ui.project.model.ProjectModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProjectPresenter(private val view: BaseTagFragment, private val scope: CoroutineScope) :
    ProjectContract.Presenter, CoroutineScope by scope {
    private val model = ProjectModel()
    private val cidNumbers = ArrayList<Int>()
    private val subTitles = ArrayList<String>()

    override fun getProjectTags() {
        launch {
            val result = withContext(Dispatchers.IO) { model.getProjectTags() }
            executeResponse(result, {
                result.data?.let {
                    for (item in it) {
                        cidNumbers.add(item.id)
                        subTitles.add(item.name)
                    }
                    view.showTags(subTitles, cidNumbers)
                }
            }, { Log.d(ERROR, "getProjectTags fail") })
        }
    }
}