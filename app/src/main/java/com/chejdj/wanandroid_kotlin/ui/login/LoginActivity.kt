package com.chejdj.wanandroid_kotlin.ui.login

import android.content.Intent
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.OnClick
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.account.AccountManager
import com.chejdj.wanandroid_kotlin.ui.MainActivity
import com.chejdj.wanandroid_kotlin.ui.base.BaseActivity
import com.chejdj.wanandroid_kotlin.ui.login.contract.LoginContract
import com.chejdj.wanandroid_kotlin.ui.login.presenter.LoginPresenter
import com.chejdj.wanandroid_kotlin.utils.StringUtils

class LoginActivity : BaseActivity(), LoginContract.View {

    @BindView(R.id.title)
    lateinit var titleTx: TextView
    @BindView(R.id.login)
    lateinit var loginBtn: Button
    @BindView(R.id.password)
    lateinit var passWordEt: EditText
    @BindView(R.id.username)
    lateinit var usernameEt: EditText
    private lateinit var presenter: LoginContract.Presenter


    override fun loginSuccessful(username: String) {
        AccountManager.isLogin = true
        AccountManager.accountName = username
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun loginFail(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        presenter = LoginPresenter(this, this)
    }

    @OnClick(R.id.login)
    fun login() {
        val password: String = passWordEt.text.toString()
        val username: String = usernameEt.text.toString()
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入账号或者密码", Toast.LENGTH_SHORT).show()
            return
        }
        presenter.login(username, password)
    }

    @OnClick(R.id.register)
    fun register() {
        titleTx.text = StringUtils.getString(R.string.register)
        loginBtn.text = StringUtils.getString(R.string.register_and_login)
        passWordEt.setText("")
        usernameEt.setText("")
    }

    @OnClick(R.id.back)
    fun back() {
        finish()
    }
}