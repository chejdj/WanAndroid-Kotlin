package com.chejdj.wanandroid_kotlin.ui.architecture.presenter

import android.util.Log
import com.chejdj.wanandroid_kotlin.commons.ERROR
import com.chejdj.wanandroid_kotlin.data.remote.executeResponse
import com.chejdj.wanandroid_kotlin.ui.architecture.contract.ArchitectureContract
import com.chejdj.wanandroid_kotlin.ui.architecture.model.ArchitectureModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by zhuyangyang on 2019-08-20
 */
class ArchitecturePresenter(view: ArchitectureContract.View, private val scope: CoroutineScope) :
    ArchitectureContract.Presenter, CoroutineScope by scope {
    private val model: ArchitectureContract.Model
    private val view: ArchitectureContract.View

    init {
        model = ArchitectureModel()
        this.view = view
    }

    override fun getArchitectureData() {
        launch {
            val result = withContext(Dispatchers.IO) { model.getArchitectureData() }

            executeResponse(result, {
                result.data?.let { view.showArchitectureData(it) }
            }, { Log.d(ERROR, "getArchitectureData fail") })
        }
    }
}