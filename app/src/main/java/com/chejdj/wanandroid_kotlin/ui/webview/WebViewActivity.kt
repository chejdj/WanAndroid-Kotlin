package com.chejdj.wanandroid_kotlin.ui.webview

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.ui.webview.VasSonic.HostSonicRuntime
import com.chejdj.wanandroid_kotlin.ui.webview.VasSonic.SonicSessionClientImpl
import com.tencent.sonic.sdk.SonicConfig
import com.tencent.sonic.sdk.SonicEngine
import com.tencent.sonic.sdk.SonicSession
import com.tencent.sonic.sdk.SonicSessionConfig


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
        articleUrl = intent.getStringExtra(ARTICLE_URL)
        articleTitle = intent.getStringExtra(ARTICLE_TITLE)

        window.addFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED)

        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(HostSonicRuntime(application), SonicConfig.Builder().build())
        }

        val sonicSessionClient = SonicSessionClientImpl()
        sonicSession = SonicEngine.getInstance().createSession(articleUrl, SonicSessionConfig.Builder().build())
        if (null != sonicSession) {
            sonicSession.bindClient(sonicSessionClient)
        } else {
            // this only happen when a same sonic session is already running,
            // u can comment following codes to feedback as a default mode.
            throw UnknownError("create session fail!")
        }


        setContentView(R.layout.activity_webview)
        val titleTx: TextView = findViewById(R.id.title)
        titleTx.text = articleTitle
        val webView: WebView = findViewById(R.id.webView)

        webView.setWebViewClient(object : WebViewClient() {
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
                    //step 6: Call sessionClient.requestResource when host allow the application
                    // to return the local data .
                    sonicSession.sessionClient.requestResource(url) as WebResourceResponse
                } else null
            }
        })

        val webSettings = webView.getSettings()

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