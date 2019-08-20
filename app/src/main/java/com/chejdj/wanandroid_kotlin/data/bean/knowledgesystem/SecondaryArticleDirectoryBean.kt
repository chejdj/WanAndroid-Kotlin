package com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem

import com.chejdj.wanandroid_kotlin.data.bean.article.Article

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
    var children: List<Article>? = null
    var courseId: Int = 0
    var id: Int = 0
    var name: String? = null
    var order: Int = 0
    var parentChapterId: Int = 0
    var userControlSetTop: Boolean = false
    var visible: Int = 0
}