package io.github.dector.quotes.android.presentation.view

import android.animation.*
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import io.github.dector.quotes.R
import io.github.dector.quotes.presentation.view.Color
import io.github.dector.quotes.presentation.view.IQuotesActionListener
import io.github.dector.quotes.presentation.view.IQuotesView

class QuotesView(val content: View) : IQuotesView {

    var listener: IQuotesActionListener? = null

    private lateinit var rootView: View
    private lateinit var touchView: View
    private lateinit var quoteView: TextView
    private lateinit var authorView: TextView

    private lateinit var loadingTextView: TextView
    private lateinit var loadingImageView: ImageView

    private lateinit var dataContainerView: View
    private lateinit var noDataContainerView: View

    private lateinit var loadingAnimators: Triple<Animator, Animator, Animator>

    override fun init() {
        rootView = content.findViewById(R.id.quotes_root)
        touchView = content.findViewById(R.id.quotes_touch)
        quoteView = content.findViewById(R.id.quotes_quote) as TextView
        authorView = content.findViewById(R.id.quotes_author) as TextView

        loadingTextView = content.findViewById(R.id.quotes_loading_text) as TextView
        loadingImageView = content.findViewById(R.id.quotes_loading_image) as ImageView

        dataContainerView = content.findViewById(R.id.quotes_data_container)
        noDataContainerView = content.findViewById(R.id.quotes_no_data_container)

        touchView.setOnClickListener { listener?.nextQuote() }

        loadingAnimators = createLoadingAnimatorsFor(loadingImageView,
                { loadingImageView.visibility = View.VISIBLE },
                { loadingImageView.visibility = View.GONE })
    }

    override fun showDataState() {
        dataContainerView.visibility = View.VISIBLE
        noDataContainerView.visibility = View.GONE
        loadingTextView.visibility = View.GONE
    }

    override fun showNoDataState() {
        dataContainerView.visibility = View.GONE
        noDataContainerView.visibility = View.VISIBLE
        loadingTextView.visibility = View.GONE
    }

    override fun showLoadingState() {
        dataContainerView.visibility = View.GONE
        noDataContainerView.visibility = View.GONE
        loadingTextView.visibility = View.VISIBLE
    }

    override fun showLoadingProgress() {
        if (loadingAnimators.first.isStarted) {
            loadingAnimators.first.cancel()
            loadingAnimators.first.start()
        } else if (!loadingAnimators.second.isStarted) {
            if (loadingAnimators.third.isStarted) {
                loadingAnimators.third.cancel()
            }
            loadingAnimators.first.start()
        }
    }

    override fun hideLoadingProgress() {
        if (loadingAnimators.first.isStarted) {
            if (loadingAnimators.first.isRunning) {
                loadingAnimators.first.cancel()
                //loadingAnimators.third.start()
            } else {
                loadingAnimators.first.cancel()
            }
        } else if (loadingAnimators.second.isStarted) {
            loadingAnimators.second.cancel()
        } else if (loadingAnimators.third.isStarted) {
            loadingAnimators.third.cancel()
        }
    }

    override fun disableUserInteraction() {
        touchView.isEnabled = false
    }

    override fun enableUserInteraction() {
        touchView.isEnabled = true
    }

    override fun showDisplayingError(message: String) {
        Toast.makeText(content.context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showQuote(quote: String) {
        quoteView.text = quote
    }

    override fun showAuthor(author: String) {
        authorView.text = author
    }

    override fun textColor(color: Color) {
        val colorValue = color.solidValue()

        quoteView.setTextColor(colorValue)
        authorView.setTextColor(colorValue)
        loadingTextView.setTextColor(colorValue)
        loadingImageView.setColorFilter(colorValue)
    }

    override fun backgroundColor(color: Color) {
        rootView.setBackgroundColor(color.solidValue())
    }
}

fun createLoadingAnimatorsFor(v: View, onStarted: () -> Unit, onFinished: ()-> Unit): Triple<Animator, Animator, Animator> {
    // Out animator
    val outAnimator = AnimatorSet().apply {
        duration = 500
        interpolator = TimeInterpolator { t -> t*t*t*t }

        addListener(object : AnimatorListenerAdapter() {
            private var cancelled = false

            override fun onAnimationStart(animation: Animator?) {
                cancelled = false
            }

            override fun onAnimationEnd(animation: Animator?) {
                if (!cancelled)
                    onFinished()
            }

            override fun onAnimationCancel(animation: Animator?) {
                cancelled = true
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

        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationCancel(animation: Animator?) {
                outAnimator.start()
            }
        })
    }

    // In animator
    val inAnimator = AnimatorSet().apply {
        duration = 500
        interpolator = TimeInterpolator { t -> t*t*t*t }
        startDelay = 100

        addListener(object : AnimatorListenerAdapter() {
            private var cancelled = false

            override fun onAnimationStart(animation: Animator?) {
                cancelled = false
                onStarted()
            }

            override fun onAnimationEnd(animation: Animator?) {
                if (! cancelled) {
                    progressAnimator.setupStartValues()
                    progressAnimator.start()
                }
            }

            override fun onAnimationCancel(animation: Animator?) {
                cancelled = true
                outAnimator.start()
            }
        })

        play(ObjectAnimator.ofFloat(v, View.SCALE_X, 0F, 1F))
                .with(ObjectAnimator.ofFloat(v, View.SCALE_Y, 0F, 1F))
    }

    return Triple(inAnimator, progressAnimator, outAnimator)
}