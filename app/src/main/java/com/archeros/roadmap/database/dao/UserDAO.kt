package com.archeros.roadmap.database.dao;

import androidx.room.Dao;
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.archeros.roadmap.entity.Branch
import com.archeros.roadmap.entity.User

@Dao
interface UserDAO {
    @Query("SELECT * FROM user WHERE id=:id")
    fun getById(id: Long): User?

    @Query("SELECT * FROM user")
    fun findAll(): List<User>

    @Insert
    fun insert(user: User)

    @Delete
    fun delete(user: User)
}
