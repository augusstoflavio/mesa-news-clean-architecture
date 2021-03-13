package com.augusto.mesanews.cleanarchitecture.presentation.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.augusto.mesanews.cleanarchitecture.R
import com.augusto.mesanews.cleanarchitecture.presentation.bases.BaseFragment
import com.augusto.mesanews.cleanarchitecture.presentation.news.presentation.NewsPresentation
import kotlinx.android.synthetic.main.fragment_news_viewer.*
import kotlinx.android.synthetic.main.toolbar_news_viewer.*
import kotlinx.android.synthetic.main.toolbar_news_viewer.view.*

class NewsViewerFragment: BaseFragment(R.layout.fragment_news_viewer) {

    private lateinit var _navController: NavController
    private lateinit var _news: NewsPresentation
    private val args: NewsViewerFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _news = args.news
        _navController = Navigation.findNavController(view)

        setupWebview()
        setupToolbar()
    }

    private fun setupToolbar() {
        activity?.setActionBar(toolbar)
        toolbar.toolbar_title.text = _news.title
        toolbar.toolbar_subtitle.text = _news.url
        toolbar.toolbar_back.setOnClickListener {
            _navController.popBackStack()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebview() {
        web_view.loadUrl(_news.url)
        web_view.settings.javaScriptEnabled = true
        web_view.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progress_bar.isVisible = false
            }
        }
    }
}