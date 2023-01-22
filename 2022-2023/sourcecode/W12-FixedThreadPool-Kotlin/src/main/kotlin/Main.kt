import java.math.BigInteger
import java.util.concurrent.*

var calculatedSum: BigInteger = BigInteger.ZERO
const val NUM_THREADS = 10

fun main(args: Array<String>) {
    val executorService = Executors.newFixedThreadPool(NUM_THREADS)
    val summationTasks: MutableList<Future<BigInteger>> = ArrayList()
    val n = 1000000L
    val taskNum = n / 10 // 10 tasks

    for (i in 0 until NUM_THREADS) {
        val fromInInnerRange = taskNum * i + 1
        val toInInnerRange = taskNum * (i + 1)
        System.out.printf("Wątek dla sumy w zakresie %d - %d %n", fromInInnerRange, toInInnerRange)
        val summationTask: Callable<BigInteger> = Sum(fromInInnerRange, toInInnerRange)
        val futureSum = executorService.submit(summationTask)
        summationTasks.add(futureSum)
    }

    for (partialSum in summationTasks) {
        try {
            calculatedSum = calculatedSum.add(partialSum.get())
        } catch (exception: CancellationException) {
            exception.printStackTrace()
        } catch (exception: ExecutionException) {
            exception.printStackTrace()
        } catch (exception: InterruptedException) {
            exception.printStackTrace()
        }
    }

    System.out.printf("Suma = %d", calculatedSum)
    executorService.shutdown()
}

class Sum(private var from: Long, private var to: Long) : Callable<BigInteger> {
    private var localSum: BigInteger = BigInteger.ZERO
    override fun call(): BigInteger {
        for (i in from..to) localSum = localSum.add(BigInteger.valueOf(i))
        return localSum
    }
}