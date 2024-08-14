package com.anant.threading.com.anant.threading

import java.util.concurrent.AbstractExecutorService
import java.util.concurrent.BlockingQueue
import java.util.concurrent.Executors
import java.util.concurrent.RejectedExecutionException
import java.util.concurrent.ThreadFactory
import java.util.concurrent.TimeUnit


class CustomThreadPoolExecutor(
    val poolSize: Int,
    val workQueue: BlockingQueue<Runnable>
): AbstractExecutorService() {

    inner class WorkerThreadTask: Runnable {
        override fun run() {
            while(true) {
                val work = workQueue.take()
                work.run()
            }
        }
    }

    private val threadFactory: ThreadFactory = Executors.defaultThreadFactory()
    val threads = mutableListOf<Thread>()
    init {
        (1..poolSize).forEach { _ -> threads.add(threadFactory.newThread(WorkerThreadTask())) }
        threads.forEach { it.start() }
    }

    override fun execute(command: Runnable) {
        if(!workQueue.offer(command)) {
            throw RejectedExecutionException()
        }
    }

    fun getActiveCount(): Int {
        TODO("Not yet implemented")
    }

    ///////////// To be implemented later
    override fun shutdown() {
        TODO("Not yet implemented")
    }

    override fun shutdownNow(): MutableList<Runnable> {
        TODO("Not yet implemented")
    }

    override fun isShutdown(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isTerminated(): Boolean {
        TODO("Not yet implemented")
    }

    override fun awaitTermination(timeout: Long, unit: TimeUnit): Boolean {
        TODO("Not yet implemented")
    }
}