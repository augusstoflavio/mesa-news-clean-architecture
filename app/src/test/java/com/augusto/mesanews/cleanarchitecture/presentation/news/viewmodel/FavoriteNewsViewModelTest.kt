package com.augusto.mesanews.cleanarchitecture.presentation.news.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.augusto.mesanews.cleanarchitecture.presentation.news.presentation.NewsPresentation
import com.augusto.mesanews.cleanarchitecture.presentation.news.presentation.NewsToPresentation
import com.augusto.mesanews.core.domain.entity.News
import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.repository.NewsRepository
import com.augusto.mesanews.core.domain.useCase.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FavoriteNewsViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var errorObserver: Observer<Result.Failure?>

    @Mock
    private lateinit var loadingObserver: Observer<Boolean>

    @Mock
    private lateinit var newsObserver: Observer<List<NewsPresentation>>

    private var newsList = listOf(
            News(
                    title = "Título",
                    description = "Description",
                    content = "asd",
                    author = "Author",
                    publishedAt = Date(),
                    highlight = false,
                    url = "http://www.url.com.br?id=1",
                    imageUrl = "http://www.url.com.br/imagens/1.png",
                    favorite = true
            ),
            News(
                    title = "Título 2",
                    description = "Description 2",
                    content = "asd 2",
                    author = "Author 2",
                    publishedAt = Date(),
                    highlight = false,
                    url = "http://www.url.com.br?id=2",
                    imageUrl = "http://www.url.com.br/imagens/2.png",
                    favorite = true
            )
    )

    @Test
    fun `when FavoriteNewsViewModelTest getFavorites get success then set news`() {
        runBlocking {
            //arrange
            val repository = Mockito.mock(NewsRepository::class.java)

            Mockito.`when`(
                    repository.getFavoriteNews()
            ).thenReturn(Result.Success(newsList))

            val viewModel = createViewModel(repository)
            viewModel.news.observeForever(newsObserver)
            viewModel.loading.observeForever(loadingObserver)

            //act
            viewModel.getFavorites()

            //assert
            verify(newsObserver).onChanged(newsList.map { NewsToPresentation.converter(it) })
            verify(loadingObserver).onChanged(true)
            verify(loadingObserver).onChanged(false)
        }
    }

    @Test
    fun `when FavoriteNewsViewModelTest getFavorites get error then set error`() {
        runBlockingTest {
            //arrange
            val repository = Mockito.mock(NewsRepository::class.java)

            Mockito.`when`(
                    repository.getFavoriteNews()
            ).thenReturn(Result.Failure(Result.Error("Mensagem", 1)))

            val viewModel = createViewModel(repository)
            viewModel.error.observeForever(errorObserver)
            viewModel.loading.observeForever(loadingObserver)

            //act
            viewModel.getFavorites()

            //assert
            verify(errorObserver).onChanged(Result.Failure(Result.Error("Mensagem", 1)))
            verify(loadingObserver).onChanged(true)
            verify(loadingObserver).onChanged(false)
        }
    }

    @Test
    fun `when FavoriteNewsViewModelTest disfavorNews get success then set news`() {
        runBlockingTest {
            val news = News(
                    title = "Título",
                    description = "Description",
                    content = "asd",
                    author = "Author",
                    publishedAt = Date(),
                    highlight = false,
                    url = "http://www.url.com.br?id=1",
                    imageUrl = "http://www.url.com.br/imagens/1.png",
                    favorite = true
            )

            //arrange
            val repository = Mockito.mock(NewsRepository::class.java)

            Mockito.`when`(
                    repository.getFavoriteNews()
            ).thenReturn(Result.Success(listOf(news)))

            Mockito.`when`(
                    repository.disfavor(news)
            ).thenReturn(Result.Success(true))

            val viewModel = createViewModel(repository)
            viewModel.news.observeForever(newsObserver)

            //act
            viewModel.getFavorites()
            viewModel.disfavorNews(NewsToPresentation.converter(news))

            //assert
            verify(newsObserver).onChanged(listOf(news).map { NewsToPresentation.converter(it) })
            verify(newsObserver).onChanged(listOf())
        }
    }

    @Test
    fun `when FavoriteNewsViewModelTest disfavorNews get error then set error and news`() {
        runBlockingTest {
            val news = News(
                    title = "Título",
                    description = "Description",
                    content = "asd",
                    author = "Author",
                    publishedAt = Date(),
                    highlight = false,
                    url = "http://www.url.com.br?id=1",
                    imageUrl = "http://www.url.com.br/imagens/1.png",
                    favorite = true
            )

            //arrange
            val repository = Mockito.mock(NewsRepository::class.java)

            Mockito.`when`(
                    repository.getFavoriteNews()
            ).thenReturn(Result.Success(listOf(news)))

            Mockito.`when`(
                    repository.disfavor(news)
            ).thenReturn(Result.Failure(Result.Error("Erro", 1)))

            val viewModel = createViewModel(repository)
            viewModel.news.observeForever(newsObserver)
            viewModel.error.observeForever(errorObserver)

            //act
            viewModel.getFavorites()
            viewModel.disfavorNews(NewsToPresentation.converter(news))

            //assert
            verify(newsObserver, times(2)).onChanged(listOf(news).map { NewsToPresentation.converter(it) })
            verify(errorObserver).onChanged(Result.Failure(Result.Error("Erro", 1)))
        }
    }

    private fun createViewModel(repository: NewsRepository) =
            FavoriteNewsViewModel(
                    GetFavoriteNews(repository),
                    DisfavorNews(repository),
                    testDispatcher
            )
}