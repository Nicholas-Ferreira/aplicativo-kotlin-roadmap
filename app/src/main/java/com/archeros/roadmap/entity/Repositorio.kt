package com.archeros.roadmap.entity;
import java.io.Serializable;

class Repositorio : Serializable {
    var id:Long = 0
    var nome = ""
    var foto = ""

    override fun toString(): String {
        return "Repositorio(nome='$nome')"
    }
}
