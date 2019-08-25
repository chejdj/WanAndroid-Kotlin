package com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem

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
    var children: List<SecondaryArticleDirectoryBean>? = null
    var courseId: Int = 0
    var id: Int = 0
    var name: String? = null
    var order: Int = 0
    var parentChapterId: Int = 0
    var userControlSetTop: Boolean = false
    var visible: Int = 0
}