package com.chejdj.wanandroid_kotlin.ui.commons.adapter

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.data.bean.article.Article
import com.chejdj.wanandroid_kotlin.utils.TimeUtils

class CommonArticleAdapter(data: MutableList<Article>) :
    BaseQuickAdapter<Article, CommonArticleAdapter.ViewHolder>(R.layout.item_article, data) {
    override fun convert(holder: ViewHolder, item: Article) {
        val title = HtmlCompat.fromHtml(item.title, FROM_HTML_MODE_LEGACY).toString()
        val author = item.author
        var description = item.desc
        if (!TextUtils.isEmpty(description)) {
            description = HtmlCompat.fromHtml(description, FROM_HTML_MODE_LEGACY).toString()
        }
        val category = item.superChapterName + "/" + item.chapterName
        val tags = if (item.tags == null || item.tags!!.isEmpty())
            "分类"
        else {
            item.tags!![0].name
        }
        val time = TimeUtils.timeToString(item.publishTime)
        if (tags == TAG_PROJECT && !TextUtils.isEmpty(item.envelopePic)) {
            holder.projectImageView.visibility = View.VISIBLE
            Glide.with(holder.itemView).load(item.envelopePic).into(holder.projectImageView)
        } else {
            if (holder.projectImageView.visibility == View.VISIBLE) {
                holder.projectImageView.visibility = View.GONE
            }
        }
        if (!TextUtils.isEmpty(description)) {
            holder.articleDescription.visibility = View.VISIBLE
            holder.articleDescription.text = description
        } else {
            holder.articleDescription.visibility = View.GONE
        }
        holder.articleTitle.text = title
        holder.articleAuthor.text = author
        holder.articleCategory.text = category
        holder.articleTags.text = tags
        holder.articleTime.text = time
    }

    companion object {
        const val TAG_PROJECT: String = "项目"
    }

    class ViewHolder(view: View) : BaseViewHolder(view) {
        var articleTitle: TextView = view.findViewById(R.id.title)
        var articleDescription: TextView = view.findViewById(R.id.description)
        var articleTime: TextView = view.findViewById(R.id.time)
        var articleAuthor: TextView = view.findViewById(R.id.author)
        var articleTags: TextView = view.findViewById(R.id.tags)
        var articleCategory: TextView = view.findViewById(R.id.category)
        var projectImageView: ImageView = view.findViewById(R.id.project_image)
    }
}