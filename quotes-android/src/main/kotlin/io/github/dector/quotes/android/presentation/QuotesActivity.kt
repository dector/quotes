package io.github.dector.quotes.android.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.dector.quotes.android.QuotesApplication
import io.github.dector.quotes.android.common.fullscreen
import io.github.dector.quotes.android.presentation.view.QuotesView
import io.github.dector.quotes.repositories.RandomColorsRepository
import io.github.dector.quotes.repositories.RandomQuoteRepository
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject

class QuotesActivity : AppCompatActivity() {

    private val view: QuotesView by inject()

    //@Inject
    private lateinit var stateDispatcher: QuotesStateDispatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stateDispatcher = createStateDispatcher()

        setContentView(view.content)

        stateDispatcher.view = view::display
        view.dispatcher = stateDispatcher::update

        init()
    }

    private fun init() {
        stateDispatcher.update(Msg.Init)
    }

    override fun onResume() {
        super.onResume()
        fullscreen()
    }

    private fun reducer(state: State, msg: Msg): Pair<State, Effect?> {
        when (msg) {
            is Msg.DisplayNote ->
                State.Data(msg.quote, msg.textColor, msg.backgroundColor).only()
            is Msg.NextQuote ->
                state + Effect.LoadNote
            else -> null
        }?.let { return it }

        return when (state) {
            is State.Empty -> when (msg) {
                is Msg.Init -> state + Effect.LoadNote
                else -> state.only()
            }
            is State.Data -> when (msg) {
                else -> state.only()
            }
            else -> state.only()
        }
    }

    private suspend fun effectsDispatcher(effect: Effect): Msg? {
        return when (effect) {
            is Effect.LoadNote -> {
                val quote = get<RandomQuoteRepository>().next()!!
                val colors = get<RandomColorsRepository>().next()

                return Msg.DisplayNote(quote, colors.foreground, colors.background)
            }
            else -> null
        }
    }

    private fun createStateDispatcher() = QuotesStateDispatcher(
        initialState = State.Empty,
        reducer = this::reducer,
        effectsDispatcher = this::effectsDispatcher
    )
}

