package io.github.dector.quotes.android.presentation.view

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout.VERTICAL
import io.github.dector.quotes.android.dp
import io.github.dector.quotes.android.presentation.Msg
import io.github.dector.quotes.android.presentation.State
import io.github.dector.quotes.android.sp
import io.github.dector.quotes.colors.solid
import trikita.anvil.Anvil
import trikita.anvil.BaseDSL.WRAP
import trikita.anvil.DSL.MATCH
import trikita.anvil.DSL.backgroundColor
import trikita.anvil.DSL.frameLayout
import trikita.anvil.DSL.gravity
import trikita.anvil.DSL.linearLayout
import trikita.anvil.DSL.margin
import trikita.anvil.DSL.onClick
import trikita.anvil.DSL.orientation
import trikita.anvil.DSL.padding
import trikita.anvil.DSL.size
import trikita.anvil.DSL.text
import trikita.anvil.DSL.textColor
import trikita.anvil.DSL.textSize
import trikita.anvil.DSL.textView
import trikita.anvil.RenderableView

class QuotesView(context: Context) {

    private var state: State? = null

    val content: View = object : RenderableView(context) {

        override fun view() {
            val state = state ?: return

            frameLayout {
                size(MATCH, MATCH)

                when (state) {
                    is State.Data -> dataState(state)
                }

                margin(0, 48.dp)
                padding(32.dp)

                gravity(Gravity.CENTER)
            }

            if (state is State.Data) {
                backgroundColor(state.backgroundColor.solid)
            }
        }

        private fun dataState(state: State.Data) {
            val quote = state.quote

            linearLayout {
                size(MATCH, MATCH)

                textView {
                    size(MATCH, WRAP)

                    text(quote.content)

                    textSize(24.sp)
                    textColor(state.textColor.solid)
                    gravity(Gravity.CENTER)
                }

                textView {
                    size(MATCH, WRAP)

                    text(quote.author)

                    textSize(18.sp)
                    textColor(state.textColor.solid)
                    gravity(Gravity.END)

                    margin(0, 16.dp, 0, 0)
                }

                onClick { dispatcher?.invoke(Msg.NextQuote) }

                orientation(VERTICAL)
                gravity(Gravity.CENTER)
            }
        }
    }

    var dispatcher: ((Msg) -> Unit)? = null

    fun display(state: State) {
        this.state = state
        Anvil.render()
    }
}
