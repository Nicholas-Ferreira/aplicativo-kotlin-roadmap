package com.archeros.roadmap.entity;
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable;

@Entity(tableName = "user")
class User : Serializable {

    @PrimaryKey
    var id:Long = 0

    var nome = ""
    var email = ""
    var senha = ""

    override fun toString(): String {
        return "Usuairo(nome='$nome')"
    }

    fun toJson(): String {
        return "{\"name\": \"$nome\", \"email\": \"$email\", \"senha\": \"$senha\"}"
    }
}
