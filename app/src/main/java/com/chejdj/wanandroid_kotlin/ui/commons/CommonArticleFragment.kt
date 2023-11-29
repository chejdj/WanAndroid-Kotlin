package com.chejdj.wanandroid_kotlin.ui.commons

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.base.BaseLazyLoadViewPagerFragment
import com.chejdj.wanandroid_kotlin.data.bean.article.Article
import com.chejdj.wanandroid_kotlin.ui.commons.adapter.CommonArticleAdapter
import com.chejdj.wanandroid_kotlin.ui.commons.viewmodel.CommonViewModel
import com.chejdj.wanandroid_kotlin.ui.webview.WebViewActivity
import kotlinx.coroutines.launch

class CommonArticleFragment : BaseLazyLoadViewPagerFragment() {
    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CommonArticleAdapter
    private val data = ArrayList<Article>()
    private var cid = 0
    private var type = 0
    private var currentPage: Int = 0
    private var totalPage: Int = 0
    private var commonViewModel: CommonViewModel? = null

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
        adapter.animationEnable = true
        recyclerView.adapter = adapter
        commonViewModel = ViewModelProvider(this)[CommonViewModel::class.java]
        adapter.loadMoreModule.setOnLoadMoreListener {
            currentPage++
            if (currentPage >= totalPage) {
                adapter.loadMoreModule.loadMoreEnd()
            } else {
                commonViewModel?.sendUiIntent(
                    CommonArticleIntent.GetCommonArticle(
                        cid,
                        currentPage,
                        type
                    )
                )
            }
        }
        adapter.setOnItemClickListener { _, _, position ->
            if (position < data.size) {
                val article = data[position]
                WebViewActivity.launchWebViewActivity(requireContext(), article.link, article.title)
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                commonViewModel?.uiStateFlow?.collect {
                    it.data?.let { articleData ->
                        currentPage = articleData.curPage
                        totalPage = articleData.pageCount
                        adapter.loadMoreModule.loadMoreComplete()
                        if (currentPage == 0) {
                            data.clear()
                        }
                        adapter.addData(articleData.datas!!)
                    }
                }
            }
        }
    }

    override fun loadData() {
        commonViewModel?.sendUiIntent(CommonArticleIntent.GetCommonArticle(cid, currentPage, type))
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