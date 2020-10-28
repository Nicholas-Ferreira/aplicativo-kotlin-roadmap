package com.archeros.roadmap.core

import android.content.Intent
import android.util.Log
import com.archeros.roadmap.R
import com.archeros.roadmap.ui.DashboardActivity
import com.archeros.roadmap.util.NotificationUtil
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService: FirebaseMessagingService() {
    val TAG = "Firebase"

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Novo token: $token")
        MyPreferences.setString("FB_TOKEN", token)
    }

    override fun onMessageReceived(mensagemRemota: RemoteMessage?) {
        Log.d(TAG, "onMessageReceived")

        // verifica se a mensagem recebida do firebase é uma notificação
        if (mensagemRemota?.notification != null) {
            val titulo = mensagemRemota.notification?.title
            val corpo = mensagemRemota.notification?.body
            Log.d(TAG, "Titulo da mensagem: $titulo")
            Log.d(TAG, "Corpo da mensagem: $corpo")

            showNotification(mensagemRemota)
        }
    }

    private fun showNotification(mensagemRemota: RemoteMessage) {
        val intent = Intent(this, DashboardActivity::class.java)

        val titulo = mensagemRemota.notification?.title?: getString(R.string.app_name)
        val mensagem = mensagemRemota.notification?.body!!

        NotificationUtil.create(this, 1, intent, titulo, mensagem)
    }
}