package io.github.dector.quotes

inline infix fun Any?.equalTo(other: Any?): Boolean = this?.equals(other) ?: other == null

inline fun Any?.nonNull(): Boolean = this != null