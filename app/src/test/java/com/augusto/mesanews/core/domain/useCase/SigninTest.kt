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
class SigninTest {

    @Test
    fun `when AuthRepositorySignin return Success Signin return Success`() {
        testSigninReturn(Result.Success("123456"))
    }

    @Test
    fun `when AuthRepositorySignin return Failure Signin return Failure`() {
        testSigninReturn(Result.Failure(Result.Error("Error", 1)))
    }

    private fun testSigninReturn(returnSigninRepository: Result<String>) {
        runBlocking {
            //arrange
            val authRepository = Mockito.mock(AuthRepository::class.java)
            val signin = Signin(authRepository)
            Mockito.`when`(
                    authRepository.signin("augusto.flaviom@gmail.com", "123456")
            ).thenReturn(returnSigninRepository)

            //act
            val returnSignin = signin.invoke("augusto.flaviom@gmail.com", "123456")

            //assert
            Assert.assertEquals(returnSigninRepository, returnSignin)
        }
    }
}