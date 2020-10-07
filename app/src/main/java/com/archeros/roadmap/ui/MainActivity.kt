package com.archeros.roadmap.ui

import android.content.Intent
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
            firebaseAuth!!
                .startActivityForSignInWithProvider(this, provider.build())
                .addOnSuccessListener(
                    OnSuccessListener<AuthResult?> {
                        val profile = it?.getAdditionalUserInfo()?.getProfile()
                        // User is signed in.
                        // IdP data available in
                        // authResult.getAdditionalUserInfo().getProfile().
                        // The OAuth access token can also be retrieved:
                        // authResult.getCredential().getAccessToken().
                        Log.i(TAG, "Sucesso!, $profile")
                        acessarDashboard()
                    })
                .addOnFailureListener(
                    OnFailureListener {
                        Log.e(TAG, it.toString())
                    })
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth!!.currentUser
    }

    fun acessarDashboard(){
        var intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    fun onLogin(ra: String, password: String, callback: (result: Boolean) -> Unit) {
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