import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.concurrent.fixedRateTimer

// Executor service is used as a queue here as it uses the queue inside
val queueMap = HashMap<String, ExecutorService>()

val mapper = jacksonObjectMapper()
const val path = "bids.json"

fun getBids(): List<Bid> {
    val file = object {}.javaClass.getResource(path).readText()
    return mapper.readValue(file, object : TypeReference<List<JsonBid>>() {}).map { it.bid }
}


fun main() {
    // Read the file and populate queues each minut
    fixedRateTimer(period = 60_000) {
        val bids: List<Bid> = getBids()
        val executor = Executors.newCachedThreadPool()
        bids.forEach { executor.execute(Producer(it)) }

    }
}

