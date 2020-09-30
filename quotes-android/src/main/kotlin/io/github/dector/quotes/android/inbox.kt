@file:Suppress("NOTHING_TO_INLINE")

package io.github.dector.quotes.android

import org.koin.core.KoinApplication
import org.koin.core.module.Module

inline fun KoinApplication.modules(vararg modules: Module) = modules(modules.asList())
