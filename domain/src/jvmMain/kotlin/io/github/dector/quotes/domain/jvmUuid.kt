package io.github.dector.quotes.domain

import java.util.UUID

actual fun Uuid.Companion.new(): Uuid =
    Uuid(UUID.randomUUID().toString())
