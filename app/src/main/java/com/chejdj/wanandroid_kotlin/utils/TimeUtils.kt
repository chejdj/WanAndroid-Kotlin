package com.chejdj.wanandroid_kotlin.utils

import java.text.SimpleDateFormat
import java.util.*

class TimeUtils {
    companion object {
        fun timeToString(time: Long): String {
            val simpleDateFormate = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.CHINA)
            return simpleDateFormate.format(time)
        }
    }
}