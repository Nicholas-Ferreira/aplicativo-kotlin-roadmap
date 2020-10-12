package com.archeros.roadmap.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.archeros.roadmap.R
import com.archeros.roadmap.adapter.BranchAdapter
import com.archeros.roadmap.adapter.RepositoriosAdapter
import com.archeros.roadmap.entity.Branch
import com.archeros.roadmap.entity.Repositorio
import com.archeros.roadmap.service.BranchService
import com.archeros.roadmap.service.RepositorioService
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_repositorio.*
import kotlinx.android.synthetic.main.toolbar.*

class RepositorioActivity : AppCompatActivity() {
    private val context: Context get() = this
    private var branches = listOf<Branch>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositorio)
        setSupportActionBar(toolbar_view)
        val repositorio = intent.getSerializableExtra("repositorio") as Repositorio
        supportActionBar?.title = repositorio.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fundamental_id: Long = 1
        if(repositorio.id == fundamental_id){
            this.getDisciplinas()
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun getDisciplinas() {
        this.branches = BranchService.getFundamental(context)
        RecyclerViewBranches?.adapter = BranchAdapter(branches) {openLearnActivity(it)}
    }

    fun openLearnActivity(branch: Branch) {
        Toast.makeText(this, "Em Breve", Toast.LENGTH_SHORT).show()
    }
}