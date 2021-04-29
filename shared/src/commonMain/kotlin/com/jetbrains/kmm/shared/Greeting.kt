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


    fun greetingFlow(number: Int): Flow<Response<Int>> =
        flow {
            emit(Response.Progress)
            while(true) {
                val number = getNumber()
                emit(Response.Success(number))
            }
        }

    fun greetingFlowWrapped(number: Int): CommonFlow<Response<Int>> = greetingFlow(number).asCommonFlow()

}


sealed class Response<out T>{
    data class Success<T>(val value: T): Response<T>()
    object Progress: Response<Nothing>()
}


fun <T> Flow<T>.asCommonFlow(): CommonFlow<T> = CommonFlow(this)

internal expect fun mainDispatcher(): CoroutineDispatcher

class CommonFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {
    fun watch(block: (T) -> Unit): Closeable {
        val job = Job(/*ConferenceService.coroutineContext[Job]*/)

        onEach {
            block(it)
        }.launchIn(CoroutineScope(Dispatchers.Main + job))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}
