package com.archeros.roadmap.service

import android.content.Context
import android.util.Log
import com.archeros.roadmap.database.DatabaseManager
import com.archeros.roadmap.entity.Branch
import com.archeros.roadmap.entity.Repositorio
import com.archeros.roadmap.entity.User
import com.archeros.roadmap.util.Network
import com.archeros.roadmap.util.Network.isInternetAvailable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object UserService : api() {

    fun realizarCadastro(user: User): User {
        val dao = DatabaseManager.getUserDAO()

        if(!Network.isInternetAvailable()){
            dao.insert(user)
            return user
        }

        val response = this.post("user", user.toJson())
        return parserJson<User>(response)
    }

    fun login(email: String, senha: String): User? {
        val dao = DatabaseManager.getUserDAO()

        val response = this.post("login", "{\"email\": \"$email\", \"senha\": \"$senha\"}")
        if(response.isEmpty()) return null

        return parserJson<User>(response)
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}