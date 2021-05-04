package com.augusto.mesanews.cleanarchitecture.presentation.login.fragment

import android.content.Context
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.augusto.mesanews.cleanarchitecture.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SigninFragmentTest {

//    val viewModel by inject<SigninViewModel>()


    @Test
    fun checkIfShowUserError() {
        val fragmentArgs = bundleOf()
        launchFragmentInContainer<SigninFragment>(fragmentArgs, R.style.Theme_AppCompat_Light_NoActionBar)

        val context = ApplicationProvider.getApplicationContext<Context>()
        val msgError = context.resources.getString(R.string.invalid_username)

        onView(withId(R.id.btn_signin)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.editTextTextEmailAddress)).check(matches(hasErrorText(msgError)))
    }

    @Test
    fun checkIfShowPasswordError() {
        val fragmentArgs = bundleOf()
        launchFragmentInContainer<SigninFragment>(fragmentArgs, R.style.Theme_AppCompat_Light_NoActionBar)

        val context = ApplicationProvider.getApplicationContext<Context>()
        val msgError = context.resources.getString(R.string.invalid_password)

        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("augusto.flaviom@gmail.com"))
        onView(withId(R.id.btn_signin)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.editTextTextPassword)).check(matches(hasErrorText(msgError)))
    }
}