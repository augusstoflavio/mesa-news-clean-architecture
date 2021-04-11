package com.augusto.mesanews.cleanarchitecture.di

import com.augusto.mesanews.cleanarchitecture.data.validator.EmailValidatorImpl
import com.augusto.mesanews.cleanarchitecture.presentation.login.viewmodel.LoginHomeViewModel
import com.augusto.mesanews.cleanarchitecture.presentation.login.viewmodel.SigninViewModel
import com.augusto.mesanews.cleanarchitecture.presentation.login.viewmodel.SignupViewModel
import com.augusto.mesanews.cleanarchitecture.presentation.news.viewmodel.FavoriteNewsViewModel
import com.augusto.mesanews.cleanarchitecture.presentation.news.viewmodel.NewsHomeViewModel
import com.augusto.mesanews.core.domain.useCase.*
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        SigninViewModel(get(), get(), EmailValidatorImpl(), Dispatchers.IO)
    }

    viewModel {
        SignupViewModel(Signup(get()), EmailValidatorImpl(), Dispatchers.IO)
    }

    viewModel {
        LoginHomeViewModel(get())
    }

    viewModel {
        NewsHomeViewModel(
            GetHighlights(get()),
            GetNews(get()),
            FavoriteNews(get()),
            DisfavorNews(get()),
            Dispatchers.IO
        )
    }

    viewModel {
        FavoriteNewsViewModel(get(), Dispatchers.IO)
    }
}