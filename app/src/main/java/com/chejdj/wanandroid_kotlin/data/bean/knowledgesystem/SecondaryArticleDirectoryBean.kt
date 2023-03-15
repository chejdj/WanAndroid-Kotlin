package com.chejdj.wanandroid_kotlin.data.bean.knowledgesystem

import android.os.Parcel
import android.os.Parcelable
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
class SecondaryArticleDirectoryBean() : Parcelable {
    var children: List<Article>? = null
    var courseId: Int = 0
    var id: Int = 0
    var name: String = ""
    var order: Int = 0
    var parentChapterId: Int = 0
    var userControlSetTop: Boolean = false
    var visible: Int = 0

    constructor(parcel: Parcel) : this() {
        courseId = parcel.readInt()
        id = parcel.readInt()
        name = parcel.readString().toString()
        order = parcel.readInt()
        parentChapterId = parcel.readInt()
        userControlSetTop = parcel.readByte() != 0.toByte()
        visible = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(courseId)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(order)
        parcel.writeInt(parentChapterId)
        parcel.writeByte(if (userControlSetTop) 1 else 0)
        parcel.writeInt(visible)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SecondaryArticleDirectoryBean> {
        override fun createFromParcel(parcel: Parcel): SecondaryArticleDirectoryBean {
            return SecondaryArticleDirectoryBean(parcel)
        }

        override fun newArray(size: Int): Array<SecondaryArticleDirectoryBean?> {
            return arrayOfNulls(size)
        }
    }
}