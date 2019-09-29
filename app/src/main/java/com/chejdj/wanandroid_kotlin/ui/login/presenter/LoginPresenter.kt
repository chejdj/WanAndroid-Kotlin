package com.chejdj.wanandroid_kotlin.ui.login.presenter

import com.chejdj.wanandroid_kotlin.data.remote.executeResponse
import com.chejdj.wanandroid_kotlin.events.LoginEvent
import com.chejdj.wanandroid_kotlin.ui.login.contract.LoginContract
import com.chejdj.wanandroid_kotlin.ui.login.model.LoginModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus

class LoginPresenter(private val view: LoginContract.View, private val scope: CoroutineScope) :
    LoginContract.Presenter, CoroutineScope by scope {
    private val model = LoginModel()
    override fun login(accountName: String, password: String) {
        launch {
            val result = withContext(Dispatchers.IO) { model.login(accountName, password) }
            executeResponse(result, {
                view.loginSuccessful(accountName)
                EventBus.getDefault().post(LoginEvent(accountName))
            }, {
                view.loginFail(result.errorMsg)
            })
        }
    }
}