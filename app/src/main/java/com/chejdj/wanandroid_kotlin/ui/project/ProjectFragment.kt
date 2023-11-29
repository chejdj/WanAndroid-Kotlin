package com.chejdj.wanandroid_kotlin.ui.project

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.base.BaseTagFragment
import com.chejdj.wanandroid_kotlin.ui.project.viewmodel.ProjectViewModel
import kotlinx.coroutines.launch

class ProjectFragment : BaseTagFragment() {
    override fun loadData() {
        val viewModel = ViewModelProvider(this)[ProjectViewModel::class.java]
        viewModel.sendUiIntent(ProjectIntent.GetTags)
    }

    override fun initView() {
        type = 1
        title.setText(R.string.menu_project)
        val viewModel = ViewModelProvider(this)[ProjectViewModel::class.java]
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiStateFlow.collect {
                    showTags(it.data)
                }
            }
        }
    }

}