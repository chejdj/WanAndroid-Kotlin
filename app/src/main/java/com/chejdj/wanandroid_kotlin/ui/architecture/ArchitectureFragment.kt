package com.chejdj.wanandroid_kotlin.ui.architecture

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.base.BaseLazyLoadFragment
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.SecondaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.ui.architecture.adapter.ArchitectureAdapter
import com.chejdj.wanandroid_kotlin.ui.architecture.viewmodel.ArchitectureViewModel
import com.chejdj.wanandroid_kotlin.ui.architecturedetail.ArchitectureDetailActivity
import kotlinx.coroutines.launch

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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel?.uiStateFlow?.collect {
                    adapter.addData(it.data)
                }
            }
        }
    }

    override fun loadData() {
        viewModel?.sendUiIntent(ArchitectureIntent.GetArchitecture)
    }

    override fun isDataEmpty(): Boolean {
        return data.isEmpty()
    }
}