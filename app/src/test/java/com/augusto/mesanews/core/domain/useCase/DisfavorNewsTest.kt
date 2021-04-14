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
class DisfavorNewsTest {

    @Test
    fun `when NewsRepository DisfavorNews return Success DisfavorNews return Success`() {
        testDisfavorReturn(Result.Success(true))
    }

    @Test
    fun `when NewsRepository DisfavorNews return Failure DisfavorNews return Failure`() {
        testDisfavorReturn(Result.Failure(Result.Error("Error", 1)))
    }

    private fun testDisfavorReturn(returnSignupRepository: Result<Boolean>) {
        runBlocking {
            //arrange
            val news = News(
                    title = "Notícia 1",
                    description = "Descrição 1",
                    content = "Content 1",
                    author = "Author 1",
                    publishedAt = Date(),
                    highlight = true,
                    url = "http://www.news.com.br/1",
                    imageUrl = "http://www.news.com.br/img1.png",
                    favorite = true
            )

            val newsRepository = Mockito.mock(NewsRepository::class.java)
            val disfavorNews = DisfavorNews(newsRepository)
            Mockito.`when`(
                    newsRepository.disfavor(news)
            ).thenReturn(returnSignupRepository)

            //act
            val returnDisfavor = disfavorNews.invoke(news)

            //assert
            Assert.assertEquals(returnSignupRepository, returnDisfavor)
        }
    }
}