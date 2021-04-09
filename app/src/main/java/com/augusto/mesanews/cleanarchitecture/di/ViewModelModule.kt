package com.augusto.mesanews.cleanarchitecture.di

import com.augusto.mesanews.cleanarchitecture.presentation.login.viewmodel.LoginHomeViewModel
import com.augusto.mesanews.cleanarchitecture.presentation.login.viewmodel.SigninViewModel
import com.augusto.mesanews.cleanarchitecture.presentation.login.viewmodel.SignupViewModel
import com.augusto.mesanews.cleanarchitecture.presentation.news.viewmodel.FavoriteNewsViewModel
import com.augusto.mesanews.cleanarchitecture.presentation.news.viewmodel.NewsHomeViewModel
import com.augusto.mesanews.core.domain.useCase.DisfavorNews
import com.augusto.mesanews.core.domain.useCase.FavoriteNews
import com.augusto.mesanews.core.domain.useCase.GetHighlights
import com.augusto.mesanews.core.domain.useCase.GetNews
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        SigninViewModel(get(), get(), Dispatchers.IO)
    }

    viewModel {
        SignupViewModel(get(), Dispatchers.IO)
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