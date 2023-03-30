package com.chejdj.wanandroid_kotlin.ui.webview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.chejdj.wanandroid_kotlin.R


/**
 * Created by zhuyangyang on 2019-08-27
 * 作为一个WebView来加载文章页面
 */
class WebViewActivity : AppCompatActivity() {
    @BindView(R.id.title)
    lateinit var titleTx: TextView
    @BindView(R.id.webView)
    lateinit var webView: WebView
    private lateinit var articleUrl: String
    private lateinit var articleTitle: String

    fun getLayoutId(): Int {
        return R.layout.activity_webview
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        ButterKnife.bind(this)
        if (savedInstanceState != null) {
            articleUrl = savedInstanceState.getString(ARTICLE_URL).toString()
            articleTitle = savedInstanceState.getString(ARTICLE_TITLE).toString()
        } else {
            articleUrl = intent.getStringExtra(ARTICLE_URL).toString()
            articleTitle = intent.getStringExtra(ARTICLE_TITLE).toString()
        }
        initView()
    }

    fun initView() {
        titleTx.text = articleTitle
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.allowContentAccess = true
        webSettings.databaseEnabled = true
        webSettings.domStorageEnabled = true
//        webSettings.setAppCacheEnabled(true)
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        webSettings.blockNetworkImage = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                webView.loadUrl(request?.url.toString())
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                webSettings.blockNetworkImage = false
                super.onPageFinished(view, url)
            }
        }
        webView.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                webView.goBack()
            }
            false
        }
        webView.loadUrl(articleUrl)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(ARTICLE_TITLE, articleTitle)
        outState.putString(ARTICLE_URL, articleUrl)
    }

    companion object {
        const val ARTICLE_URL = "ARTICLE_URL"
        const val ARTICLE_TITLE = "ARTICEL_TITLE"
        fun launchWebViewActivity(context: Context, url: String, title: String) {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra(ARTICLE_URL, url)
            intent.putExtra(ARTICLE_TITLE, title)
            context.startActivity(intent)
        }
    }

    @OnClick(R.id.back)
    fun goToBack() {
        finish()
    }
}