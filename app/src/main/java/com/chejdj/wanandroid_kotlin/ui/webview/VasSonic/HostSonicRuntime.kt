package com.chejdj.wanandroid_kotlin.ui.webview.VasSonic

import android.content.Context
import android.os.Build
import android.webkit.WebResourceResponse
import com.tencent.sonic.sdk.SonicRuntime
import com.tencent.sonic.sdk.SonicSessionClient
import java.io.InputStream


class HostSonicRuntime(context: Context) : SonicRuntime(context) {
    override fun showToast(text: CharSequence?, duration: Int) {

    }

    override fun log(tag: String?, level: Int, message: String?) {

    }

    override fun getUserAgent(): String {
        return "Mozilla/5.0 (Linux; Android 5.1.1; Nexus 6 Build/LYZ28E) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Mobile Safari/537.36"
    }

    override fun isNetworkValid(): Boolean {
        return true
    }

    override fun postTaskToThread(task: Runnable?, delayMillis: Long) {
        val thread = Thread(task, "SonicThread")
        thread.start()
    }

    override fun isSonicUrl(url: String?): Boolean {
        return true
    }

    override fun setCookie(url: String?, cookies: MutableList<String>?): Boolean {
        return false
    }

    override fun getCookie(url: String?): String? {
        return null
    }

    override fun createWebResourceResponse(
        mimeType: String?,
        encoding: String?,
        data: InputStream?,
        headers: MutableMap<String, String>?
    ): Any? {
        val resourceResponse = WebResourceResponse(mimeType, encoding, data)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            resourceResponse.responseHeaders = headers
        }
        return resourceResponse
    }

    override fun getCurrentUserAccount(): String {
        return "chejdj"
    }

    override fun notifyError(client: SonicSessionClient?, url: String?, errorCode: Int) {
    }
}