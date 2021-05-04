package com.augusto.mesanews.cleanarchitecture.presentation.login.fragment

import android.content.Context
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.augusto.mesanews.cleanarchitecture.R
import com.augusto.mesanews.core.domain.entity.Result
import com.augusto.mesanews.core.domain.repository.AuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.Mockito


@RunWith(AndroidJUnit4ClassRunner::class)
@ExperimentalCoroutinesApi
@LargeTest
class SigninFragmentTest: KoinTest {

    private lateinit var fragment: FragmentScenario<SigninFragment>
    private lateinit var mockedModule: Module
    private lateinit var authRepository: AuthRepository

    @Before
    fun setup() {
        authRepository = Mockito.mock(AuthRepository::class.java)
        mockedModule = module {
            single(override = true) { authRepository }
        }

        loadKoinModules(mockedModule)
    }

    @After
    fun tearDown() {
        unloadKoinModules(mockedModule)
    }

    @Test
    fun checkIfShowToastError() {
        val fragmentArgs = bundleOf()
        fragment = launchFragmentInContainer<SigninFragment>(fragmentArgs, R.style.Theme_AppCompat_Light_NoActionBar)

        val errorReturn = Result.Failure(Result.Error("Login inválido", 1))

        Mockito.`when`(
                runBlocking {
                    authRepository.signin("augusto.flaviom@gmail.com", "123456")
                }
        ).thenReturn(errorReturn)

        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("augusto.flaviom@gmail.com"))
        onView(withId(R.id.editTextTextPassword)).perform(typeText("123456"))
        onView(withId(R.id.btn_signin)).perform(click())

        onView(withText("Login inválido")).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun checkIfShowPasswordError() {
        val fragmentArgs = bundleOf()
        fragment = launchFragmentInContainer<SigninFragment>(fragmentArgs, R.style.Theme_AppCompat_Light_NoActionBar)

        val context = ApplicationProvider.getApplicationContext<Context>()
        val msgError = context.resources.getString(R.string.invalid_password)

        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("augusto.flaviom@gmail.com"))
        onView(withId(R.id.btn_signin)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.editTextTextPassword)).check(matches(hasErrorText(msgError)))
    }

    @Test
    fun checkIfShowUserError() {
        val fragmentArgs = bundleOf()
        fragment = launchFragmentInContainer<SigninFragment>(fragmentArgs, R.style.Theme_AppCompat_Light_NoActionBar)

        val context = ApplicationProvider.getApplicationContext<Context>()
        val msgError = context.resources.getString(R.string.invalid_username)

        onView(withId(R.id.btn_signin)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.editTextTextEmailAddress)).check(matches(hasErrorText(msgError)))
    }
}