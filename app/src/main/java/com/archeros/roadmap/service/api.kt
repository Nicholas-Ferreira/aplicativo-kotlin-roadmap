package com.archeros.roadmap.service

import android.util.Log
import com.archeros.roadmap.entity.Branch
import com.archeros.roadmap.util.HttpHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URL

open class api {
    private val HOST = "https://us-central1-roadmap-4e592.cloudfunctions.net/app"

    fun get(route: String): String {
        val url = "$HOST/$route"
        return HttpHelper.get(url)
    }
}