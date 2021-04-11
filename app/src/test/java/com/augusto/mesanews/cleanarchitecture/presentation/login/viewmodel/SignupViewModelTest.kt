package com.augusto.mesanews.cleanarchitecture.presentation.login.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.augusto.mesanews.cleanarchitecture.R
import com.augusto.mesanews.cleanarchitecture.data.validator.EmailValidator
import com.augusto.mesanews.cleanarchitecture.presentation.login.formstate.SignupFormState
import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.repository.AuthRepository
import com.augusto.mesanews.core.domain.useCase.Signup
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
class SignupViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var errorObserver: Observer<Result.Failure?>

    @Mock
    private lateinit var loadingObserver: Observer<Boolean>

    @Mock
    private lateinit var signupResultObserver: Observer<Boolean>

    @Mock
    private lateinit var signupFormObserver: Observer<SignupFormState>

    @Test
    fun `when SignupViewModel signup return success then set signupResult`() {
        runBlockingTest {
            //arrange
            val repository = Mockito.mock(AuthRepository::class.java)
            val emailValidator = Mockito.mock(EmailValidator::class.java)
            val viewModel = createViewModel(repository, emailValidator)
            viewModel.signupResult.observeForever(signupResultObserver)
            viewModel.loading.observeForever(loadingObserver)

            Mockito.`when`(
                    repository.signup("Augusto", "augusto.flaviom@gmail.com", "123456")
            ).thenReturn(Result.Success("token"))

            Mockito.`when`(
                    emailValidator.isValid("augusto.flaviom@gmail.com")
            ).thenReturn(true)

            //act
            viewModel.signup("Augusto","augusto.flaviom@gmail.com", "123456")

            //assert
            verify(signupResultObserver).onChanged(true)
            verify(loadingObserver).onChanged(true)
            verify(loadingObserver).onChanged(false)
        }
    }

    @Test
    fun `when SignupViewModel signup return error then set loginResult and error`() {
        runBlockingTest {
            //arrange
            val repository = Mockito.mock(AuthRepository::class.java)
            val emailValidator = Mockito.mock(EmailValidator::class.java)
            val viewModel = createViewModel(repository, emailValidator)
            viewModel.signupResult.observeForever(signupResultObserver)
            viewModel.loading.observeForever(loadingObserver)
            viewModel.error.observeForever(errorObserver)

            val errorReturn = Result.Failure(Result.Error("Login inválido", 1))

            Mockito.`when`(
                    repository.signup("Augusto","augusto.flaviom@gmail.com", "123456")
            ).thenReturn(errorReturn)

            Mockito.`when`(
                    emailValidator.isValid("augusto.flaviom@gmail.com")
            ).thenReturn(true)

            //act
            viewModel.signup("Augusto","augusto.flaviom@gmail.com", "123456")

            //assert
            verify(signupResultObserver).onChanged(false)
            verify(loadingObserver).onChanged(true)
            verify(loadingObserver).onChanged(false)
            verify(errorObserver).onChanged(errorReturn)
        }
    }

    @Test
    fun `when SignupViewModel signup with invalid email then set signupForm`() {
        runBlockingTest {
            //arrange
            val repository = Mockito.mock(AuthRepository::class.java)
            val emailValidator = Mockito.mock(EmailValidator::class.java)
            val viewModel = createViewModel(repository, emailValidator)
            viewModel.signinFormState.observeForever(signupFormObserver)
            Mockito.`when`(
                    emailValidator.isValid("augusto.flaviom@gmail.com")
            ).thenReturn(false)

            //act
            viewModel.signup("Augusto", "augusto.flaviom@gmail.com", "123456")

            //assert
            verify(signupFormObserver).onChanged(SignupFormState(
                    usernameError = R.string.invalid_username
            ))
        }
    }

    @Test
    fun `when SignupViewModel signup with invalid password then set signupForm`() {
        runBlockingTest {
            //arrange
            val repository = Mockito.mock(AuthRepository::class.java)
            val emailValidator = Mockito.mock(EmailValidator::class.java)
            val viewModel = createViewModel(repository, emailValidator)
            viewModel.signinFormState.observeForever(signupFormObserver)
            Mockito.`when`(
                    emailValidator.isValid("augusto.flaviom@gmail.com")
            ).thenReturn(true)

            //act
            viewModel.signup("Augusto","augusto.flaviom@gmail.com", "12")

            //assert
            verify(signupFormObserver).onChanged(SignupFormState(
                    passwordError = R.string.invalid_password
            ))
        }
    }

    @Test
    fun `when SignupViewModel signup with invalid name then set signupForm`() {
        runBlockingTest {
            //arrange
            val repository = Mockito.mock(AuthRepository::class.java)
            val emailValidator = Mockito.mock(EmailValidator::class.java)
            val viewModel = createViewModel(repository, emailValidator)
            viewModel.signinFormState.observeForever(signupFormObserver)

            //act
            viewModel.signup("","augusto.flaviom@gmail.com", "124356")

            //assert
            verify(signupFormObserver).onChanged(SignupFormState(
                    nameError = R.string.invalid_name
            ))
        }
    }

    private fun createViewModel(repository: AuthRepository, emailValidator: EmailValidator) =
            SignupViewModel(
                    Signup(repository),
                    emailValidator,
                    testDispatcher
            )

}