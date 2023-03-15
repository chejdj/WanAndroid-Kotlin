package com.chejdj.wanandroid_kotlin.ui.commons

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.data.bean.article.Article
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.ui.base.BaseLazyLoadViewPagerFragment
import com.chejdj.wanandroid_kotlin.ui.commons.adapter.CommonArticleAdapter
import com.chejdj.wanandroid_kotlin.ui.commons.contract.CommonArticleContract
import com.chejdj.wanandroid_kotlin.ui.commons.presenter.CommonArticlePresenter
import com.chejdj.wanandroid_kotlin.ui.webview.WebViewActivity

class CommonArticleFragment : BaseLazyLoadViewPagerFragment(), CommonArticleContract.View {
    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CommonArticleAdapter
    private val data = ArrayList<Article>()
    private var cid = 0
    private var type = 0
    private var currentPage: Int = 0
    private var totalPage: Int = 0
    private lateinit var presenter: CommonArticleContract.Presenter

    override fun getLayoutId(): Int {
        return R.layout.fragment_common_article
    }

    override fun initView() {
        val bundle = arguments
        cid = bundle!!.getInt(CID)
        type = bundle.getInt(TYPE)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = CommonArticleAdapter(data)
//        adapter.openLoadAnimation()
//        adapter.setEnableLoadMore(true)
        recyclerView.adapter = adapter
        presenter = CommonArticlePresenter(this, this)
//        adapter.setOnLoadMoreListener({
//            currentPage++
//            if (currentPage >= totalPage) {
//                adapter.loadMoreEnd()
//            } else {
//                presenter.getArticleData(cid, currentPage, type)
//            }
//        }, recyclerView)
        adapter.setOnItemClickListener { _, _, position ->
            if (position < data.size) {
                val article = data[position]
                WebViewActivity.launchWebViewActivity(requireContext(), article.link, article.title)
            }
        }

    }

    override fun loadData() {
        presenter.getArticleData(cid, currentPage, type)
    }


    override fun showArticleDatas(data: ArticleData) {
        currentPage = data.curPage
        totalPage = data.pageCount
//        adapter.loadMoreComplete()
        if (currentPage == 0) {
            this.data.clear()
        }
        adapter.addData(data.datas!!)
    }

    companion object {
        const val TYPE = "TYPE"
        const val CID = "CID"
        fun getCommonArticleFragment(cid: Int, type: Int): Fragment {
            val fragment = CommonArticleFragment()
            val bundle = Bundle()
            bundle.putInt(TYPE, type)
            bundle.putInt(CID, cid)
            fragment.arguments = bundle
            return fragment
        }
    }

}