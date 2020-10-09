package com.archeros.roadmap.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.archeros.roadmap.NavigationDrawer
import com.archeros.roadmap.R
import com.archeros.roadmap.adapter.RepositoriosAdapter
import com.archeros.roadmap.entity.Repositorio
import com.archeros.roadmap.service.RepositorioService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.toolbar.*

class DashboardActivity : NavigationDrawer() {
    private val context: Context get() = this
    private var repositios = listOf<Repositorio>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(toolbar_view)
        setConfigDrawer(drawer_dashboard)
        RecyclerViewRepositorios?.layoutManager = LinearLayoutManager(context)
        RecyclerViewRepositorios?.itemAnimator = DefaultItemAnimator()
        RecyclerViewRepositorios?.setHasFixedSize(true)
        supportActionBar?.title = getString(R.string.title_dashboard)

        //btnEssencial.setOnClickListener { openBranchActivity("Essencial") }
        //btnFrontend.setOnClickListener { openBranchActivity("Front-End") }
        //btnBackend.setOnClickListener { openBranchActivity("Back-End") }
        //btnDevops.setOnClickListener { openBranchActivity("DevOps") }
    }

    override fun onResume() {
        super.onResume()
        this.taskRepositorios()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView?)?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                Log.i("SEARCH_TOOLBAR", "toolbar text change: $newText")
                return false
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(context, "Buscando: $query", Toast.LENGTH_SHORT).show()
                if(progressBar.visibility == View.VISIBLE) return false
                progressBar.visibility = View.VISIBLE
                Thread(Runnable {
                    Thread.sleep(1000)
                    progressBar.visibility = View.INVISIBLE
                }).start()
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_atualizar -> this.onLoading()
            R.id.action_config -> {
                var intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
            R.id.action_sair -> {
                FirebaseAuth.getInstance().signOut();
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                this.finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun onLoading() {
        if(progressBar.visibility == View.VISIBLE) return
        progressBar.visibility = View.VISIBLE
        Thread(Runnable {
            Thread.sleep(10000)
            progressBar.visibility = View.INVISIBLE
        }).start()
    }

    fun openRepositorioActivity(repositorio: Repositorio) {
        Toast.makeText(context, "Clicou repositrio ${repositorio.nome}", Toast.LENGTH_SHORT).show()
        var intent = Intent(this, RepositorioActivity::class.java)
        intent.putExtra("repositorio", repositorio)
        startActivity(intent)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_roadmap -> null
            R.id.nav_favoritos -> null
        }
        drawer_dashboard.closeDrawer(GravityCompat.START)
        return true
    }

    fun taskRepositorios() {
        this.repositios = RepositorioService.getDisciplinas(context)
        RecyclerViewRepositorios?.adapter = RepositoriosAdapter(repositios) {openRepositorioActivity(it)}
    }
}