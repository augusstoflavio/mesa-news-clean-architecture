package com.augusto.mesanews.cleanarchitecture.presentation.login.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.augusto.mesanews.cleanarchitecture.R
import com.augusto.mesanews.cleanarchitecture.data.local.dataSouce.SharedPreferencesDataSource
import com.augusto.mesanews.cleanarchitecture.data.validator.EmailValidator
import com.augusto.mesanews.cleanarchitecture.presentation.login.formstate.SigninFormState
import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.repository.AuthRepository
import com.augusto.mesanews.core.domain.useCase.Signin
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SigninViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: SigninViewModel

    @Mock
    private lateinit var errorObserver: Observer<Result.Failure?>

    @Mock
    private lateinit var loadingObserver: Observer<Boolean>

    @Mock
    private lateinit var loginResultObserver: Observer<Boolean>

    @Mock
    private lateinit var loginFormObserver: Observer<SigninFormState>

    @Test
    fun `when SigninViewModel login return success then set loginResult`() {
        runBlockingTest {
            //arrange
            val repository = Mockito.mock(AuthRepository::class.java)
            val emailValidator = Mockito.mock(EmailValidator::class.java)
            val sharedPreferencesDataSource = SharedPreferencesDataSourceMock()
            val viewModel = createViewModel(repository, emailValidator, sharedPreferencesDataSource)
            viewModel.loginResult.observeForever(loginResultObserver)
            viewModel.loading.observeForever(loadingObserver)

            Mockito.`when`(
                    repository.signin("augusto.flaviom@gmail.com", "123456")
            ).thenReturn(Result.Success("token"))

            Mockito.`when`(
                    emailValidator.isValid("augusto.flaviom@gmail.com")
            ).thenReturn(true)

            //act
            viewModel.login("augusto.flaviom@gmail.com", "123456")

            //assert
            verify(loginResultObserver).onChanged(true)
            verify(loadingObserver).onChanged(true)
            verify(loadingObserver).onChanged(false)

            assertTrue(sharedPreferencesDataSource.isLogged())
            assertNotNull(sharedPreferencesDataSource.getToken())
        }
    }

    @Test
    fun `when SigninViewModel login return error then set loginResult and error`() {
        runBlockingTest {
            //arrange
            val repository = Mockito.mock(AuthRepository::class.java)
            val emailValidator = Mockito.mock(EmailValidator::class.java)
            val sharedPreferencesDataSource = SharedPreferencesDataSourceMock()
            val viewModel = createViewModel(repository, emailValidator, sharedPreferencesDataSource)
            viewModel.loginResult.observeForever(loginResultObserver)
            viewModel.loading.observeForever(loadingObserver)
            viewModel.error.observeForever(errorObserver)

            val errorReturn = Result.Failure(Result.Error("Login inválido", 1))

            Mockito.`when`(
                    repository.signin("augusto.flaviom@gmail.com", "123456")
            ).thenReturn(errorReturn)

            Mockito.`when`(
                    emailValidator.isValid("augusto.flaviom@gmail.com")
            ).thenReturn(true)

            //act
            viewModel.login("augusto.flaviom@gmail.com", "123456")

            //assert
            verify(loginResultObserver).onChanged(false)
            verify(loadingObserver).onChanged(true)
            verify(loadingObserver).onChanged(false)
            verify(errorObserver).onChanged(errorReturn)

            assertFalse(sharedPreferencesDataSource.isLogged())
            assertNull(sharedPreferencesDataSource.getToken())
        }
    }

    @Test
    fun `when SigninViewModel login with invalid email then set loginForm`() {
        runBlockingTest {
            //arrange
            val repository = Mockito.mock(AuthRepository::class.java)
            val emailValidator = Mockito.mock(EmailValidator::class.java)
            val sharedPreferencesDataSource = SharedPreferencesDataSourceMock()
            val viewModel = createViewModel(repository, emailValidator, sharedPreferencesDataSource)
            viewModel.signinFormState.observeForever(loginFormObserver)
            Mockito.`when`(
                    emailValidator.isValid("augusto.flaviom@gmail.com")
            ).thenReturn(false)

            //act
            viewModel.login("augusto.flaviom@gmail.com", "123456")

            //assert
            verify(loginFormObserver).onChanged(SigninFormState(
                    usernameError = R.string.invalid_username
            ))
        }
    }

    @Test
    fun `when SigninViewModel login with invalid password then set loginForm`() {
        runBlockingTest {
            //arrange
            val repository = Mockito.mock(AuthRepository::class.java)
            val emailValidator = Mockito.mock(EmailValidator::class.java)
            val sharedPreferencesDataSource = SharedPreferencesDataSourceMock()
            val viewModel = createViewModel(repository, emailValidator, sharedPreferencesDataSource)
            viewModel.signinFormState.observeForever(loginFormObserver)
            Mockito.`when`(
                    emailValidator.isValid("augusto.flaviom@gmail.com")
            ).thenReturn(true)

            //act
            viewModel.login("augusto.flaviom@gmail.com", "12")

            //assert
            verify(loginFormObserver).onChanged(SigninFormState(
                    passwordError = R.string.invalid_password
            ))
        }
    }

    private fun createViewModel(repository: AuthRepository, emailValidator: EmailValidator, sharedPreferencesDataSource: SharedPreferencesDataSource) =
            SigninViewModel(
                    sharedPreferencesDataSource,
                    Signin(repository),
                    emailValidator,
                    testDispatcher
            )
}

class SharedPreferencesDataSourceMock: SharedPreferencesDataSource {

    private var token: String? = null
    private var user: String? = null

    override fun isLogged(): Boolean {
        return token != null
    }

    override fun saveUser(name: String, token: String) {
        this.token = token
        this.user = name
    }

    override fun clear() {
        token = null
        user = null
    }

    override fun getToken(): String? {
        return token
    }

}