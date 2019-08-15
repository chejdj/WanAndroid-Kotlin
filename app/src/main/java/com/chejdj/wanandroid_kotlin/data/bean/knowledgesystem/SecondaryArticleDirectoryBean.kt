package com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem

/**
 * Created by zhuyangyang on 2019-08-15
 */

/**
{
"children": [],
"courseId": 13,
"id": 402,
"name": "跨平台应用",
"order": 145001,
"parentChapterId": 293,
"userControlSetTop": false,
"visible": 1
}
 **/
class SecondaryArticleDirectoryBean {
    private var children: List<SecondaryArticleDirectoryBean>? = null
    private var courseId: Int = 0
    private var id: Int = 0
    private var name: String? = null
    private var order: Int = 0
    private var parentChapterId: Int = 0
    private var userControlSetTop: Boolean = false
    private var visible: Int = 0
}