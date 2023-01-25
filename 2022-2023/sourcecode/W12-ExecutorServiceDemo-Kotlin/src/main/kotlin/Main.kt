import java.math.BigInteger
import java.util.concurrent.Callable
import java.util.concurrent.ExecutionException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
const val sum = 17

fun main(args: Array<String>) {
    val executor = Executors.newSingleThreadExecutor()
    val callable: Callable<BigInteger> = Callable {
        var result = BigInteger.ZERO
        for (i in 0..sum) {
            result = result.add(BigInteger.valueOf(i.toLong()))
        }
        result
    }
    val taskFuture = executor.submit(callable)
    try {
        while (!taskFuture.isDone) println("waiting")
        println(taskFuture.get())
    } catch (ee: ExecutionException) {
        System.err.println("task threw an exception")
        ee.printStackTrace()
    } catch (ie: InterruptedException) {
        System.err.println("interrupted while waiting")
    }
    executor.shutdown()
}