package com.chejdj.wanandroid_kotlin.ui.wechatstub.presenter

import android.annotation.SuppressLint
import com.chejdj.wanandroid_kotlin.ui.base.BaseTagFragment
import com.chejdj.wanandroid_kotlin.ui.wechatstub.contract.WechatStubContract
import com.chejdj.wanandroid_kotlin.ui.wechatstub.model.WechatStubModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WechatStubPresenter(view: BaseTagFragment) : WechatStubContract.Presenter {
    private var view = view
    private var model = WechatStubModel()
    private val cidNumbers = ArrayList<Int>()
    private val subTitles = ArrayList<String>()
    @SuppressLint("CheckResult")
    override fun getWechatChapters() {
        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { model.getWechatChapters().await() }
            if (result.errorCode == 0) {
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

}