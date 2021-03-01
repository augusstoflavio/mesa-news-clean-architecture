package com.augusto.mesanews.cleanarchitecture.presentation.login.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.augusto.mesanews.cleanarchitecture.R
import com.augusto.mesanews.cleanarchitecture.presentation.bases.BaseFragment
import com.augusto.mesanews.cleanarchitecture.presentation.extensions.toast
import com.augusto.mesanews.cleanarchitecture.presentation.login.viewmodel.SignupViewModel
import kotlinx.android.synthetic.main.fragment_signup.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignupFragment : BaseFragment(R.layout.fragment_signup) {

    private val _viewModel: SignupViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar(R.string.action_sign_up)

        observerBaseViewModel(_viewModel)

        setupObservers()

        btn_signup.setOnClickListener {
            _viewModel.signup(
                    editTextTextNameAddress.text.toString(),
                    editTextTextEmail.text.toString(),
                    editTextTextPassword.text.toString()
            )
        }
    }

    private fun setupObservers() {
        _viewModel.loading.observe(viewLifecycleOwner, {
            progressBar.isVisible = it
            btn_signup.isEnabled = !it
        })

        _viewModel.signinFormState.observe(viewLifecycleOwner, {
            editTextTextNameAddress.error = it.nameError?.let { it1 -> getString(it1) }
            editTextTextEmail.error = it.usernameError?.let { it1 -> getString(it1) }
            editTextTextPassword.error = it.passwordError?.let { it1 -> getString(it1) }
        })

        _viewModel.signupResult.observe(viewLifecycleOwner, {
            if (it) {
                view?.findNavController()?.popBackStack()
                toast(getString(R.string.signup_success))
            }
        })
    }
}