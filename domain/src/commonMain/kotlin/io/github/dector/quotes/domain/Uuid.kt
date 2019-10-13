package io.github.dector.quotes.domain

inline class Uuid(val value: String) {
    companion object
}

expect fun Uuid.Companion.new(): Uuid
