import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//fun main() {
//    var x = 0
//    val job = GlobalScope.launch {
//        while (true) {
//            x += 1
//            delay(10)
//            println(x)
//        }
//    }
//    runBlocking {
//        delay(100)
//        job.cancel()
//    }
//}

fun main() {
    val job = GlobalScope.launch {
        delay(1000L)
        println("Hello")
    }
    runBlocking {
        job.join()
        println("World")
    }
}