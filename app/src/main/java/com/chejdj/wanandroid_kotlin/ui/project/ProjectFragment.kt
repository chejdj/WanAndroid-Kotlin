package com.chejdj.wanandroid_kotlin.ui.project

import androidx.lifecycle.ViewModelProvider
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.ui.base.BaseTagFragment
import com.chejdj.wanandroid_kotlin.ui.project.viewmodel.ProjectViewModel

class ProjectFragment : BaseTagFragment() {
    override fun loadData() {
        val viewModel = ViewModelProvider(this)[ProjectViewModel::class.java]
        viewModel.getProjectTags()
        viewModel.tags.observe(this) {
            showTags(it)
        }
    }

    override fun initView() {
        type = 1
        title.setText(R.string.menu_project)
    }

}