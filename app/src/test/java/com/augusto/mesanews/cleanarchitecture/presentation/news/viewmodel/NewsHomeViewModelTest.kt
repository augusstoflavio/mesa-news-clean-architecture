package com.augusto.mesanews.cleanarchitecture.presentation.news.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.augusto.mesanews.cleanarchitecture.presentation.news.presentation.NewsPresentation
import com.augusto.mesanews.cleanarchitecture.presentation.news.presentation.NewsToPresentation
import com.augusto.mesanews.core.domain.entity.News
import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.repository.NewsRepository
import com.augusto.mesanews.core.domain.useCase.DisfavorNews
import com.augusto.mesanews.core.domain.useCase.FavoriteNews
import com.augusto.mesanews.core.domain.useCase.GetHighlights
import com.augusto.mesanews.core.domain.useCase.GetNews
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsHomeViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: NewsHomeViewModel

    @Mock
    private lateinit var newsObserver: Observer<List<NewsPresentation>>

    @Mock
    private lateinit var errorObserver: Observer<Result.Failure?>

    @Mock
    private lateinit var loadingObserver: Observer<Boolean>

    @Test
    fun `when NewsViewModel getNews get success then set newsLiveDate`() {
        runBlocking {
            //arrange
            val repository = Mockito.mock(NewsRepository::class.java)


            val newsList = listOf(
                    News(
                            title = "Título",
                            description = "Description",
                            content = "asd",
                            author = "Author",
                            publishedAt = Date(),
                            highlight = false,
                            url = "http://www.url.com.br?id=1",
                            imageUrl = "http://www.url.com.br/imagens/1.png",
                            favorite = false
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

            Mockito.`when`(
                repository.get(1)
            ).thenReturn(Result.Success(newsList))

            viewModel = NewsHomeViewModel(
                GetHighlights(repository),
                GetNews(repository),
                FavoriteNews(repository),
                DisfavorNews(repository),
                testDispatcher
            )
            viewModel.news.observeForever(newsObserver)
            viewModel.loading.observeForever(loadingObserver)

            //act
            viewModel.getNews()

            //assert
            verify(newsObserver).onChanged(newsList.map { NewsToPresentation.converter(it) })
            verify(loadingObserver).onChanged(true)
            verify(loadingObserver).onChanged(false)
        }
    }

    @Test
    fun `when NewsViewModel getNews get error then set error`() {
        runBlockingTest {
            //arrange
            val repository = Mockito.mock(NewsRepository::class.java)

            Mockito.`when`(
                    repository.get(1)
            ).thenReturn(Result.Failure(Result.Error("Mensagem", 1)))

            viewModel = NewsHomeViewModel(
                    GetHighlights(repository),
                    GetNews(repository),
                    FavoriteNews(repository),
                    DisfavorNews(repository),
                    testDispatcher
            )
            viewModel.error.observeForever(errorObserver)
            viewModel.loading.observeForever(loadingObserver)

            //act
            viewModel.getNews()

            //assert
            verify(errorObserver).onChanged(Result.Failure(Result.Error("Mensagem", 1)))
            verify(loadingObserver).onChanged(true)
            verify(loadingObserver).onChanged(false)
        }
    }
}