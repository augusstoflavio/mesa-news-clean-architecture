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
class GetNewsTest {

    @Test
    fun `when NewsRepository get return Success GetNews return Success`() {
        runBlocking {
            val newsFavorite = News(
                    title = "Notícia 1",
                    description = "Descrição 1",
                    content = "Content 1",
                    author = "Author 1",
                    publishedAt = Date(),
                    highlight = false,
                    url = "http://www.news.com.br/1",
                    imageUrl = "http://www.news.com.br/img1.png",
                    favorite = false
            )

            val newsNotFavorite = News(
                    title = "Notícia 2",
                    description = "Descrição 2",
                    content = "Content 2",
                    author = "Author 2",
                    publishedAt = Date(),
                    highlight = false,
                    url = "http://www.news.com.br/2",
                    imageUrl = "http://www.news.com.br/img2.png",
                    favorite = false
            )


            //arrange
            val listNews = listOf(
                    newsFavorite,
                    newsNotFavorite
            )

            val newsRepository = Mockito.mock(NewsRepository::class.java)
            val getNews = GetNews(newsRepository)
            Mockito.`when`(
                    newsRepository.get(1)
            ).thenReturn(Result.Success(listNews))

            Mockito.`when`(
                    newsRepository.checkIsFavorite(newsFavorite)
            ).thenReturn(Result.Success(true))

            Mockito.`when`(
                    newsRepository.checkIsFavorite(newsNotFavorite)
            ).thenReturn(Result.Success(false))

            //act
            val returnGetNews = getNews.invoke(1)

            newsFavorite.favorite = true

            //assert
            Assert.assertEquals(Result.Success(listOf(newsFavorite, newsNotFavorite)), returnGetNews)
        }
    }

    @Test
    fun `when NewsRepository get return Failure GetNews return Failure`() {
        runBlocking {

            val newsRepository = Mockito.mock(NewsRepository::class.java)
            val getNews = GetNews(newsRepository)

            val returnGet = Result.Failure(Result.Error("Error", 1))

            Mockito.`when`(
                    newsRepository.get(1)
            ).thenReturn(returnGet)


            //act
            val returnGetNews = getNews.invoke(1)

            //assert
            Assert.assertEquals(returnGet, returnGetNews)
        }
    }
}