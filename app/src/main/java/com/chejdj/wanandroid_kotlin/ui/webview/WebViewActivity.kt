package com.chejdj.wanandroid_kotlin.ui.webview

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.WindowManager
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import android.widget.Toast
import butterknife.OnClick
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.ui.webview.VasSonic.HostSonicRuntime
import com.chejdj.wanandroid_kotlin.ui.webview.VasSonic.SonicSessionClientImpl
import com.tencent.sonic.sdk.SonicConfig
import com.tencent.sonic.sdk.SonicEngine
import com.tencent.sonic.sdk.SonicSession
import com.tencent.sonic.sdk.SonicSessionConfig
import kotlinx.android.synthetic.main.activity_webview.*


/**
 * Created by zhuyangyang on 2019-08-27
 * 作为一个WebView来加载文章页面
 */
class WebViewActivity : AppCompatActivity() {
    private lateinit var articleUrl: String
    private lateinit var articleTitle: String
    private lateinit var sonicSession: SonicSession


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        if (savedInstanceState != null) {
            articleUrl = savedInstanceState.getString(ARTICLE_URL)
            articleTitle = savedInstanceState.getString(ARTICLE_TITLE)
        } else {
            articleUrl = intent.getStringExtra(ARTICLE_URL)
            articleTitle = intent.getStringExtra(ARTICLE_TITLE)
        }

        window.addFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED)

        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(HostSonicRuntime(application), SonicConfig.Builder().build())
        }
        val config = SonicSessionConfig.Builder().build()
        SonicEngine.getInstance().preCreateSession(articleUrl, config)

        val sonicSessionClient = SonicSessionClientImpl()
        sonicSession = SonicEngine.getInstance().createSession(articleUrl, config)
        if (null != sonicSession) {
            sonicSession.bindClient(sonicSessionClient)
        } else {
            Toast.makeText(this, "create sonic session fail!", Toast.LENGTH_LONG).show()
        }

        val titleTx: TextView = findViewById(R.id.title)
        titleTx.text = articleTitle
        val webView: WebView = findViewById(R.id.webView)

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                if (sonicSession != null) {
                    sonicSession.sessionClient.pageFinish(url)
                }
            }

            @TargetApi(21)
            override fun shouldInterceptRequest(view: WebView, request: WebResourceRequest): WebResourceResponse? {
                return shouldInterceptRequest(view, request.url.toString())
            }

            override fun shouldInterceptRequest(view: WebView, url: String): WebResourceResponse? {
                return if (sonicSession != null) {
                    sonicSession.sessionClient.requestResource(url) as WebResourceResponse
                } else null
            }
        }

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.removeJavascriptInterface("searchBoxJavaBridge_")
        intent.putExtra(SonicJavaScriptInterface.PARAM_LOAD_URL_TIME, System.currentTimeMillis())
        webView.addJavascriptInterface(SonicJavaScriptInterface(sonicSessionClient, intent), "sonic")

        // init webview settings
        webSettings.allowContentAccess = true
        webSettings.databaseEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.setAppCacheEnabled(true)
        webSettings.savePassword = false
        webSettings.saveFormData = false
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true


        if (sonicSessionClient != null) {
            sonicSessionClient.bindWebView(webView)
            sonicSessionClient.clientReady()
        } else {
            webView.loadUrl(articleUrl)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(ARTICLE_TITLE, articleTitle)
        outState.putString(ARTICLE_URL, articleUrl)
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

    @OnClick(R.id.back)
    fun back() {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (null != sonicSession) {
            sonicSession.destroy()
        }
    }
}