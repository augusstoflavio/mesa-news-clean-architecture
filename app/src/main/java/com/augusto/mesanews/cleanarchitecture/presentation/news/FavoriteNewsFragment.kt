package com.augusto.mesanews.cleanarchitecture.presentation.news

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.augusto.mesanews.cleanarchitecture.R
import com.augusto.mesanews.cleanarchitecture.presentation.bases.BaseFragment
import com.augusto.mesanews.cleanarchitecture.presentation.news.adapter.NewsAdapter
import com.augusto.mesanews.cleanarchitecture.presentation.news.presentation.NewsPresentation
import com.augusto.mesanews.cleanarchitecture.presentation.news.viewmodel.FavoriteNewsViewModel
import kotlinx.android.synthetic.main.fragment_favorite_news.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteNewsFragment : BaseFragment(R.layout.fragment_favorite_news) {

    private val _viewModel: FavoriteNewsViewModel by viewModel()
    private val _newsAdapter: NewsAdapter = NewsAdapter(::viewNews, ::favoriteNews)
    private lateinit var _navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerBaseViewModel(_viewModel)
        _navController = Navigation.findNavController(view)
        setupObservers()
        setupList()
        initToolbar(R.string.title_favorites)
    }

    private fun setupList() {
        list_favorites.adapter = _newsAdapter
        list_favorites.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupObservers() {
        _viewModel.news.observe(viewLifecycleOwner, {
            _newsAdapter.update(it)
        })

        _viewModel.loading.observe(viewLifecycleOwner, {
            progress.isVisible = it
        })
    }

    private fun viewNews(newsPresentation: NewsPresentation) {
        _navController.navigate(
                FavoriteNewsFragmentDirections.toViewer(newsPresentation)
        )
    }

    private fun favoriteNews(newsPresentation: NewsPresentation, favorite: Boolean) {
        _viewModel.disfavorNews(newsPresentation)
    }
}