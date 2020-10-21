package com.archeros.roadmap.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "branch")
class Branch : Serializable {

    @PrimaryKey
    var id:Long = 0

    var name = ""
    var checked = false
    var percent = 0

    override fun toString(): String {
        return "Branch(name='$name')"
    }
}