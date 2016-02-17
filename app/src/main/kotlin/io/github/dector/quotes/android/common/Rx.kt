package io.github.dector.quotes.android.common

import rx.subjects.BehaviorSubject

inline fun BehaviorSubject<Unit>.onNext() = this.onNext(Unit)