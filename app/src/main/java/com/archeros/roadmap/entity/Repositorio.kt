package com.archeros.roadmap.entity;
import java.io.Serializable;

class Repositorio : Serializable {
    var id:Long = 0
    var name = ""
    var photo = ""

    override fun toString(): String {
        return "Repositorio(name='$name')"
    }
}
