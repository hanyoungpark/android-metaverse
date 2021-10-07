package io.hanyoungpark.metaverse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.hanyoungpark.metaverse.view.membership.LoginActivity
import io.hanyoungpark.metaverse.viewmodels.MembershipViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val membershipViewModel: MembershipViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindLiveData()
    }

    override fun onStart() {
        super.onStart()
        membershipViewModel.fetchUser()
    }

    private fun bindLiveData() {
        membershipViewModel.user.observe(this, {
            if (it == null) {
                openLoginActivity()
                return@observe
            }
            Log.d("MainActivity", "success")
        })
    }

    private fun openLoginActivity() {
        val loginIntent = Intent(this, LoginActivity::class.java)
        loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(loginIntent)
    }
}
