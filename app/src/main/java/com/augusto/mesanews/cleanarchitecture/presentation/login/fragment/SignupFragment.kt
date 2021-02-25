package com.augusto.mesanews.cleanarchitecture.presentation.login.fragment

import android.os.Bundle
import android.view.View
import com.augusto.mesanews.cleanarchitecture.R
import com.augusto.mesanews.cleanarchitecture.presentation.bases.BaseFragment

class SignupFragment : BaseFragment(R.layout.fragment_signup) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar(R.string.action_sign_up)
    }
}