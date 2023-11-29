package com.chejdj.wanandroid_kotlin.ui.search

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.OnClick
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.data.bean.HotKeyBean
import com.chejdj.wanandroid_kotlin.data.bean.article.Article
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.ui.base.BaseActivity
import com.chejdj.wanandroid_kotlin.ui.commons.adapter.CommonArticleAdapter
import com.chejdj.wanandroid_kotlin.ui.search.viewmodel.SearchViewModel
import com.chejdj.wanandroid_kotlin.ui.webview.WebViewActivity
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout
import java.util.*

/**
 * Created by zhuyangyang on 2019-08-26
 */
class SearchActivity : BaseActivity() {

    @BindView(R.id.search)
    lateinit var searchView: SearchView

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.flowLayout)
    lateinit var flowLayout: TagFlowLayout

    @BindView(R.id.hot_keys)
    lateinit var hotKeysTx: TextView
    private var currentPage: Int = 0
    private var totalPage: Int = 0
    private val articleList = ArrayList<Article>()
    private var currentKeyWords: String = ""
    private lateinit var adapter: CommonArticleAdapter
    private var viewModel: SearchViewModel? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun initView() {
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        viewModel?.getHotKeys()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = CommonArticleAdapter(articleList)
        adapter.animationEnable = true
        adapter.loadMoreModule.setOnLoadMoreListener {
            currentPage++
            if (currentPage > totalPage) {
                adapter.loadMoreModule.loadMoreEnd()
            } else {
                viewModel?.getSearchResults(currentKeyWords, currentPage)
            }
        }
        adapter.setOnItemClickListener { _, _, position ->
            if (position < articleList.size) {
                val article = articleList[position]
                WebViewActivity.launchWebViewActivity(this, article.link, article.title)
            }
        }

        recyclerView.adapter = adapter

        viewModel?.hotSearchWords?.observe(this) {
            showHotKeys(it)
        }
        viewModel?.searchResult?.observe(this) {
            showSearchResults(it)
        }
    }

    private fun showHotKeys(hotkeys: List<HotKeyBean>) {
        if (recyclerView.visibility == View.VISIBLE) {
            recyclerView.visibility = View.GONE
        }
        if (hotKeysTx.visibility == View.GONE) {
            hotKeysTx.visibility = View.VISIBLE
        }
        if (flowLayout.visibility == View.GONE) {
            flowLayout.visibility = View.VISIBLE
        }
        flowLayout.adapter = object : TagAdapter<HotKeyBean>(hotkeys) {
            override fun getView(parent: FlowLayout?, position: Int, t: HotKeyBean?): View {
                val textView: TextView =
                    LayoutInflater.from(parent?.context).inflate(
                        R.layout.item_tags,
                        parent,
                        false
                    ) as TextView
                textView.text = t!!.name
                return textView
            }
        }
        flowLayout.setOnTagClickListener { _, position, _ ->
            currentPage = 0
            totalPage = 0
            currentKeyWords = hotkeys[position].name
            viewModel?.getSearchResults(currentKeyWords, currentPage)
            true
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (!TextUtils.isEmpty(p0)) {
                    currentPage = 0
                    totalPage = 0
                    viewModel?.getSearchResults(p0!!, currentPage)
                } else {
                    viewModel?.getHotKeys()
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (!TextUtils.isEmpty(p0)) {
                    currentPage = 0
                    totalPage = 0
                    viewModel?.getSearchResults(p0!!, currentPage)
                } else {
                    viewModel?.getHotKeys()
                }
                return true
            }

        })

    }

    private fun showSearchResults(articleData: ArticleData) {
        if (recyclerView.visibility == View.GONE) {
            recyclerView.visibility = View.VISIBLE
        }
        if (hotKeysTx.visibility == View.VISIBLE) {
            hotKeysTx.visibility = View.GONE
        }
        if (flowLayout.visibility == View.VISIBLE) {
            flowLayout.visibility = View.GONE
        }
        if (articleData.curPage == 1) {
            articleList.clear()
        }
        currentPage = articleData.curPage
        totalPage = articleData.pageCount
        adapter.addData(articleData.datas!!)
    }

    @OnClick(R.id.back)
    fun backActivity() {
        finish()
    }
}