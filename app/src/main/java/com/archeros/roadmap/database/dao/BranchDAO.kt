package com.archeros.roadmap.database.dao;

import androidx.room.Dao;
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.archeros.roadmap.entity.Branch

@Dao
interface BranchDAO {
    @Query("SELECT * FROM branch WHERE id=:id")
    fun getById(id: Long): Branch?

    @Query("SELECT * FROM branch")
    fun findAll(): List<Branch>

    @Insert
    fun insert(branch: Branch)

    @Delete
    fun delete(branch: Branch)
}
