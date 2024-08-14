package com.anant.threading

import com.anant.threading.com.anant.threading.CustomThreadPoolExecutor
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue

class Task(val i: Int) : Runnable {
    override fun run() {
        println("Task is running: $i, ${Thread.currentThread().name}")
    }
}

fun main() {
    val threadPool = CustomThreadPoolExecutor(10, LinkedBlockingQueue())
    threadPool.execute(Task(1))
    threadPool.execute(Task(2))
    Thread.sleep(1000)
    threadPool.execute(Task(3))
    threadPool.execute((Task(4)))
}
