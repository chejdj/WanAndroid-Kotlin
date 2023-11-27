package com.chejdj.wanandroid_kotlin.ui.me

import android.app.AlertDialog
import android.content.Intent
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.OnClick
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.account.AccountManager
import com.chejdj.wanandroid_kotlin.data.bean.article.Article
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.ui.base.BaseFragment
import com.chejdj.wanandroid_kotlin.ui.commons.adapter.CommonArticleAdapter
import com.chejdj.wanandroid_kotlin.ui.login.LoginActivity
import com.chejdj.wanandroid_kotlin.ui.me.viewmodel.MeViewModel
import com.chejdj.wanandroid_kotlin.utils.StringUtils
import com.google.android.material.appbar.CollapsingToolbarLayout

class MeFragment : BaseFragment() {
    @BindView(R.id.toolbar)
    lateinit var toolbar: CollapsingToolbarLayout

    @BindView(R.id.scrollView)
    lateinit var scrollView: NestedScrollView

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView
    private var currentPage = 0
    private var totalPage = 0
    private val articleData = ArrayList<Article>()
    private lateinit var adapter: CommonArticleAdapter
    private var viewModel: MeViewModel? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_me
    }

    override fun initView() {
        if (AccountManager.isLogin) {
            hasLogin()
        } else {
            scrollView.visibility = View.VISIBLE
        }
    }

    override fun onUserVisible() {
        super.onUserVisible()
        if (AccountManager.isLogin) {
            hasLogin()
        }
    }

    private fun hasLogin() {
        if (scrollView.visibility == View.VISIBLE) {
            scrollView.visibility = View.GONE
        }
        toolbar.title = AccountManager.accountName

        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        adapter = CommonArticleAdapter(articleData)
        adapter.animationEnable = true
        adapter.loadMoreModule.setOnLoadMoreListener {
            currentPage++
            if (currentPage < totalPage) {
                viewModel?.getCollectArticles(currentPage)
            } else {
                adapter.loadMoreModule.loadMoreEnd()
            }
        }

        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this)[MeViewModel::class.java]
        viewModel?.getCollectArticles(currentPage)
        viewModel?.collectArticle?.observe(this) {
            showCollectArticles(it)
        }
    }

    @OnClick(R.id.picture)
    fun intentToLoginActivity() {
        if (!AccountManager.isLogin) {
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        } else {
            val dialog =
                AlertDialog.Builder(context, R.style.Theme_MaterialComponents_Light_Dialog_Alert)
                    .setCancelable(true)
                    .setNegativeButton("OK") { dialog, _ ->
                        dialog?.dismiss()
                        toolbar.title = "Login"
                        scrollView.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                        AccountManager.clearAccount()
                    }
                    .setPositiveButton("No,No,No") { dialog, _ ->
                        dialog?.dismiss()
                    }.setMessage(StringUtils.getString(R.string.logout_warning)).create()
            dialog.show()
        }
    }

    private fun showCollectArticles(data: ArticleData) {
        if (data.datas !== null) {
            if (scrollView.visibility == View.VISIBLE) {
                scrollView.visibility = View.GONE
            }
            if (recyclerView.visibility == View.GONE) {
                recyclerView.visibility = View.VISIBLE
            }
            if (data.curPage == 0) {
                articleData.clear()
            }
            if (totalPage == 0) {
                totalPage = data.pageCount
            }
            currentPage = data.curPage
            adapter.addData(data.datas!!)
            adapter.loadMoreModule.loadMoreComplete()
        }
    }
}