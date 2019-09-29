package com.chejdj.wanandroid_kotlin.ui.wechatstub.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.chejdj.wanandroid_kotlin.commons.ERROR
import com.chejdj.wanandroid_kotlin.data.remote.executeResponse
import com.chejdj.wanandroid_kotlin.ui.base.BaseTagFragment
import com.chejdj.wanandroid_kotlin.ui.wechatstub.contract.WechatStubContract
import com.chejdj.wanandroid_kotlin.ui.wechatstub.model.WechatStubModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WechatStubPresenter(private var view: BaseTagFragment, private val scope: CoroutineScope) :
    WechatStubContract.Presenter, CoroutineScope by scope {
    private var model = WechatStubModel()
    private val cidNumbers = ArrayList<Int>()
    private val subTitles = ArrayList<String>()
    @SuppressLint("CheckResult")
    override fun getWechatChapters() {
        launch {
            val result = withContext(Dispatchers.IO) { model.getWechatChapters() }
            executeResponse(result, {
                result.data?.let {
                    for (item in it) {
                        cidNumbers.add(item.id)
                        subTitles.add(item.name)
                    }
                    view.showTags(subTitles, cidNumbers)
                }
            }, { Log.d(ERROR, "getWechatChapters fail") })
        }
    }

}