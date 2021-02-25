package com.augusto.mesanews.cleanarchitecture.presentation.login.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.augusto.mesanews.cleanarchitecture.R
import kotlinx.android.synthetic.main.fragment_login_home.*

class LoginHomeFragment : Fragment() {

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
    }
}