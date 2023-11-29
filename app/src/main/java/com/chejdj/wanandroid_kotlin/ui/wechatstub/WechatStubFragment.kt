package com.chejdj.wanandroid_kotlin.ui.wechatstub

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.base.BaseTagFragment
import com.chejdj.wanandroid_kotlin.ui.wechatstub.viewmodel.WechatViewModel
import kotlinx.coroutines.launch

class WechatStubFragment : BaseTagFragment() {
    override fun loadData() {
        val viewModel = ViewModelProvider(this)[WechatViewModel::class.java]
        viewModel.sendUiIntent(WechatIntent.GetChapters)
    }

    override fun initView() {
        title.setText(R.string.menu_wechat_sub)
        type = 0
        val viewModel = ViewModelProvider(this)[WechatViewModel::class.java]
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiStateFlow.collect {
                    showTags(it.data)
                }
            }
        }

    }
}