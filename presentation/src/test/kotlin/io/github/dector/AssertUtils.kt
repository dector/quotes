package io.github.dector

inline infix fun Any?.equalTo(other: Any?): Boolean = this?.equals(other) ?: other == null