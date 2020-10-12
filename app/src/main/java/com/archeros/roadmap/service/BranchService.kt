package com.archeros.roadmap.service

import android.content.Context
import com.archeros.roadmap.entity.Branch

object BranchService {
    fun getFundamental(context: Context):  List<Branch> {
        val branches = mutableListOf<Branch>()

        val b1 = Branch()
        b1.name = "Git - Version Control"
        branches.add(b1)

        val b2 = Branch()
        b2.name = "Basic Terminal Usage"
        branches.add(b2)

        val b3 = Branch()
        b3.name = "Data Structures & Algorithms"
        branches.add(b3)

        val b4 = Branch()
        b4.name = "GitHub"
        branches.add(b4)

        val b5 = Branch()
        b5.name = "Licenses"
        branches.add(b5)

        val b6 = Branch()
        b6.name = "Semantic Versioning"
        branches.add(b6)

        val b7 = Branch()
        b7.name = "SSH"
        branches.add(b7)

        val b8 = Branch()
        b8.name = "HTTP/HTTPS and APIs"
        branches.add(b8)

        val b9 = Branch()
        b9.name = "Design Patterns"
        branches.add(b9)

        val b10 = Branch()
        b10.name = "Character Encodings"
        branches.add(b10)

        return branches
    }
}