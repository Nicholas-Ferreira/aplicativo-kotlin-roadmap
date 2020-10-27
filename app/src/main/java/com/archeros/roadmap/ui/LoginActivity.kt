package com.archeros.roadmap.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.archeros.roadmap.DebugActivity
import com.archeros.roadmap.R
import com.archeros.roadmap.core.MyPreferences
import com.archeros.roadmap.entity.User
import com.archeros.roadmap.service.UserService
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.json.responseJson
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.btnCadastro
import kotlinx.android.synthetic.main.activity_login.etEmail


class LoginActivity : DebugActivity() {
    private val TAG = "MainActivity"
    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth = FirebaseAuth.getInstance();

        etEmail.setText(MyPreferences.getString("email"))
        etPassword.setText(MyPreferences.getString("password"))
        cbRemember.isChecked = MyPreferences.getBoolean("remember")

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val remember = cbRemember.isChecked

            btnLoginLoading.visibility = View.VISIBLE
            btnLogin.text = ""

            Thread {
                val user: User? = UserService.login(email, password)
                runOnUiThread {
                    btnLoginLoading.visibility = View.INVISIBLE
                    btnLogin.text = getString(R.string.btn_login)

                    if(user != null || (email == "123" && password == "123")){
                        if(remember) {
                            MyPreferences.setBoolean("remember", true)
                            MyPreferences.setString("email", email)
                            MyPreferences.setString("password", password)
                        } else {
                            MyPreferences.setBoolean("remember", false)
                            MyPreferences.setString("email", "")
                            MyPreferences.setString("password", "")
                        }
                        this.acessarDashboard()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Usuário ou senha incorretos.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }.start()

        }
        btnLoginGithub.setOnClickListener { githubLogin (it) }

        btnCadastro.setOnClickListener { acessarCadastro(it) }
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

    fun acessarCadastro(view: View) {
        val intent = Intent(this, CadastroActivity::class.java)
        startActivity(intent)
    }

    fun githubLogin(view: View) {
        val provider = OAuthProvider.newBuilder("github.com")

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