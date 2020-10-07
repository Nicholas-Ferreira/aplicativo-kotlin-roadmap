package com.archeros.aula

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_dashboard.*
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }
}