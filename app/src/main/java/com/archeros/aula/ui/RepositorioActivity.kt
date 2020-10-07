package com.archeros.aula.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.archeros.aula.R
import com.archeros.aula.entity.Repositorio
import kotlinx.android.synthetic.main.toolbar.*

class RepositorioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositorio)
        setSupportActionBar(toolbar_view)
        val repositorio = intent.getSerializableExtra("repositorio") as Repositorio
        supportActionBar?.title = repositorio.nome
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}