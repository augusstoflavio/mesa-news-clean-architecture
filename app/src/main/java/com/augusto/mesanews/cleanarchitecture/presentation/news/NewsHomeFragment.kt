package com.augusto.mesanews.cleanarchitecture.presentation.news

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.augusto.mesanews.cleanarchitecture.R
import com.augusto.mesanews.cleanarchitecture.presentation.bases.BaseFragment
import com.augusto.mesanews.cleanarchitecture.presentation.news.adapter.HighlightAdapter
import com.augusto.mesanews.cleanarchitecture.presentation.news.adapter.NewsAdapter
import com.augusto.mesanews.cleanarchitecture.presentation.news.presentation.NewsPresentation
import com.augusto.mesanews.cleanarchitecture.presentation.news.viewmodel.NewsHomeViewModel
import kotlinx.android.synthetic.main.fragment_news_home.*
import kotlinx.android.synthetic.main.toolbar_news.*
import kotlinx.android.synthetic.main.toolbar_news.toolbar
import kotlinx.android.synthetic.main.toolbar_news.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsHomeFragment : BaseFragment(R.layout.fragment_news_home) {

    private val _viewModel: NewsHomeViewModel by viewModel()
    private val _newsAdapter: NewsAdapter = NewsAdapter(::viewNews, ::favoriteNews)
    private lateinit var _navController: NavController
    private val _highlightAdapter: HighlightAdapter = HighlightAdapter(::viewNews, ::favoriteNews)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observerBaseViewModel(_viewModel)

        _viewModel.getHighlights()
        _viewModel.getNews()

        setupLists()
        setupObservers()

        _navController = Navigation.findNavController(view)

        activity?.setActionBar(toolbar)
        toolbar.ic_favorites_news.setOnClickListener {
            _navController.navigate(
                    NewsHomeFragmentDirections.toFavorites()
            )
        }
    }

    private fun setupObservers() {
        _viewModel.news.observe(viewLifecycleOwner, {
            _newsAdapter.insertItens(it)
        })

        _viewModel.highlights.observe(viewLifecycleOwner, {
            _highlightAdapter.update(it)
        })

        _viewModel.loading.observe(viewLifecycleOwner, {
            progress_bar.isVisible = it
        })

        _viewModel.updatedNews.observe(viewLifecycleOwner, {
            it?.let { newsPresentation ->
                _newsAdapter.updateItem(newsPresentation)
                _highlightAdapter.updateItem(newsPresentation)
            }
        })
    }

    private fun setupLists() {
        list_news.adapter = _newsAdapter
        list_news.layoutManager = LinearLayoutManager(requireContext())
        nested_scrool_view.setOnScrollChangeListener { v, _, scrollY, _, oldScrollY ->
            if ((scrollY >= (list_news.measuredHeight - v.measuredHeight)) &&
                    scrollY > oldScrollY) {

                if (!_viewModel.loading.value!!) {
                    _viewModel.getNews()
                }
            }
        }

        list_highlight.adapter = _highlightAdapter
        list_highlight.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun viewNews(newsPresentation: NewsPresentation) {
        _navController.navigate(
            NewsHomeFragmentDirections.toViewer(newsPresentation)
        )
    }

    private fun favoriteNews(newsPresentation: NewsPresentation, favorite: Boolean) {
        _viewModel.favoriteNews(newsPresentation, favorite)
    }
}