package com.augusto.mesanews.cleanarchitecture.presentation.login.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.augusto.mesanews.cleanarchitecture.R
import com.augusto.mesanews.cleanarchitecture.presentation.login.viewmodel.LoginHomeViewModel
import com.augusto.mesanews.cleanarchitecture.presentation.login.viewmodel.SigninViewModel
import com.augusto.mesanews.cleanarchitecture.presentation.news.NewsActivity
import kotlinx.android.synthetic.main.fragment_login_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginHomeFragment : Fragment() {

    private val _viewModel: LoginHomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_signin_email.setOnClickListener {
            val action = LoginHomeFragmentDirections.toSigninFragment()
            view.findNavController().navigate(action)
        }

        btn_signup.setOnClickListener {
            val action = LoginHomeFragmentDirections.toSignupFragment()
            view.findNavController().navigate(action)
        }

        setupObservers()
        _viewModel.checkIsLogged()
    }

    private fun setupObservers() {
        //TODO add this check on a splash screen
        _viewModel.isLogged.observe(viewLifecycleOwner, {
            if (it) {
                val intent = Intent(requireContext(), NewsActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(
                    intent
                )
                activity?.finish()
            }
        })
    }
}