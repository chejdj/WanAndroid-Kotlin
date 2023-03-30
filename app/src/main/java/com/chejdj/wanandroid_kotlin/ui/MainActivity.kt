package com.chejdj.wanandroid_kotlin.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import butterknife.BindView
import com.chejdj.wanandroid_kotlin.R
import com.chejdj.wanandroid_kotlin.ui.architecture.ArchitectureFragment
import com.chejdj.wanandroid_kotlin.ui.base.BaseActivity
import com.chejdj.wanandroid_kotlin.ui.home.HomeFragment
import com.chejdj.wanandroid_kotlin.ui.me.MeFragment
import com.chejdj.wanandroid_kotlin.ui.project.ProjectFragment
import com.chejdj.wanandroid_kotlin.ui.wechatstub.WechatStubFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : BaseActivity() {
    val storePermissionRequestCode: Int = 1001

    @BindView(R.id.navigate)
    lateinit var navigate: BottomNavigationView
    private val fragmentList: ArrayList<Fragment> = ArrayList()
    private var lastFragment: Int = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestExternalStoragePermission()
    }

    private fun requestExternalStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                intent.data = Uri.parse("package:$packageName")
                val settingsLauncher =
                    registerForActivityResult(
                        ActivityResultContracts.StartActivityForResult()
                    ) { result ->
                        Log.i("Permission", "result: ${result.resultCode}")
                    }
                settingsLauncher.launch(intent)
            }
        } else {
            val granted: Int =
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            if (granted != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    storePermissionRequestCode
                )
            }
        }
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
            .hide(fragmentList[0])
            .hide(fragmentList[1])
            .hide(fragmentList[2])
            .hide(fragmentList[3])
            .hide(fragmentList[4])
            .show(fragmentList[0])
            .commit()
        lastFragment = 0
        navigate.setOnItemSelectedListener {
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == storePermissionRequestCode) {
            Log.i("Permission", "grantResults: " + grantResults + " permissions: " + permissions[0])
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun showNextFragment(lastFragment: Int, currentFragment: Int) {
        supportFragmentManager.beginTransaction().hide(fragmentList[lastFragment])
            .show(fragmentList[currentFragment]).commitAllowingStateLoss()
    }


}