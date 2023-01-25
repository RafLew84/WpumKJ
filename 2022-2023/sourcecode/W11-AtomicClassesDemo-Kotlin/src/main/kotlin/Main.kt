private const val COUNT_UP_TO = 1000
private const val NUM_OF_THREADS = 100

@Volatile
private var mCount = 0

// private val mCount = AtomicInteger(0)

fun main() {
    mCount = 0
    //mCount.set(0)
    for (i in 0 until NUM_OF_THREADS) {
        startThread()
    }
    Thread.sleep(2000)
    println(mCount)
    //println(mCount.get())
}

private fun startThread() {
    Thread {
        for (i in 0 until COUNT_UP_TO) {
            mCount++
            //mCount.incrementAndGet()
        }
    }.start()
}