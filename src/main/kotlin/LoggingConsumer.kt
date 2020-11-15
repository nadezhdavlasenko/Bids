import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

class LoggingConsumer(private val bid: Bid) : Runnable {
    private val log: Logger = LoggerFactory.getLogger(LoggingConsumer::class.java)

    override fun run() {
        // Timestamp is logged by lib
        log.info("Queque name = ${bid.ty}. Bid id = ${bid.id}. Payload = ${String(Base64.getDecoder().decode(bid.pl))}")
    }
}