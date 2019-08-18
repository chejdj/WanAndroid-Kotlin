package com.chejdj.wanandroid_kotlin.ui.commonarticle

import android.support.v4.text.HtmlCompat
import android.support.v4.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chejdj.wanandroid_kotlin.data.bean.article.Article
import com.chejdj.wanandroid_kotlin.utils.TimeUtils
import kotlinx.android.synthetic.main.item_article.view.*

class CommonArticleAdapter(layoutResId: Int, data: List<Article>) :
    BaseQuickAdapter<Article, CommonArticleAdapter.ViewHolder>(layoutResId, data) {
    override fun convert(helper: ViewHolder, item: Article?) {
        val title = HtmlCompat.fromHtml(item!!.title!!, FROM_HTML_MODE_LEGACY).toString()
        val author = item!!.author
        var description = item.desc
        if (!TextUtils.isEmpty(description)) {
            description = HtmlCompat.fromHtml(description!!, FROM_HTML_MODE_LEGACY).toString()
        }
        var category = item.superChapterName + "/" + item.chapterName
        var tags: String
        if (item.tags!!.isEmpty())
            tags = "分类"
        else {
            tags = item.tags!![0]!!.name!!
        }
        val time = TimeUtils.timeToString(item.publishTime)
        if (tags == TAG_PROJECT && !TextUtils.isEmpty(item.envelopePic)) {
            helper.projectImageView.visibility = View.VISIBLE
            Glide.with(helper.itemView).load(item.envelopePic).into(helper.projectImageView)
        } else {
            if (helper.projectImageView.visibility == View.VISIBLE) {
                helper.projectImageView.visibility = View.GONE
            }
        }
        if (!TextUtils.isEmpty(description)) {
            helper.articleDescription.visibility = View.VISIBLE
            helper.articleDescription.text = description
        } else {
            helper.articleDescription.visibility = View.GONE
        }
        helper.articleTitle.text = title
        helper.articleAuthor.text = author
        helper.articleCategory.text = category
        helper.articleTags.text = tags
        helper.articleTime.text = time
    }

    companion object {
        const val TAG_PROJECT: String = "项目"
    }

    class ViewHolder(view: View) : BaseViewHolder(view) {
        var articleTitle: TextView = view.title
        var articleDescription: TextView = view.description
        var articleTime: TextView = view.time
        var articleAuthor: TextView = view.author
        var articleTags: TextView = view.tags
        var articleCategory: TextView = view.category
        var projectImageView: ImageView = view.project_image
    }
}