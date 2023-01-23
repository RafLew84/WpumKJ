import kotlinx.coroutines.*
import java.math.BigInteger

const val sum = 17

fun main() {
    runBlocking {
        val result = async {
            var result = BigInteger.ZERO
            for (i in 0..sum) {
                result = result.add(BigInteger.valueOf(i.toLong()))
            }
            result
        }
        println("waiting")
        println(result.await())
    }
}

//private var calculatedSum = BigInteger.ZERO
//private const val NUM_THREADS = 10
//
//class Sum(private val from: Long, private val to: Long) :  CoroutineScope by CoroutineScope(Dispatchers.Default) {
//    private var localSum: BigInteger = BigInteger.ZERO
//    fun call(): BigInteger {
//        for (i in from..to) {
//            localSum = localSum.add(BigInteger.valueOf(i))
//        }
//        return localSum
//    }
//}
//
//fun main() {
//    runBlocking {
//        val summationTasks = mutableListOf<Deferred<BigInteger>>()
//        val n = 1000000L
//        val taskNum = n / NUM_THREADS
//        for (i in 0 until NUM_THREADS) {
//            val fromInInnerRange = (taskNum * i) + 1
//            val toInInnerRange = taskNum * (i + 1)
//            println("Coroutine dla sumy w zakresie $fromInInnerRange - $toInInnerRange")
//
//            val summationTask = async { Sum(fromInInnerRange, toInInnerRange).call() }
//            summationTasks.add(summationTask)
//        }
//        for (partialSum in summationTasks) {
//            calculatedSum = calculatedSum.add(partialSum.await())
//        }
//        println("Suma = $calculatedSum")
//    }
//}
