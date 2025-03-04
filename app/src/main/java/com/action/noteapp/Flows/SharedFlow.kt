package com.action.noteapp.Flows
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    val actionFlow = MutableSharedFlow<String>(replay = 1) // Replay the last emitted value

    // Emit some actions AFTER the collectors are started
    val job1 = launch {
        actionFlow.collect { action ->
            println("Collector 1 received: $action")
        }
    }

    val job2 = launch {
        actionFlow.collect { action ->
            println("Collector 2 received: $action")
        }
    }

    // Emit actions AFTER both collectors are active
    delay(100) // Giving a  moment for collectors to start collecting
    actionFlow.emit("Action 1")
    actionFlow.emit("Action 2")

    // Wait for a while before cancelling
    delay(1000)
    job1.cancel()
    job2.cancel()
}

