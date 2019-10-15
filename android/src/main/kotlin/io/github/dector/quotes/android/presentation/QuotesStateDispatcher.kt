package io.github.dector.quotes.android.presentation

import io.github.dector.quotes.android.common.UIScope
import io.github.dector.quotes.colors.Color
import io.github.dector.quotes.domain.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class QuotesStateDispatcher(
    initialState: State,
    private val reducer: (State, Msg) -> Pair<State, Effect?>,
    private val effectsDispatcher: suspend (Effect) -> Msg?,
    private val effectsContext: CoroutineContext = Dispatchers.IO
) {

    private val scope = UIScope()

    private var state: State = initialState

    var view: ((State) -> Unit)? = null

    fun update(msg: Msg) {
        val (newState, effect) = reducer(state, msg)

        if (effect != null) launchEffect(effect)

        if (newState != state) {
            state = newState
            view?.invoke(state)
        }
    }

    private fun launchEffect(effect: Effect) {
        scope.launch {
            val msg = withContext(effectsContext) {
                effectsDispatcher(effect)
            }
            if (msg != null) update(msg)
        }
    }
}

sealed class State {
    object Empty : State()
    data class Data(
        val quote: Quote,
        val textColor: Color,
        val backgroundColor: Color
    ) : State()

}

sealed class Msg {
    object Init : Msg()
    object NextQuote : Msg()

    data class DisplayNote(
        val quote: Quote,
        val textColor: Color,
        val backgroundColor: Color
    ) : Msg()
}

sealed class Effect {
    object LoadNote : Effect()
}

internal fun State.only(): Pair<State, Effect?> =
    this + null

operator fun State.plus(effect: Effect?): Pair<State, Effect?> =
    this to effect
