package com.archeros.roadmap.entity;
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable;

@Entity(tableName = "repositorio")
class Repositorio : Serializable {

    @PrimaryKey
    var id:Long = 0

    var name = ""
    var photo = ""

    override fun toString(): String {
        return "Repositorio(name='$name')"
    }
}
