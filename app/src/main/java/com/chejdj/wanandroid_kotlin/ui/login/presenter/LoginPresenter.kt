package com.chejdj.wanandroid_kotlin.ui.login.presenter

import com.chejdj.wanandroid_kotlin.events.LoginEvent
import com.chejdj.wanandroid_kotlin.ui.login.contract.LoginContract
import com.chejdj.wanandroid_kotlin.ui.login.model.LoginModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {
    private val model = LoginModel()
    override fun login(accountName: String, password: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { model.login(accountName, password).await() }
            if (result.errorCode == 0) {
                view.loginSuccessful(accountName)
                EventBus.getDefault().post(LoginEvent(accountName))
            } else {
                view.loginFail(result.errorMsg)
            }
        }
    }

}