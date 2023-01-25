import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

//fun main() {
//    suspend fun a() {
//        delay(100)
//        println("a")
//    }
//    suspend fun b() {
//        delay(100)
//        println("b")
//    }
//    runBlocking {
//        //println("Done")
//        a()
//        //println("Done")
//        b()
//        println("Done")
//    }
//}

//fun main() {
//    suspend fun a(): Int {
//        delay(1000)
//        return 1
//    }
//    fun b(a: Int) {
//        println("Using $a")
//    }
//    runBlocking {
//        val a = a()
//        b(a)
//    }
//}

//fun main() {
//    runBlocking {
//        println("Parent starting")
//        launch {
//            println("Child a starting")
//            delay(1000)
//            println("Child a complete")
//        }
//        launch {
//            println("Child b starting")
//            delay(100)
//            println("Child b complete")
//        }
//        println("Parent complete")
//    }
//    println("Parent returned")
//}