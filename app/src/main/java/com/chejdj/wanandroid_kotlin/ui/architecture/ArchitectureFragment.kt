package com.chejdj.wanandroid_kotlin.ui.architecture

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.SecondaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.ui.architecture.adapter.ArchitectureAdapter
import com.chejdj.wanandroid_kotlin.ui.architecture.contract.ArchitectureContract
import com.chejdj.wanandroid_kotlin.ui.architecture.presenter.ArchitecturePresenter
import com.chejdj.wanandroid_kotlin.ui.architecturedetail.ArchitectureDetailActivity
import com.chejdj.wanandroid_kotlin.ui.base.BaseLazyLoadFragment

class ArchitectureFragment : BaseLazyLoadFragment(), ArchitectureContract.View {
    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView
    private val data: List<PrimaryArticleDirectoryBean> = ArrayList()
    private lateinit var adapter: ArchitectureAdapter
    private lateinit var presenter: ArchitectureContract.Presenter

    override fun getLayoutId(): Int {
        return R.layout.fragment_architecture
    }

    override fun initView() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        adapter =
            ArchitectureAdapter(R.layout.item_architecture, data)
        adapter.openLoadAnimation()
        recyclerView.adapter = adapter
        presenter = ArchitecturePresenter(this)
        adapter.setOnItemClickListener { adapter, view, position ->
            if (position < data.size) {
                ArchitectureDetailActivity.launch(
                    context!!,
                    data[position].name,
                    data[position].children as ArrayList<SecondaryArticleDirectoryBean>
                )
            }
        }
    }

    override fun loadData() {
        presenter.getArchitectureData()
    }

    override fun isDataEmpty(): Boolean {
        return data.isEmpty()
    }

    override fun showArchitectureData(data: List<PrimaryArticleDirectoryBean>) {
        adapter.addData(data)
    }
}