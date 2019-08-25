package com.chejdj.wanandroid_kotlin.ui.login.presenter

import android.text.TextUtils
import com.chejdj.wanandroid_kotlin.data.bean.BaseRes
import com.chejdj.wanandroid_kotlin.data.bean.LoginBean
import com.chejdj.wanandroid_kotlin.events.LoginEvent
import com.chejdj.wanandroid_kotlin.ui.login.contract.LoginContract
import com.chejdj.wanandroid_kotlin.ui.login.model.LoginModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus

class LoginPresenter(view: LoginContract.View) : LoginContract.Presenter {
    private val view = view
    private val model = LoginModel()
    override fun login(accountName: String, password: String) {
        model.login(accountName, password).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BaseRes<LoginBean>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: BaseRes<LoginBean>) {
                    if (t.errorCode == 0) {
                        if (view != null) {
                            view.loginSuccessful(accountName)
                            EventBus.getDefault().post(LoginEvent(accountName))
                        }
                    } else if (!TextUtils.isEmpty(t.errorMsg)) {
                        view.loginFail(t.errorMsg!!)
                    }
                }

                override fun onError(e: Throwable) {
                }
            })
    }

}