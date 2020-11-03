package com.archeros.roadmap.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.archeros.roadmap.DebugActivity
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

class RepositorioActivity : DebugActivity() {
    private val context: Context get() = this
    private var branches = listOf<Branch>()
    private var repository: Repositorio? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositorio)
        setSupportActionBar(toolbar_view)
        repository = intent.getSerializableExtra("repositorio") as Repositorio
        supportActionBar?.title = repository!!.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getBranches()
    }

    override fun onResume() {
        super.onResume()
    }

    fun getBranches() {
        Thread {
            this.branches = BranchService.getBranchByRepository(repository!!)
            runOnUiThread {
                RecyclerViewBranches?.adapter = BranchAdapter(branches) {openLearnActivity(it)}
            }
        }.start()
    }

    fun openLearnActivity(branch: Branch) {
        Toast.makeText(this, "Em Breve", Toast.LENGTH_SHORT).show()
    }
}