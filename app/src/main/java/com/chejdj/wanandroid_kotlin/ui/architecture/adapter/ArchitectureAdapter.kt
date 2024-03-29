package com.chejdj.wanandroid_kotlin.ui.architecture.adapter

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.PrimaryArticleDirectoryBean
import com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem.SecondaryArticleDirectoryBean
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout

class ArchitectureAdapter(layoutId: Int, data: MutableList<PrimaryArticleDirectoryBean>) :
    BaseQuickAdapter<PrimaryArticleDirectoryBean, ArchitectureAdapter.ViewHolder>(layoutId, data) {
    override fun convert(holder: ViewHolder, item: PrimaryArticleDirectoryBean) {
        holder.title.text = item.name
        holder.updateSecondArchitectureDirectoryData(item.children!!)
    }

    class ViewHolder(view: View) : BaseViewHolder(view) {
        private val architectureTags: TagFlowLayout = view.findViewById(R.id.flowLayout_tags)
        val title: TextView = view.findViewById(R.id.title)
        val secondArchitectureDirectoryList = ArrayList<SecondaryArticleDirectoryBean>()

        init {
            architectureTags.adapter =
                object :
                    TagAdapter<SecondaryArticleDirectoryBean>(secondArchitectureDirectoryList) {
                    override fun getView(
                        parent: FlowLayout?,
                        position: Int,
                        t: SecondaryArticleDirectoryBean?
                    ): View {
                        val textView: TextView =
                            LayoutInflater.from(view.context)
                                .inflate(R.layout.item_tags, parent, false) as TextView
                        textView.text = t!!.name
                        return textView
                    }
                }
        }

        fun updateSecondArchitectureDirectoryData(data: List<SecondaryArticleDirectoryBean>) {
            secondArchitectureDirectoryList.clear()
            secondArchitectureDirectoryList.addAll(data)
            architectureTags.adapter.notifyDataChanged()
        }
    }
}