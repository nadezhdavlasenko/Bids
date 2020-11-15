import java.util.concurrent.Executors


class Producer(private val bid: Bid) : Runnable {

    override fun run() {
        // Check whether the que for this type of bid is already created
        queueMap[bid.ty] ?: queueMap.put(bid.ty, Executors.newSingleThreadExecutor())
        // Put a task to a queue
        queueMap[bid.ty]!!.execute(LoggingConsumer(bid))
    }
}