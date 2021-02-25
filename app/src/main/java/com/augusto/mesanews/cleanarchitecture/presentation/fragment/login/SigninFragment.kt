package com.augusto.mesanews.cleanarchitecture.presentation.fragment.login

import android.os.Bundle
import android.view.View
import com.augusto.mesanews.cleanarchitecture.R
import com.augusto.mesanews.cleanarchitecture.presentation.fragment.BaseFragment
import com.augusto.mesanews.cleanarchitecture.presentation.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SigninFragment : BaseFragment(R.layout.fragment_signin) {

    private val _viewModel: LoginViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel

//        observerBaseViewModel(_viewModel)
    }

}