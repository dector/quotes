package io.github.dector.quotes.android.presentation

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText

// Matchers

inline fun ViewInteraction.isDisplayed()
        = this.check(matches(ViewMatchers.isDisplayed()))

inline fun ViewInteraction.hasText(text: String)
        = this.check(matches(withText(text)))

// Interactions

inline fun ViewInteraction.click()
        = this.perform(ViewActions.click())
