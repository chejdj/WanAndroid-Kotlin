package com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem

import com.chejdj.wanandroid_kotlin.data.bean.article.Article

/**
 * Created by zhuyangyang on 2019-08-15
 */
/**
 * /*
{
"children": [],
"courseId": 13,
"id": 60,
"name": "Android Studio相关",
"order": 1000,
"parentChapterId": 150,
"userControlSetTop": false,
"visible": 1
}
*/
 */
class PrimaryArticleDirectoryBean {
    private var children: List<Article>? = null
    private var courseId: Int = 0
    private var id: Int = 0
    private var name: String? = null
    private var order: Int = 0
    private var parentChapterId: Int = 0
    private var userControlSetTop: Boolean = false
    private var visible: Int = 0
}