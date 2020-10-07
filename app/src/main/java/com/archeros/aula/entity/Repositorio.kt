package com.archeros.aula.entity;
import java.io.Serializable;

class Repositorio : Serializable {
    var id:Long = 0
    var nome = ""
    var ementa = ""
    var foto = ""
    var professor = ""

    override fun toString(): String {
        return "Disciplina(nome='$nome')"
    }
}
