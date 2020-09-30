package io.github.dector.quotes.domain

data class Quote(
    val uuid: Uuid,
    val content: String,
    val author: String
) {
    constructor(content: String, author: String) :
        this(Uuid.new(), content, author)

    val quote: String get() = content
}
