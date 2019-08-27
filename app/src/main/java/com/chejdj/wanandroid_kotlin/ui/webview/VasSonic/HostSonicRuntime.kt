package com.chejdj.wanandroid_kotlin.ui.webview.VasSonic

import android.content.Context
import com.tencent.sonic.sdk.SonicRuntime
import com.tencent.sonic.sdk.SonicSessionClient
import java.io.InputStream

class HostSonicRuntime(context: Context) : SonicRuntime(context) {
    override fun showToast(text: CharSequence?, duration: Int) {

    }

    override fun log(tag: String?, level: Int, message: String?) {

    }

    override fun getUserAgent(): String {
        return ""
    }

    override fun isNetworkValid(): Boolean {
        return true
    }

    override fun postTaskToThread(task: Runnable?, delayMillis: Long) {

    }

    override fun isSonicUrl(url: String?): Boolean {
        return true
    }

    override fun setCookie(url: String?, cookies: MutableList<String>?): Boolean {
        return true
    }

    override fun getCookie(url: String?): String {
        return ""
    }

    override fun createWebResourceResponse(
        mimeType: String?,
        encoding: String?,
        data: InputStream?,
        headers: MutableMap<String, String>?
    ): Any {
        return ""
    }

    override fun getCurrentUserAccount(): String {
        return ""
    }

    override fun notifyError(client: SonicSessionClient?, url: String?, errorCode: Int) {
    }
}