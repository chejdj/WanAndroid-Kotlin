package com.chejdj.wanandroid_kotlin.ui.architecturedetail

import android.content.Context
import android.content.Intent
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.SecondaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.ui.base.BaseActivity

/**
 * 知识体系下面的二级目录显示的文章
 */
class ArchitectureDetailActivity : BaseActivity() {
    private var title: String = ""
    lateinit var data: ArrayList<SecondaryArticleDirectoryBean>

    override fun getLayoutId(): Int {
        return R.layout.activity_architecture_detail
    }

    override fun initView() {
        title = intent.getStringExtra(TITLE).toString()
        data = intent.getParcelableArrayListExtra(SECOND_DIRECTORY)!!
        val architectureDetailFragment = ArchitectureDetailFragment()
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().add(R.id.frameLayout, architectureDetailFragment)
            .show(architectureDetailFragment).commit()
    }

    fun getToolbarTitle(): String {
        return title
    }

    fun getSecondaryArticles(): ArrayList<SecondaryArticleDirectoryBean> {
        return data
    }


    companion object {
        private const val TITLE: String = "title"
        private const val SECOND_DIRECTORY = "second_directory"
        fun launch(
            context: Context,
            title: String,
            data: ArrayList<SecondaryArticleDirectoryBean>
        ) {
            val intent = Intent(context, ArchitectureDetailActivity::class.java)
            intent.putExtra(TITLE, title)
            intent.putParcelableArrayListExtra(SECOND_DIRECTORY, data)
            context.startActivity(intent)
        }
    }

}