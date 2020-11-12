package com.archeros.roadmap.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.archeros.roadmap.R
import com.archeros.roadmap.adapter.RepositoriosAdapter
import com.archeros.roadmap.entity.User
import com.archeros.roadmap.service.RepositorioService
import com.archeros.roadmap.service.UserService
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.toolbar.*

class CadastroActivity : AppCompatActivity() {
    private val TAG = "Cadastro"
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        setSupportActionBar(toolbar_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Cadastro"

        btnCadastro.setOnClickListener{ handleFinalizarCadastro(it) }
    }

    fun handleFinalizarCadastro(v: View) {
        val nome = etNome.text.toString()
        val email = etEmail.text.toString()
        val senha = etSenha.text.toString()
        val senha2 = etSenha2.text.toString()

        if(nome.isEmpty()) return etNome.setError("Digite o seu nome")
        if(email.isEmpty()) return etEmail.setError("Digite o seu e-mail")
        if(senha.isEmpty()) return etSenha.setError("Digite o sua senha")
        if(senha2.isEmpty()) return etSenha2.setError("Confirme a sua senha")

        if(senha != senha2) return etSenha2.setError("Sua senha esta divergente")

        val user = User()
        user.nome = nome
        user.email = email
        user.senha = senha

        Log.i(TAG, "Cadastro OK")
        btnCadastroLoading.visibility = View.VISIBLE
        btnCadastro.text = ""
        Thread {
            this.user = UserService.realizarCadastro(user)
            runOnUiThread {
                Toast.makeText(this,"Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }.start()
    }

}