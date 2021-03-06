package com.augusto.mesanews.cleanarchitecture.presentation.news.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.augusto.mesanews.cleanarchitecture.R
import com.augusto.mesanews.cleanarchitecture.presentation.bases.BaseAdapter
import com.augusto.mesanews.cleanarchitecture.presentation.news.presentation.NewsPresentation

class NewsAdapter() : BaseAdapter<NewsAdapter.NewsAdapterHolder, NewsPresentation>(R.layout.adapter_news) {

    inner class NewsAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val image = itemView.findViewById<ImageView>(R.id.image)
        private val title = itemView.findViewById<TextView>(R.id.title)
        private val content = itemView.findViewById<TextView>(R.id.content)
        private val date = itemView.findViewById<TextView>(R.id.date)


        fun bind(news: NewsPresentation) {
            title.text = news.title
            content.text = news.content
            date.text = news.date
        }
    }

    override fun onBind(holder: NewsAdapterHolder, item: NewsPresentation) {
        holder.bind(item)
    }

    override fun createHolder(view: View): NewsAdapterHolder = NewsAdapterHolder(view)
}