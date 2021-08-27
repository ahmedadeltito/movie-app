package com.talentsarena.core.thread

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory
import java.util.concurrent.atomic.AtomicInteger

/**
 * Default implementation for [TaskExecutor]
 */
class DefaultTaskExecutor : TaskExecutor() {

    private val lock = Any()

    private val diskIO = Executors.newFixedThreadPool(
        4,
        object : ThreadFactory {
            val THREAD_NAME_STEM = "arch_disk_io_%d"
            private val threadId = AtomicInteger(0)

            override fun newThread(runnable: Runnable): Thread = Thread(runnable).apply {
                name = String.format(THREAD_NAME_STEM, threadId.getAndIncrement())
            }
        }
    )

    @Volatile
    private var mainHandler: Handler? = null

    override fun executeOnDiskIO(runnable: Runnable) {
        diskIO.execute(runnable)
    }

    override fun postToMainThread(runnable: Runnable) {
        val safeMainThread = mainHandler ?: synchronized(lock) {
            val mainHandler = Handler(Looper.getMainLooper())
            this.mainHandler = mainHandler
            mainHandler
        }
        safeMainThread.post(runnable)
    }

    override val isMainThread: Boolean
        get() = Looper.getMainLooper().thread === Thread.currentThread()

    override fun shutDown() {
        mainHandler?.removeCallbacksAndMessages(null)
        diskIO.shutdownNow()
    }

    companion object {

        @Volatile
        private var INSTANCE: DefaultTaskExecutor? = null

        /**
         * Returns an instance of the task executor.
         *
         * @return The singleton ArchTaskExecutor.
         */
        private fun getInstance(): DefaultTaskExecutor = INSTANCE ?: synchronized(DefaultTaskExecutor::class.java) {
            val instance = DefaultTaskExecutor()
            INSTANCE = instance
            instance
        }
    }
}