package com.augusto.mesanews.cleanarchitecture.presentation.login.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.augusto.mesanews.cleanarchitecture.data.local.dataSouce.SharedPreferencesDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginHomeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var isLoggedObserver: Observer<Boolean>

    @Test
    fun `when LoginHomeViewModelTest checkIsLogged return true then set isLogged`() {
        testCheckIsLogged(true)
    }

    @Test
    fun `when LoginHomeViewModelTest checkIsLogged return error then set isLogged`() {
        testCheckIsLogged(false)
    }

    private fun testCheckIsLogged(isLogged: Boolean) {
        //arrange
        val sharedPreferencesDataSource = Mockito.mock(SharedPreferencesDataSource::class.java)
        val viewModel = LoginHomeViewModel(sharedPreferencesDataSource)
        Mockito.`when`(
                sharedPreferencesDataSource.isLogged()
        ).thenReturn(isLogged)
        viewModel.isLogged.observeForever(isLoggedObserver)

        //act
        viewModel.checkIsLogged()

        //assert
        verify(isLoggedObserver).onChanged(isLogged)
    }
}