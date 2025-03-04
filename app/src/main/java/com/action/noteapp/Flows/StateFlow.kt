package com.action.noteapp.Flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val stateFlow = MutableStateFlow("Hello, Stateflow!") //Creating Mutable state flow

    //launching coroutine to collect values
    launch{
        stateFlow.collect{
                value -> println("Collected : $value") //prints updated value
        }
    }

    delay(1000)
    stateFlow.value = "updated value" // Update Stateflow value
    delay(1000) //  Wait another second to observe changes before exiting
}
