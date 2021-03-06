package com.archeros.roadmap.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.archeros.roadmap.database.dao.BranchDAO
import com.archeros.roadmap.database.dao.RepositorioDAO
import com.archeros.roadmap.database.dao.UserDAO
import com.archeros.roadmap.entity.Branch
import com.archeros.roadmap.entity.Repositorio
import com.archeros.roadmap.entity.User

@Database(entities = [
    User::class,
    Branch::class,
    Repositorio::class
], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun userDAO(): UserDAO
    abstract fun repositorioDAO(): RepositorioDAO
    abstract fun branchDAO(): BranchDAO
}