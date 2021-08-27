package com.talentsarena.core.thread

/**
 * A task executor that can divide tasks into logical groups. It holds a collection a executors for each group of task.
 *
 */
abstract class TaskExecutor {
    /**
     * Executes the given task in the disk IO thread pool.
     *
     * @param runnable The runnable to run in the disk IO thread pool.
     */
    abstract fun executeOnDiskIO(runnable: Runnable)

    /**
     * Posts the given task to the main thread.
     *
     * @param runnable The runnable to run on the main thread.
     */
    abstract fun postToMainThread(runnable: Runnable)

    /**
     * Returns true if the current thread is the main thread, false otherwise.
     *
     * @return true if we are on the main thread, false otherwise.
     */
    abstract val isMainThread: Boolean

    /**
     * Shut down any main or io threads that is working during the current session.
     */
    abstract fun shutDown()
}