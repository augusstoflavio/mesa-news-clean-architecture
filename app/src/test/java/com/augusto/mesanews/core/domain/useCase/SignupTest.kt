package com.augusto.mesanews.core.domain.useCase

import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.repository.AuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SignupTest {

    @Test
    fun `when AuthRepositorySignup return Success Signin return Success`() {
        testSigninReturn(Result.Success("123456"))
    }

    @Test
    fun `when AuthRepositorySignup return Failure Signin return Failure`() {
        testSigninReturn(Result.Failure(Result.Error("Error", 1)))
    }

    private fun testSigninReturn(returnSignupRepository: Result<String>) {
        runBlocking {
            //arrange
            val authRepository = Mockito.mock(AuthRepository::class.java)
            val signup = Signup(authRepository)
            Mockito.`when`(
                    authRepository.signup("Augusto Flávio Mendonça", "augusto.flaviom@gmail.com", "123456")
            ).thenReturn(returnSignupRepository)

            //act
            val returnSignup = signup.invoke("Augusto Flávio Mendonça", "augusto.flaviom@gmail.com", "123456")

            //assert
            Assert.assertEquals(returnSignupRepository, returnSignup)
        }
    }
}