package com.augusto.mesanews.cleanarchitecture.presentation.news

import android.os.Bundle
import android.view.View
import com.augusto.mesanews.cleanarchitecture.R
import com.augusto.mesanews.cleanarchitecture.presentation.bases.BaseFragment
import com.augusto.mesanews.cleanarchitecture.presentation.news.viewmodel.NewsHomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsHomeFragment : BaseFragment(R.layout.fragment_news_home) {

    private val _viewModel: NewsHomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observerBaseViewModel(_viewModel)

        _viewModel.getHighlights()
        _viewModel.getNews(1)
    }

}