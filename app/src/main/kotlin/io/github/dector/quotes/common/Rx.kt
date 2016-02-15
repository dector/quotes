package io.github.dector.quotes.common

import rx.subjects.BehaviorSubject

inline fun BehaviorSubject<Unit>.onNext() = this.onNext(Unit)