package com.example.laoshitest.utils

import android.content.Context
import androidx.core.os.ConfigurationCompat

class Utils {
    companion object {
        fun getLng(context: Context): String {
            return ConfigurationCompat.getLocales(context.resources.configuration)[0].language
        }
    }
}