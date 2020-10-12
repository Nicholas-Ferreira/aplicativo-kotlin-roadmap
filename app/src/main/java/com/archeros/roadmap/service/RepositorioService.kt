package com.archeros.roadmap.service

import android.content.Context
import com.archeros.roadmap.entity.Repositorio

object RepositorioService {

    fun getDisciplinas (context: Context): List<Repositorio> {
        val repositorios = mutableListOf<Repositorio>()

        val r1 = Repositorio()
        r1.nome = "Fundamental"
        r1.foto = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Closed_Book_Icon.svg/1200px-Closed_Book_Icon.svg.png"
        repositorios.add(r1)

        val r2 = Repositorio()
        r2.nome = "Front-End"
        r2.foto = "https://icon-library.com/images/frontend-icon/frontend-icon-24.jpg"
        repositorios.add(r2)

        val r3 = Repositorio()
        r3.nome = "Back-End"
        r3.foto = "https://hackernoon.com/hn-images/1*GkzKz-wfxLaShBREklifbg.png"
        repositorios.add(r3)

        val r4 = Repositorio()
        r4.nome = "DevOps"
        r4.foto = "https://www.vhv.rs/dpng/d/215-2152054_transparent-model-icon-png-devops-icon-png-download.png"
        repositorios.add(r4)

        return repositorios
    }
}