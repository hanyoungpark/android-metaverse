package io.hanyoungpark.metaverse.view.membership

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.hanyoungpark.metaverse.R
import io.hanyoungpark.metaverse.viewmodels.MembershipViewModel

class SignUpDialog : BottomSheetDialogFragment() {
    private val membershipViewModel: MembershipViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.dialog_signup, container, false)
        return v
    }
}
