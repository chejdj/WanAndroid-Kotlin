package com.chejdj.wanandroid_kotlin.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

abstract class BaseActivity : AppCompatActivity(),CoroutineScope by MainScope(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        ButterKnife.bind(this)
        initView()
    }

    abstract fun getLayoutId(): Int
    abstract fun initView()
}
