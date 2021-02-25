package com.augusto.mesanews.cleanarchitecture.presentation.fragment.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.augusto.mesanews.cleanarchitecture.R
import com.augusto.mesanews.cleanarchitecture.presentation.activity.MainActivity
import com.augusto.mesanews.cleanarchitecture.presentation.fragment.BaseFragment
import com.augusto.mesanews.cleanarchitecture.presentation.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_signin.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SigninFragment : BaseFragment(R.layout.fragment_signin) {

    private val _viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar(R.string.log_in_with_email)

        observerBaseViewModel(_viewModel)

        setupObservers()

        btn_signin.setOnClickListener {
            _viewModel.login(editTextTextEmailAddress.text.toString(), editTextTextPassword.text.toString())
        }
    }

    private fun setupObservers() {
        _viewModel.loading.observe(viewLifecycleOwner, {
            progressBar.isVisible = it
            btn_signin.isEnabled = !it
        })

        _viewModel.loginFormState.observe(viewLifecycleOwner, {
            editTextTextEmailAddress.error = it.usernameError?.let { it1 -> getString(it1) }
            editTextTextPassword.error = it.passwordError?.let { it1 -> getString(it1) }
        })

        _viewModel.loginResult.observe(viewLifecycleOwner, {
            if (it) {
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(
                    intent
                )
            }
        })
    }

}