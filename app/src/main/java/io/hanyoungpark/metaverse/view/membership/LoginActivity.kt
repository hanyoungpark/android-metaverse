package io.hanyoungpark.metaverse.view.membership

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import io.hanyoungpark.metaverse.R
import io.hanyoungpark.metaverse.databinding.ActivityLoginBinding
import io.hanyoungpark.metaverse.viewmodels.MembershipViewModel

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val membershipViewModel: MembershipViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.membershipViewModel = membershipViewModel
        membershipViewModel.user.observe(this, { user ->
            if (user == null) {
                Toast.makeText(this, R.string.login_fail, Toast.LENGTH_SHORT)
                return@observe
            }
        })
    }

    fun signUpButtonClicked(sender: View) {
        val signUpDialog = SignUpDialog()
        signUpDialog.show(supportFragmentManager, null)
    }
}
