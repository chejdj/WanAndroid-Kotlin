package com.chejdj.wanandroid_kotlin.ui

import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import butterknife.BindView
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.ui.architecture.ArchitectureFragment
import com.chejdj.wanandroid_kotlin.ui.base.BaseActivity
import com.chejdj.wanandroid_kotlin.ui.home.HomeFragment
import com.chejdj.wanandroid_kotlin.ui.me.MeFragment
import com.chejdj.wanandroid_kotlin.ui.project.ProjectFragment
import com.chejdj.wanandroid_kotlin.ui.wechatstub.WechatStubFragment

class MainActivity : BaseActivity() {
    @BindView(R.id.navigate)
    lateinit var navigate: BottomNavigationView
    private val fragmentList: ArrayList<Fragment> = ArrayList()
    private var lastFragment: Int = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        fragmentList.add(HomeFragment())
        fragmentList.add(ArchitectureFragment())
        fragmentList.add(WechatStubFragment())
        fragmentList.add(ProjectFragment())
        fragmentList.add(MeFragment())
        supportFragmentManager.beginTransaction().add(R.id.content, fragmentList[0])
            .add(R.id.content, fragmentList[1])
            .add(R.id.content, fragmentList[2])
            .add(R.id.content, fragmentList[3])
            .add(R.id.content, fragmentList[4])
            .hide(fragmentList[1])
            .hide(fragmentList[2])
            .hide(fragmentList[3])
            .hide(fragmentList[4])
            .show(fragmentList[0])
            .commit()
        lastFragment = 0
        navigate.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    if (lastFragment != 0) {
                        showNextFragment(lastFragment, 0)
                        lastFragment = 0
                    }
                }
                R.id.architecture -> {
                    if (lastFragment != 1) {
                        showNextFragment(lastFragment, 1)
                        lastFragment = 1
                    }
                }
                R.id.wechatStub -> {
                    if (lastFragment != 2) {
                        showNextFragment(lastFragment, 2)
                        lastFragment = 2
                    }
                }
                R.id.project -> {
                    if (lastFragment != 3) {
                        showNextFragment(lastFragment, 3)
                        lastFragment = 3
                    }
                }
                R.id.me -> {
                    if (lastFragment != 4) {
                        showNextFragment(lastFragment, 4)
                        lastFragment = 4
                    }
                }
            }
            true
        }

    }

    private fun showNextFragment(lastFragment: Int, currentFragment: Int) {
        supportFragmentManager.beginTransaction().hide(fragmentList[lastFragment])
            .show(fragmentList[currentFragment]).commitAllowingStateLoss()
    }


}