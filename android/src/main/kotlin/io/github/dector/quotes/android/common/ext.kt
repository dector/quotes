package io.github.dector.quotes.android.common

import trikita.anvil.BaseDSL.sip
import trikita.anvil.DSL.dip

val Int.dp get() = dip(this)
val Int.sp: Float get() = sip(this.toFloat())
