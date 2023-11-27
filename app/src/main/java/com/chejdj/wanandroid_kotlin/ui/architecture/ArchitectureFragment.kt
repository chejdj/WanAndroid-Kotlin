package com.chejdj.wanandroid_kotlin.ui.architecture

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.SecondaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.ui.architecture.adapter.ArchitectureAdapter
import com.chejdj.wanandroid_kotlin.ui.architecture.viewmodel.ArchitectureViewModel
import com.chejdj.wanandroid_kotlin.ui.architecturedetail.ArchitectureDetailActivity
import com.chejdj.wanandroid_kotlin.ui.base.BaseLazyLoadFragment

class ArchitectureFragment : BaseLazyLoadFragment() {
    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView
    private val data: MutableList<PrimaryArticleDirectoryBean> = ArrayList()
    private lateinit var adapter: ArchitectureAdapter
    private var viewModel: ArchitectureViewModel? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_architecture
    }

    override fun initView() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        adapter =
            ArchitectureAdapter(R.layout.item_architecture, data)
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this)[ArchitectureViewModel::class.java]
        adapter.setOnItemClickListener { _, _, position ->
            if (position < data.size) {
                ArchitectureDetailActivity.launch(
                    requireContext(),
                    data[position].name,
                    data[position].children as ArrayList<SecondaryArticleDirectoryBean>
                )
            }
        }
        viewModel?.architectureList?.observe(this) {
            it?.apply {
                adapter.addData(this)
            }
        }
    }

    override fun loadData() {
        viewModel?.getArchitectureData()
    }

    override fun isDataEmpty(): Boolean {
        return data.isEmpty()
    }
}