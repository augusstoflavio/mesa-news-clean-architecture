package com.augusto.mesanews.cleanarchitecture.presentation.news.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.augusto.mesanews.cleanarchitecture.R
import com.augusto.mesanews.cleanarchitecture.presentation.bases.BaseAdapter
import com.augusto.mesanews.cleanarchitecture.presentation.news.presentation.NewsPresentation
import com.squareup.picasso.Picasso

class HighlightAdapter(
        private val clickNews: (NewsPresentation) -> Unit
) : BaseAdapter<HighlightAdapter.HighlightAdapterHolder, NewsPresentation>(R.layout.adapter_highlight) {

    inner class HighlightAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val contentNews = itemView.findViewById<View>(R.id.content_news)
        private val image = itemView.findViewById<ImageView>(R.id.image)
        private val title = itemView.findViewById<TextView>(R.id.title)
        private val date = itemView.findViewById<TextView>(R.id.date)


        fun bind(news: NewsPresentation, clickNews: (NewsPresentation) -> Unit) {
            title.text = news.title
            date.text = news.date
            contentNews.setOnClickListener {
                clickNews.invoke(news)
            }

            Picasso.get().load(news.imageUrl).into(image)
        }
    }

    override fun onBind(holder: HighlightAdapterHolder, item: NewsPresentation) {
        holder.bind(item, clickNews)
    }

    override fun createHolder(view: View): HighlightAdapterHolder = HighlightAdapterHolder(view)
}