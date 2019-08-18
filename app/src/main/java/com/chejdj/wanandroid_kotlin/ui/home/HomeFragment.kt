package com.chejdj.wanandroid_kotlin.ui.home

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import butterknife.BindView
import butterknife.OnClick
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.data.bean.HomeBannerBean
import com.chejdj.wanandroid_kotlin.data.bean.article.Article
import com.chejdj.wanandroid_kotlin.data.bean.article.ArticleData
import com.chejdj.wanandroid_kotlin.ui.base.BaseFragment
import com.chejdj.wanandroid_kotlin.ui.commonarticle.CommonArticleAdapter
import com.chejdj.wanandroid_kotlin.ui.home.contract.HomeContract
import com.chejdj.wanandroid_kotlin.ui.home.presenter.HomePresenter
import com.youth.banner.Banner

class HomeFragment : BaseFragment(), HomeContract.View {

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView
    @BindView(R.id.swipe)
    lateinit var swipe: SwipeRefreshLayout
    private lateinit var presenter: HomeContract.Presenter
    private val articleList = ArrayList<Article>()
    private val bannerList = ArrayList<HomeBannerBean>()
    private lateinit var commonArticleAdapter: CommonArticleAdapter
    private var currentPage = 0
    private var totalPage = 0
    private lateinit var homeBanner: Banner

    override fun initView() {
        presenter = HomePresenter(this)
        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        val headerView = LayoutInflater.from(context).inflate(R.layout.item_banner, null)
        homeBanner = headerView.findViewById(R.id.home_banner) as Banner
        homeBanner.setImageLoader(HomeImageLoader())
        homeBanner.setImages(bannerList)
        homeBanner.start()

        commonArticleAdapter = CommonArticleAdapter(R.layout.item_article, articleList)
        commonArticleAdapter.addHeaderView(headerView)
        commonArticleAdapter.openLoadAnimation()
        commonArticleAdapter.setEnableLoadMore(true)
        recyclerView.adapter = commonArticleAdapter

        initListener()
        (presenter as HomePresenter).start()
    }

    private fun initListener() {
        swipe.setOnRefreshListener {
            (presenter as HomePresenter).start()
            swipe.isRefreshing = false
        }
        commonArticleAdapter.setOnLoadMoreListener({
            if (currentPage + 1 <= totalPage) {
                presenter.getArticlesData(currentPage + 1)
            } else {
                commonArticleAdapter.loadMoreEnd()
            }
        }, recyclerView)

    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    @OnClick(R.id.search)
    fun intentToSearchActivity() {

    }


    override fun onStop() {
        super.onStop()
        (presenter as HomePresenter).destory()
    }

    override fun shwoBannerData(data: List<HomeBannerBean>?) {
        if (data !== null) {
            homeBanner.update(data)
        }
    }

    override fun showArticlesData(data: ArticleData?) {
        if (data !== null && data.datas !== null) {
            if (data.curPage == 0) {
                articleList.clear()
            }
            if (totalPage == 0) {
                totalPage = data.pageCount
            }
            currentPage = data.curPage
            commonArticleAdapter.addData(data.datas!!)
            commonArticleAdapter.loadMoreComplete()
        }
    }
}