package com.chejdj.wanandroid_kotlin.ui.webview

import android.content.Context
import android.content.Intent
import android.widget.TextView
import butterknife.BindView
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.ui.base.BaseActivity

/**
 * Created by zhuyangyang on 2019-08-27
 * 作为一个WebView来加载文章页面
 */
class WebViewActivity : BaseActivity() {
    @BindView(R.id.title)
    lateinit var titleTx: TextView

    override fun getLayoutId(): Int {
        return R.layout.activity_webview
    }

    override fun initView() {
    }

    companion object {
        const val ARTICLE_URL = "ARTICLE_URL"
        const val ARTICLE_TITLE = "ARTICEL_TITLE"
        fun launch(context: Context, url: String, title: String) {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra(ARTICLE_URL, url)
            intent.putExtra(ARTICLE_TITLE, title)
            context.startActivity(intent)
        }
    }
}