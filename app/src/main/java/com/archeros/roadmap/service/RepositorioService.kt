package com.archeros.roadmap.service

import android.content.Context
import com.archeros.roadmap.database.DatabaseManager
import com.archeros.roadmap.entity.Repositorio
import com.archeros.roadmap.util.Network
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object RepositorioService: api() {

    fun getRepositories(): List<Repositorio> {
        val dao = DatabaseManager.getRepositorioDAO()

        if(!Network.isInternetAvailable()){
            return dao.findAll()
        }

        val response = get("/repository")
        val repositorios = parserJson<List<Repositorio>>(response)
        repositorios.map { dao.insert(it) }

        return repositorios
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}