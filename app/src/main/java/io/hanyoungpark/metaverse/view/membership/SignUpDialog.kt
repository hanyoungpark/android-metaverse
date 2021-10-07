package io.hanyoungpark.metaverse.view.membership

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import io.hanyoungpark.metaverse.R
import io.hanyoungpark.metaverse.databinding.DialogSignupBinding
import io.hanyoungpark.metaverse.viewmodels.MembershipViewModel

@AndroidEntryPoint
class SignUpDialog : BottomSheetDialogFragment() {
    private val membershipViewModel: MembershipViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: DialogSignupBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.dialog_signup,
                container,
                false)
        binding.membershipViewModel = membershipViewModel
        return binding.root
    }
}
