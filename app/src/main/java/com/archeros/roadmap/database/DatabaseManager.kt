package com.archeros.roadmap.database

import androidx.room.Room
import com.archeros.roadmap.core.MyApplication
import com.archeros.roadmap.database.dao.BranchDAO
import com.archeros.roadmap.database.dao.RepositorioDAO
import com.archeros.roadmap.database.dao.UserDAO
import com.archeros.roadmap.entity.User

object DatabaseManager {
    private var dbInstance: Database
    init {
        val context = MyApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
            context,
            Database::class.java,
            "roudmap"
        ).build()
    }
    fun getUserDAO(): UserDAO {
        return dbInstance.userDAO()
    }

    fun getRepositorioDAO(): RepositorioDAO {
        return dbInstance.repositorioDAO()
    }

    fun getBranchDAO(): BranchDAO {
        return dbInstance.branchDAO()
    }
}