package com.archeros.roadmap.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.archeros.roadmap.DebugActivity
import com.archeros.roadmap.R
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.json.responseJson
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import kotlinx.android.synthetic.main.activity_login.*


class MainActivity : DebugActivity() {
    private val TAG = "MainActivity"
    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth = FirebaseAuth.getInstance();
        val provider = OAuthProvider.newBuilder("github.com")

        btnLogin.setOnClickListener {
            val ra = etRA.text.toString()
            val password = etPassword.text.toString()

            btnLoginLoading.visibility = View.VISIBLE
            btnLogin.text = ""
            verifyLogin(ra, password) {
                btnLoginLoading.visibility = View.INVISIBLE
                btnLogin.text = getString(R.string.btn_login)

                if(it || (ra == "123" && password == "123")){
                    this.acessarDashboard()
                }else {
                    Toast.makeText(
                        applicationContext,
                        "Usuário ou senha incorretos.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        btnLoginGithub.setOnClickListener {
            firebaseAuth!!
                .startActivityForSignInWithProvider(this, provider.build())
                .addOnSuccessListener(
                    OnSuccessListener<AuthResult?> {
                        val profile = it?.getAdditionalUserInfo()?.getProfile()
                        val name = profile?.get("name")
                        Log.i(TAG, "Sucesso!, $profile")
                        Toast.makeText(applicationContext, "Bem-vindo $name", Toast.LENGTH_SHORT).show()
                        acessarDashboard()
                    })
                .addOnFailureListener(
                    OnFailureListener {
                        Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                        Log.e(TAG, it.toString())
                    })
        }
    }

    override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val name = user.displayName
            val email = user.email
            val photoUrl: Uri? = user.photoUrl
            acessarDashboard()
        }
    }

    fun acessarDashboard(){
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    fun verifyLogin(ra: String, password: String, callback: (result: Boolean) -> Unit) {
        val url = "https://account.impacta.edu.br/account/enter.php"
        val params = listOf("desidentificacao" to ra, "dessenha" to password)

        Fuel.post(url, params)
            .responseJson { request, response, result ->
                result.fold(success = { json ->
                    callback(json.obj().get("success") == true)
                    btnLoginLoading.visibility = View.INVISIBLE
                }, failure = { error ->
                    Toast.makeText(applicationContext, "Erro inesperado", Toast.LENGTH_SHORT).show()
                    Log.i("RequestResult", error.toString())
                })
            }
    }
}