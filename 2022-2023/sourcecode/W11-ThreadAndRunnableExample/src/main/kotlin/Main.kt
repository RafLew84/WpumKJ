class C : Thread(){
    override fun run(){
        println("wewnątrz wątku")
    }
}

fun main(){
    val thread = C()
    thread.start()
    println("poza wątkiem")
}