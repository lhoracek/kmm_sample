package com.jetbrains.kmm.shared

import io.ktor.utils.io.core.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class Greeting {
    fun greeting(): String {
        return "Hello World, ${Platform().platform}!"
    }


    var num = 0
    suspend fun getNumber() = run {
        delay(1000)
        ++num
    }


    fun greetingFlow(): Flow<Int> =
        flow {
            while(true) {
                val number = getNumber()
                emit(number)
            }
        }

    fun greetingFlowWrapped(): CommonFlow<Int> = greetingFlow().asCommonFlow()

}

fun <T> Flow<T>.asCommonFlow(): CommonFlow<T> = CommonFlow(this)

internal expect fun dispatcher(): CoroutineDispatcher

class CommonFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {
    fun watch(block: (T) -> Unit): Closeable {
        val job = Job(/*ConferenceService.coroutineContext[Job]*/)

        onEach {
            block(it)
        }.launchIn(CoroutineScope(dispatcher() + job))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}
