package com.archeros.aula

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.toolbar.*

open class NavigationDrawer : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val TAG = "Drawer"
    private val className: String
    get() {
        val s = javaClass.name
        return s.substring(s.lastIndexOf("."))
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "$className:Drawer -> initialize")
        this.configDrawer()
    }

    private fun configDrawer() {
        Log.d(TAG, "$className:Drawer -> config")
        val toogle = ActionBarDrawerToggle(
            this@NavigationDrawer,
            drawer,
            toolbar_view,
            R.string.nav_abrir,
            R.string.nav_fechar
        )
        drawer.addDrawerListener(toogle)
        toogle.syncState()
        drawer_view.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }
}