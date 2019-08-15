package com.chejdj.wanandroid_kotlin.data.bean.article

class ArticleData {
    private var curPage: Int = 0
    private var datas: List<Article>? = null
    private var offset: Int = 0
    private var pageCount: Int = 0
    private var size: Int = 0
    private var total: Int = 0
}