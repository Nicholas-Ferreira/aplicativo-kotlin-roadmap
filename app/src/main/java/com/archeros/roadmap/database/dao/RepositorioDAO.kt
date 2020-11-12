package com.archeros.roadmap.database.dao;

import androidx.room.*

import com.archeros.roadmap.entity.Repositorio;

@Dao
interface RepositorioDAO {
    @Query("SELECT * FROM repositorio WHERE id=:id")
    fun getById(id: Long): Repositorio?

    @Query("SELECT * FROM repositorio")
    fun findAll(): List<Repositorio>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repositorio: Repositorio)

    @Delete
    fun delete(repositorio: Repositorio)
}
