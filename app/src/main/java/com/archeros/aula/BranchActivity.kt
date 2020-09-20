package com.archeros.aula

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.toolbar.*

class BranchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_branch)
        setSupportActionBar(toolbar_view)
        val args = intent.extras
        val branchName = args?.getString("branch")
        supportActionBar?.title = branchName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }
}