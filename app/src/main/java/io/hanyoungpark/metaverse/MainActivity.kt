package io.hanyoungpark.metaverse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging
import io.hanyoungpark.metaverse.view.membership.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(mainLooper).postDelayed({
            val loginIntent = Intent(this, LoginActivity::class.java).apply {
                flags  = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            startActivity(loginIntent)
        }, 4000)
    }
}
