package com.augusto.mesanews.cleanarchitecture.di

import com.augusto.mesanews.cleanarchitecture.presentation.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        LoginViewModel(get(), get())
    }
}