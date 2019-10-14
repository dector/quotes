package io.github.dector.quotes.android.presentation.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import io.github.dector.quotes.R
import io.github.dector.quotes.android.presentation.Msg
import io.github.dector.quotes.android.presentation.State
import io.github.dector.quotes.presentation.view.Color

class QuotesView(val content: View) {

    var dispatcher: ((Msg) -> Unit)? = null

    private lateinit var rootView: View
    private lateinit var touchView: View
    private lateinit var quoteView: TextView
    private lateinit var authorView: TextView

    private lateinit var loadingTextView: TextView
    private lateinit var loadingImageView: ImageView

    private lateinit var dataContainerView: View
    private lateinit var noDataContainerView: View

    fun init() {
        rootView = content.findViewById(R.id.quotes_root)
        touchView = content.findViewById(R.id.quotes_touch)
        quoteView = content.findViewById(R.id.quotes_quote) as TextView
        authorView = content.findViewById(R.id.quotes_author) as TextView

        loadingTextView = content.findViewById(R.id.quotes_loading_text) as TextView
        loadingImageView = content.findViewById(R.id.quotes_loading_image) as ImageView

        dataContainerView = content.findViewById(R.id.quotes_data_container)
        noDataContainerView = content.findViewById(R.id.quotes_no_data_container)

        touchView.setOnClickListener { dispatcher?.invoke(Msg.NextQuote) }
    }

    fun display(state: State) {
        dataContainerView.visibility = if (state is State.Data) View.VISIBLE else View.GONE
        noDataContainerView.visibility = if (state is State.Empty) View.VISIBLE else View.GONE

        if (state is State.Data) {
            val quote = state.quote

            showQuote(quote.content)
            showAuthor(quote.author)
            textColor(state.textColor)
            backgroundColor(state.backgroundColor)
        }
    }

    private fun showQuote(quote: String) {
        quoteView.text = quote
    }

    private fun showAuthor(author: String) {
        authorView.text = author
    }

    private fun textColor(color: Color) {
        val colorValue = color.solidValue()

        quoteView.setTextColor(colorValue)
        authorView.setTextColor(colorValue)
        loadingTextView.setTextColor(colorValue)
        loadingImageView.setColorFilter(colorValue)
    }

    private fun backgroundColor(color: Color) {
        rootView.setBackgroundColor(color.solidValue())
    }
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
