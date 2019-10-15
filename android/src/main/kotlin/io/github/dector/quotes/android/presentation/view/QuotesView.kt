package io.github.dector.quotes.android.presentation.view

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout.VERTICAL
import android.widget.TextView
import io.github.dector.quotes.android.dp
import io.github.dector.quotes.android.presentation.Msg
import io.github.dector.quotes.android.presentation.State
import io.github.dector.quotes.android.sp
import io.github.dector.quotes.domain.solid
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

                    textSize(20.sp)
                    textColor(state.textColor.solid)
                    gravity(Gravity.CENTER)
                }

                textView {
                    size(MATCH, WRAP)

                    text(quote.author)

                    textSize(16.sp)
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

    private lateinit var rootView: View
    private lateinit var touchView: View
    private lateinit var quoteView: TextView
    private lateinit var authorView: TextView

    private lateinit var loadingTextView: TextView
    private lateinit var loadingImageView: ImageView

    private lateinit var dataContainerView: View
    private lateinit var noDataContainerView: View

    /*fun init() {
        rootView = content.findViewById(R.id.quotes_root)
        touchView = content.findViewById(R.id.quotes_touch)
        quoteView = content.findViewById(R.id.quotes_quote) as TextView
        authorView = content.findViewById(R.id.quotes_author) as TextView

        loadingTextView = content.findViewById(R.id.quotes_loading_text) as TextView
        loadingImageView = content.findViewById(R.id.quotes_loading_image) as ImageView

        dataContainerView = content.findViewById(R.id.quotes_data_container)
        noDataContainerView = content.findViewById(R.id.quotes_no_data_container)

        touchView.setOnClickListener { dispatcher?.invoke(Msg.NextQuote) }
    }*/

    fun display(state: State) {
        this.state = state
        Anvil.render()

        /*return
        dataContainerView.visibility = if (state is State.Data) View.VISIBLE else View.GONE
        noDataContainerView.visibility = if (state is State.Empty) View.VISIBLE else View.GONE

        if (state is State.Data) {
            val quote = state.quote

            showQuote(quote.content)
            showAuthor(quote.author)
            textColor(state.textColor)
            backgroundColor(state.backgroundColor)
        }*/
    }

    /*private fun showQuote(quote: String) {
        quoteView.text = quote
    }

    private fun showAuthor(author: String) {
        authorView.text = author
    }

    private fun textColor(color: Color) {
        val colorValue = color.solid

        quoteView.setTextColor(colorValue)
        authorView.setTextColor(colorValue)
        loadingTextView.setTextColor(colorValue)
        loadingImageView.setColorFilter(colorValue)
    }

    private fun backgroundColor(color: Color) {
        rootView.setBackgroundColor(color.solid)
    }*/
}

/*class InOutAnimator(val view: View, val onStarted: () -> Unit, val onFinished: () -> Unit) {

    private val animationsHandler = Handler()

    private val animators = createLoadingAnimatorsFor(view, onStarted, onFinished)
    private val startAnimationCallback = { animators.first.start() }

    fun start() {
        if (!animators.first.isRunning && !animators.second.isRunning && !animators.third.isRunning) {
            removeInAnimationScheduling()
            scheduleInAnimation()
        } else if (animators.third.isRunning) {
            scheduleInAnimation()
        } else {}
    }

    private fun removeInAnimationScheduling() {
        animationsHandler.removeCallbacksAndMessages(null)
    }

    private fun scheduleInAnimation() {
        animationsHandler.postDelayed(startAnimationCallback, 100)
    }

    fun stop() {
        if (!animators.first.isRunning && !animators.second.isRunning && !animators.third.isRunning) {
            removeInAnimationScheduling()
        } else if (animators.first.isRunning) {
            animators.first.cancel()
            animators.third.start()
        } else if (animators.second.isRunning) {
            animators.second.cancel()
            animators.third.start()
        }
    }

    fun createLoadingAnimatorsFor(v: View, onStarted: () -> Unit, onFinished: ()-> Unit): Triple<Animator, Animator, Animator> {
        // Out animator
        val outAnimator = AnimatorSet().apply {
            duration = 500
            interpolator = TimeInterpolator { t -> t*t*t*t }

            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    onFinished()
                }
            })

            play(ObjectAnimator.ofFloat(v, View.SCALE_X, 0F))
                    .with(ObjectAnimator.ofFloat(v, View.SCALE_Y, 0F))
        }

        // Progress animator
        val progressAnimator = ObjectAnimator.ofFloat(v, View.ROTATION, 0F, -360F).apply {
            repeatMode = ObjectAnimator.RESTART
            repeatCount = ObjectAnimator.INFINITE
            duration = 700
            interpolator = DecelerateInterpolator()
        }

        // In animator
        val inAnimator = AnimatorSet().apply {
            duration = 500
            interpolator = TimeInterpolator { t -> t*t*t*t }

            addListener(object : AnimatorListenerAdapter() {
                private var cancelled = false

                override fun onAnimationStart(animation: Animator?) {
                    cancelled = false
                    onStarted()
                }

                override fun onAnimationEnd(animation: Animator?) {
                    if (!cancelled) {
                        progressAnimator.setupStartValues()
                        progressAnimator.start()
                    }
                }

                override fun onAnimationCancel(animation: Animator?) {
                    cancelled = true
                }
            })

            play(ObjectAnimator.ofFloat(v, View.SCALE_X, 0F, 1F))
                    .with(ObjectAnimator.ofFloat(v, View.SCALE_Y, 0F, 1F))
        }

        return Triple(inAnimator, progressAnimator, outAnimator)
    }
}*/

//fun Color.android(): Int = AndroidColor.parseColor("#" + value.toString(16))
