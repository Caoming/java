package coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    println("start")
    GlobalScope.launch {
        delay(100)
        println("World")
    }

    println("Hello")
    runBlocking {
        delay(150)
    }

    val job = GlobalScope.launch {
        delay(100)
        println("test")
    }

    println("hi....")
    runBlocking {
        delay(50)
        job.join()
    }


}