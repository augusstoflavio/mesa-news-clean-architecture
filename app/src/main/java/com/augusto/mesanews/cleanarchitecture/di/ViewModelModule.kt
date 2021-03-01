package com.augusto.mesanews.cleanarchitecture.di

import com.augusto.mesanews.cleanarchitecture.presentation.login.viewmodel.SigninViewModel
import com.augusto.mesanews.cleanarchitecture.presentation.login.viewmodel.SignupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        SigninViewModel(get(), get())
    }

    viewModel {
        SignupViewModel(get())
    }
}