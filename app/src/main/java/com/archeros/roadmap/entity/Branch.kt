package com.archeros.roadmap.entity

import java.io.Serializable

class Branch : Serializable {
    var id:Long = 0
    var name = ""
    var checked = false

    override fun toString(): String {
        return "Branch(name='$name')"
    }
}