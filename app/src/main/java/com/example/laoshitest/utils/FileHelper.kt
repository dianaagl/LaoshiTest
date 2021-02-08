package com.example.laoshitest.utils

import android.content.Context

class FileHelper {
    companion object{
        fun getJsonFromRaw(context: Context, resId: Int): String{
            return context.resources.openRawResource(resId).use {
                it.bufferedReader().use{ reader ->
                    reader.readText()
                }
            }
        }
    }
}