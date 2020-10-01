package io.github.dector.quotes.domain

data class Uuid(val value: String) {
    companion object
}

expect fun Uuid.Companion.new(): Uuid
