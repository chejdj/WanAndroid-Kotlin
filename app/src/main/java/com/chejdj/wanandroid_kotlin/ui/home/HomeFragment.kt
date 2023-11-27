package com.chejdj.wanandroid_kotlin.ui.home

import android.content.Intent
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.data.bean.HomeBannerBean
import com.chejdj.wanandroid_kotlin.data.bean.article.Article
import com.chejdj.wanandroid_kotlin.ui.base.BaseLazyLoadFragment
import com.chejdj.wanandroid_kotlin.ui.commons.adapter.CommonArticleAdapter
import com.chejdj.wanandroid_kotlin.ui.home.viewmodel.HomeViewModel
import com.chejdj.wanandroid_kotlin.ui.search.SearchActivity
import com.chejdj.wanandroid_kotlin.ui.webview.WebViewActivity
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator

class HomeFragment : BaseLazyLoadFragment() {
    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.swipe)
    lateinit var swipe: SwipeRefreshLayout
    private var homeViewModel: HomeViewModel? = null
    private val articleList = ArrayList<Article>()
    private val bannerList = ArrayList<HomeBannerBean>()
    private lateinit var commonArticleAdapter: CommonArticleAdapter
    private var currentPage = 0
    private var totalPage = 0
    private lateinit var homeBanner: Banner<HomeBannerBean, BannerImageAdapter<HomeBannerBean>>

    override fun initView() {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        val headerView = LayoutInflater.from(context).inflate(R.layout.item_banner, null)
        homeBanner = headerView.findViewById(R.id.home_banner)
        homeBanner.addBannerLifecycleObserver(this).indicator = CircleIndicator(requireContext())
        homeBanner.setAdapter(object : BannerImageAdapter<HomeBannerBean>(bannerList) {
            override fun onBindView(
                holder: BannerImageHolder?,
                data: HomeBannerBean?,
                position: Int,
                size: Int
            ) {
                if (data != null) {
                    holder?.imageView?.let {
                        Glide.with(it).load(data.imagePath).apply(
                            RequestOptions.bitmapTransform(
                                RoundedCorners(30)
                            )
                        ).into(it)
                    }
                }
            }
        })
        homeBanner.setDatas(bannerList)
        homeBanner.start()

        commonArticleAdapter =
            CommonArticleAdapter(articleList)
        commonArticleAdapter.addHeaderView(headerView)
        commonArticleAdapter.animationEnable = true
        commonArticleAdapter.loadMoreModule.isEnableLoadMore = true
        recyclerView.adapter = commonArticleAdapter
        initListener()
        homeViewModel?.start()
        homeViewModel?.bannerLiveData?.observe(this) {
            if (it !== null) {
                bannerList.clear()
                bannerList.addAll(it)
                homeBanner.setDatas(it)
            }
        }
        homeViewModel?.articleData?.observe(this) {
            if (it !== null && it.datas !== null) {
                if (it.curPage == 0) {
                    articleList.clear()
                }
                if (totalPage == 0) {
                    totalPage = it.pageCount
                }
                currentPage = it.curPage
                commonArticleAdapter.addData(it.datas!!)
                commonArticleAdapter.loadMoreModule.loadMoreComplete()
            }
        }
    }

    override fun loadData() {
        homeViewModel?.start()
    }

    override fun isDataEmpty(): Boolean {
        return articleList.isEmpty()
    }

    private fun initListener() {
        swipe.setOnRefreshListener {
            homeViewModel?.start()
            swipe.isRefreshing = false
        }
        commonArticleAdapter.loadMoreModule.setOnLoadMoreListener {
            if (currentPage + 1 <= totalPage) {
                homeViewModel?.getArticlesData(currentPage + 1)
            } else {
                commonArticleAdapter.loadMoreModule.loadMoreEnd()
            }
        }
        commonArticleAdapter.setOnItemClickListener { _, _, position ->
            if (position < articleList.size) {
                val article = articleList[position]
                WebViewActivity.launchWebViewActivity(requireContext(), article.link, article.title)
            }
        }

        homeBanner.adapter.setOnBannerListener { _, position ->
            WebViewActivity.launchWebViewActivity(
                requireContext(),
                bannerList[position].url,
                bannerList[position].title
            )
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    @OnClick(R.id.search)
    fun intentToSearchActivity() {
        val intent = Intent(context, SearchActivity::class.java)
        startActivity(intent)
    }
}