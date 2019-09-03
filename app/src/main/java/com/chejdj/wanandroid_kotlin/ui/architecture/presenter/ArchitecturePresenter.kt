package com.chejdj.wanandroid_kotlin.ui.architecture.presenter

import com.chejdj.wanandroid_kotlin.ui.architecture.contract.ArchitectureContract
import com.chejdj.wanandroid_kotlin.ui.architecture.model.ArchitectureModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { model.getArchitectureData().await() }
            if (result.errorCode == 0) {
                result.data?.let { view.showArchitectureData(it) }
            }
        }
    }
}