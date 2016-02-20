package io.github.dector.quotes.android.presentation

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import io.github.dector.quotes.R
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QuotesActivityTest {

    @Rule fun ruleChain() = RuleChain.emptyRuleChain()
            .around(ActivityTestRule(QuotesActivity::class.java))

    @Test fun quoteShown() {
        onQuote().isDisplayed()
        onAuthor().isDisplayed()
    }

    @Test fun quoteChange() {
        onRoot().click()

        onQuote().apply {
            isDisplayed()
            //hasText()
        }
        onAuthor().isDisplayed()
    }
}

inline fun onRoot() = onView(withId(R.id.quotes_root))

inline fun onQuote() = onView(withId(R.id.quotes_quote))

inline fun onAuthor() = onView(withId(R.id.quotes_author))