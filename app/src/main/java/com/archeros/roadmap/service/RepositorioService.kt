package com.archeros.roadmap.service

import android.content.Context
import com.archeros.roadmap.entity.Repositorio
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object RepositorioService: api() {

    fun getRepositories(): List<Repositorio> {
        val response = get("/repository")
        return parserJson(response)
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}