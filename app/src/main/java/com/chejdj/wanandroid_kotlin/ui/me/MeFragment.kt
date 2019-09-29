package com.chejdj.wanandroid_kotlin.ui.me

import android.app.AlertDialog
import android.content.Intent
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import butterknife.OnClick
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.account.AccountManager
import com.chejdj.wanandroid_kotlin.data.bean.article.Article
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.events.LoginEvent
import com.chejdj.wanandroid_kotlin.ui.base.BaseFragment
import com.chejdj.wanandroid_kotlin.ui.commons.adapter.CommonArticleAdapter
import com.chejdj.wanandroid_kotlin.ui.login.LoginActivity
import com.chejdj.wanandroid_kotlin.ui.me.contract.MeContract
import com.chejdj.wanandroid_kotlin.ui.me.presenter.MePresenter
import com.chejdj.wanandroid_kotlin.utils.StringUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MeFragment : BaseFragment(), MeContract.View {
    @BindView(R.id.toolbar)
    lateinit var toolbar: CollapsingToolbarLayout
    @BindView(R.id.scrollView)
    lateinit var scrollView: NestedScrollView
    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView
    private lateinit var presenter: MeContract.Presenter
    private var currentPage = 0
    private var totalPage = 0
    private val articleData = ArrayList<Article>()
    private lateinit var adapter: CommonArticleAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_me
    }

    override fun initView() {
        EventBus.getDefault().register(this)
        if (AccountManager.isLogin) {
            hasLogin()
        } else {
            scrollView.visibility = View.VISIBLE
        }

    }

    private fun hasLogin() {
        if (scrollView.visibility == View.VISIBLE) {
            scrollView.visibility = View.GONE
        }
        toolbar.title = AccountManager.accountName

        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        adapter = CommonArticleAdapter(R.layout.item_article, articleData)

        adapter.openLoadAnimation()
        adapter.setEnableLoadMore(true)
        adapter.setOnLoadMoreListener({
            currentPage++
            if (currentPage < totalPage) {
                presenter.getCollectArticles(currentPage)
            } else {
                adapter.loadMoreEnd()
            }
        }, recyclerView)


        recyclerView.adapter = adapter


        presenter = MePresenter(this, this)
        presenter.getCollectArticles(currentPage)

    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
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
                    .setNegativeButton("OK") { dialog, which ->
                        dialog?.dismiss()
                        toolbar.title = "Login"
                        scrollView.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                        AccountManager.clearAccount()
                    }
                    .setPositiveButton("No,No,No") { dialog, which ->
                        dialog?.dismiss()
                    }.setMessage(StringUtils.getString(R.string.logout_warning)).create()
            dialog.show()
        }
    }

    override fun showCollectArticles(data: ArticleData) {
        if (data !== null && data.datas !== null) {
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
            adapter.loadMoreComplete()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun loginSuccessful(event: LoginEvent) {
        hasLogin()
    }
}