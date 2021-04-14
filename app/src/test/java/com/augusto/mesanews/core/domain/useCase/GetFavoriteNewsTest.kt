package com.augusto.mesanews.core.domain.useCase

import com.augusto.mesanews.core.domain.entity.News
import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.repository.NewsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetFavoriteNewsTest {

    @Test
    fun `when NewsRepository getFavoriteNews return Success GetFavoriteNews return Success`() {
        runBlocking {
            val news1 = News(
                    title = "Notícia 1",
                    description = "Descrição 1",
                    content = "Content 1",
                    author = "Author 1",
                    publishedAt = Date(),
                    highlight = false,
                    url = "http://www.news.com.br/1",
                    imageUrl = "http://www.news.com.br/img1.png",
                    favorite = true
            )

            val news2 = News(
                    title = "Notícia 2",
                    description = "Descrição 2",
                    content = "Content 2",
                    author = "Author 2",
                    publishedAt = Date(),
                    highlight = false,
                    url = "http://www.news.com.br/2",
                    imageUrl = "http://www.news.com.br/img2.png",
                    favorite = true
            )


            //arrange
            val listNews = listOf(
                    news1,
                    news2
            )

            val newsRepository = Mockito.mock(NewsRepository::class.java)
            val getFavoriteNews = GetFavoriteNews(newsRepository)
            Mockito.`when`(
                    newsRepository.getFavoriteNews()
            ).thenReturn(Result.Success(listNews))

            Mockito.`when`(
                    newsRepository.checkIsFavorite(news1)
            ).thenReturn(Result.Success(true))

            Mockito.`when`(
                    newsRepository.checkIsFavorite(news2)
            ).thenReturn(Result.Success(true))

            //act
            val returnGetFavoriteNews = getFavoriteNews.invoke()

            //assert
            Assert.assertEquals(Result.Success(listNews), returnGetFavoriteNews)
        }
    }

    @Test
    fun `when NewsRepository getFavoriteNews return Failure GetFavoriteNews return Failure`() {
        runBlocking {

            val newsRepository = Mockito.mock(NewsRepository::class.java)
            val getNews = GetFavoriteNews(newsRepository)

            val returnGet = Result.Failure(Result.Error("Error", 1))

            Mockito.`when`(
                    newsRepository.getFavoriteNews()
            ).thenReturn(returnGet)


            //act
            val returnGetFavoriteNews = getNews.invoke()

            //assert
            Assert.assertEquals(returnGet, returnGetFavoriteNews)
        }
    }
}