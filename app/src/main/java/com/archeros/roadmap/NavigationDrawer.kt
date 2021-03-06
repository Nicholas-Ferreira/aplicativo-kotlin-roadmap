package com.archeros.roadmap

import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.drawer_header.*
import kotlinx.android.synthetic.main.drawer_navigation.*
import kotlinx.android.synthetic.main.toolbar.*

open class NavigationDrawer : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val TAG = "Drawer"

    protected fun setConfigDrawer(drawer: DrawerLayout) {
        val toogle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar_view,
            R.string.nav_abrir,
            R.string.nav_fechar
        )
        drawer.addDrawerListener(toogle)
        toogle.syncState()
        drawer_view.setNavigationItemSelectedListener(this)
    }

    protected fun setUserName(nome: String = ""){
        val title = findViewById<TextView>(R.id.drawer_user_name)
        title?.text = nome
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }
}