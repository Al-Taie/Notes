package com.altaie.notes.utils


fun <T> Event<T>?.observe(onEventUnhandledContent: (T) -> Unit) {
    this?.getContentIfNotHandled()?.let(onEventUnhandledContent)
}