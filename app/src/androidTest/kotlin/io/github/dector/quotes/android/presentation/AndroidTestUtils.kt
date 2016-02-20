package io.github.dector.quotes.android.presentation

import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withText

// Matchers

inline fun ViewInteraction.isDisplayed()
        = this.check(matches(ViewMatchers.isDisplayed()))

inline fun ViewInteraction.hasText(text: String)
        = this.check(matches(withText(text)))

// Interactions

inline fun ViewInteraction.click()
        = this.perform(ViewActions.click())