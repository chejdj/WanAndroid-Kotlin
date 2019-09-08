package com.chejdj.wanandroid_kotlin.ui.project.presenter

import com.chejdj.wanandroid_kotlin.ui.base.BaseTagFragment
import com.chejdj.wanandroid_kotlin.ui.project.contract.ProjectContract
import com.chejdj.wanandroid_kotlin.ui.project.model.ProjectModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProjectPresenter(private val view: BaseTagFragment) : ProjectContract.Presenter {
    private val model = ProjectModel()
    private val cidNumbers = ArrayList<Int>()
    private val subTitles = ArrayList<String>()

    override fun getProjectTags() {
        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { model.getProjectTags().await() }
            result.data?.let {
                for (item in it) {
                    cidNumbers.add(item.id)
                    subTitles.add(item.name)
                }
                view.showTags(subTitles, cidNumbers)
            }
        }
    }
}