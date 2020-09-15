package com.ebelli.chucknorrisjokes

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun elementsAreVisible() {
        Espresso.onView(withId(R.id.progressBar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(5000)
        Espresso.onView(withId(R.id.jokes_list))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}