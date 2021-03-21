package com.augusto.mesanews.cleanarchitecture.di

import com.augusto.mesanews.cleanarchitecture.presentation.login.viewmodel.LoginHomeViewModel
import com.augusto.mesanews.cleanarchitecture.presentation.login.viewmodel.SigninViewModel
import com.augusto.mesanews.cleanarchitecture.presentation.login.viewmodel.SignupViewModel
import com.augusto.mesanews.cleanarchitecture.presentation.news.viewmodel.FavoriteNewsViewModel
import com.augusto.mesanews.cleanarchitecture.presentation.news.viewmodel.NewsHomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        SigninViewModel(get(), get())
    }

    viewModel {
        SignupViewModel(get())
    }

    viewModel {
        LoginHomeViewModel(get())
    }

    viewModel {
        NewsHomeViewModel(get())
    }

    viewModel {
        FavoriteNewsViewModel(get())
    }
}