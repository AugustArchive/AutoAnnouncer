// Credit: https://github.com/KurozeroPB/CoroutineTimer
package dev.august.plugins.minecraft.announcer.util

import kotlinx.coroutines.*

class Timer {
  private val scope: Scope = Scope()

  fun createInterval(milliseconds: Int = 1000, action: suspend Scope.() -> Unit) = GlobalScope.launch {
    while (true) {
      if (scope.cancelled()) break

      action(scope)
      delay(milliseconds.toLong())
      yield()
    }
  }

  fun cancel() = scope.cancel()
}

class Scope {
  private var cancelled: Boolean = false

  fun cancel() {
    cancelled = true
  }

  fun cancelled() = cancelled
}