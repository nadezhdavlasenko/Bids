data class Bid(
    val id: String,
    val ts: String,
    val ty: String,
    val pl: String
)

data class JsonBid(
    val bid: Bid
)