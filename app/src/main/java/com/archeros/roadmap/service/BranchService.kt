package com.archeros.roadmap.service

import android.content.Context
import com.archeros.roadmap.entity.Branch
import com.archeros.roadmap.entity.Repositorio
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object BranchService : api() {
    fun getBranchByRepository(repository: Repositorio): List<Branch> {
        val id = repository.id
        val response = get("/repository/$id/branch")
        return parserJson(response)
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}