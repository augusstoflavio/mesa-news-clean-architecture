package com.augusto.mesanews.cleanarchitecture.presentation.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.augusto.mesanews.cleanarchitecture.R

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
    }
}