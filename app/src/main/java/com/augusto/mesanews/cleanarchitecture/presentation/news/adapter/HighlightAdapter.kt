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
        private val clickNews: (NewsPresentation) -> Unit,
        private val favoriteNews: (NewsPresentation, Boolean) -> Unit
) : BaseAdapter<HighlightAdapter.HighlightAdapterHolder, NewsPresentation>(R.layout.adapter_highlight) {

    inner class HighlightAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val icFavorite = itemView.findViewById<ImageView>(R.id.ic_favorite)
        private val image = itemView.findViewById<ImageView>(R.id.image)
        private val title = itemView.findViewById<TextView>(R.id.title)
        private val date = itemView.findViewById<TextView>(R.id.date)


        fun bind(news: NewsPresentation) {
            title.text = news.title
            date.text = news.date
            image.setOnClickListener {
                clickNews.invoke(news)
            }
            title.setOnClickListener {
                clickNews.invoke(news)
            }

            Picasso.get().load(news.imageUrl).into(image)

            setupIconFavorite(icFavorite, news.isFavorite)

            icFavorite.setOnClickListener {
                val isFavorite = it.tag as Boolean
                favoriteNews.invoke(news, !isFavorite)
                setupIconFavorite(icFavorite, !isFavorite)
            }
        }
    }

    private fun setupIconFavorite(icFavorite: ImageView, isFavorite: Boolean) {
        icFavorite.tag = isFavorite
        if (isFavorite) {
            icFavorite.setImageResource(R.drawable.ic_saved_fill)
        } else {
            icFavorite.setImageResource(R.drawable.ic_saved)
        }
    }

    override fun onBind(holder: HighlightAdapterHolder, item: NewsPresentation) {
        holder.bind(item)
    }

    override fun createHolder(view: View): HighlightAdapterHolder = HighlightAdapterHolder(view)
}