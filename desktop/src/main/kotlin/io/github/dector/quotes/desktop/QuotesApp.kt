package io.github.dector.quotes.desktop

import io.github.dector.quotes.presentation.presenter.QuotesPresenter
import io.github.dector.quotes.presentation.providers.ColorPairProvider
import io.github.dector.quotes.presentation.view.Color
import io.github.dector.quotes.presentation.view.IQuotesActionListener
import io.github.dector.quotes.presentation.view.IQuotesView
import io.github.dector.quotes.repositories.MockQuotesRepository
import io.github.dector.quotes.usecases.GetRandomQuoteUseCase
import javafx.application.Application
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.layout.BorderPane
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.text.TextAlignment
import javafx.stage.Stage

fun main(args: Array<String>) {
    Application.launch(QuotesApp::class.java, *args)
}

class QuotesApp : Application() {

    lateinit var presenter: QuotesPresenter
    lateinit var view: QuotesView

    override fun start(primaryStage: Stage) {
        presenter = QuotesPresenter(GetRandomQuoteUseCase(MockQuotesRepository()), ColorPairProvider())
        view = QuotesView()

        primaryStage.title = "Quotes"
        primaryStage.scene = Scene(view.root, 640.0, 480.0)
        primaryStage.show()

        presenter.view = view
        view.actionListener = presenter

        presenter.init()
    }
}

class QuotesView : IQuotesView {

    var actionListener: IQuotesActionListener? = null

    val root = BorderPane()
    private val rootContent = StackPane().apply {
        val nextQuoteAction = { actionListener?.nextQuote() }

        prefWidth = Double.MAX_VALUE
        prefHeight = Double.MAX_VALUE

        alignment = Pos.CENTER

        onKeyTyped = EventHandler<KeyEvent> { if (it.character == " ") nextQuoteAction() }
        onMouseClicked = EventHandler<MouseEvent> { nextQuoteAction() }

        root.center = this
    }
    private val content = VBox().apply {
        prefWidth = Double.MAX_VALUE
        prefHeight = Double.MAX_VALUE
        padding = Insets(0.0, 32.0, 0.0, 32.0)
        spacing = 32.0

        alignment = Pos.CENTER

        rootContent.children.add(this)
    }
    private val quote = Label().apply {
        prefWidth = Double.MAX_VALUE
        isWrapText = true

        alignment = Pos.CENTER
        textAlignment = TextAlignment.CENTER

        style = "-fx-font-size: 25px"

        content.children.add(this)
    }
    private val author = Label().apply {
        prefWidth = Double.MAX_VALUE

        alignment = Pos.CENTER_RIGHT
        textAlignment = TextAlignment.RIGHT

        style = "-fx-font-size: 20px"

        content.children.add(this)
    }

    override fun init() {
        rootContent.requestFocus()
    }

    override fun showDataState() {
    }

    override fun showNoDataState() {
    }

    override fun showLoadingState() {
    }

    override fun showLoadingProgress() {
    }

    override fun hideLoadingProgress() {
    }

    override fun showDisplayingError(message: String) {
    }

    override fun disableUserInteraction() {
        // FIXME add presenter control
    }

    override fun enableUserInteraction() {
    }

    override fun showQuote(quote: String) {
        this.quote.text = quote
    }

    override fun showAuthor(author: String) {
        this.author.text = author
    }

    override fun textColor(color: Color) {
        quote.textFill = javafx.scene.paint.Color.web(color.hexValue())
        author.textFill = javafx.scene.paint.Color.web(color.hexValue())
    }

    override fun backgroundColor(color: Color) {
        rootContent.style = "-fx-background-color: ${color.hexValue()}"
    }
}

fun Color.hexValue(): String {
    var value = Integer.toHexString(this.value)
    while (value.length < 6) {
        value = "0" + value
    }
    return "#$value"
}