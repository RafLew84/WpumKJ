fun main() {
    Consumer().start()
    try {
        Thread.sleep(500)
    } catch (e: InterruptedException) {
        return
    }
    Producer().start()
}

private var sCounter = 0
// @Volatile private var sCounter = 0

class Producer : Thread() {
    override fun run() {
        while (sCounter < 7) {
            var localValue = sCounter
            localValue++
            println("Producer: $localValue")
            sCounter = localValue
            try {
                sleep(1000)
            } catch (e: InterruptedException) {
                return
            }
        }
        println("Producer is terminated")
    }
}

class Consumer : Thread() {
    override fun run() {
        var localCounter = -1
        while (true) {
            if (localCounter != sCounter) {
                println("Consumer: $sCounter")
                localCounter = sCounter
            }
            if (sCounter >= 7) break
        }
        println("Consumer is terminated")
    }
}
