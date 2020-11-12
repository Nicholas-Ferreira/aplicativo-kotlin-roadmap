package com.archeros.roadmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem

open class DebugActivity : AppCompatActivity() {
    private val TAG = "DebugActivity"
    private val className: String
    get() {
        val s = javaClass.name
        return s.substring(s.lastIndexOf("."))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "$className onCreate chamado")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "$className onStart chamado")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "$className onResume chamado")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "$className onStop chamado")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "$className onRestart chamado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "$className onDestroy chamado")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}