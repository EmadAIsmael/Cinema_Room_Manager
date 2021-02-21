package cinema


class Cinema(private val rows: Int, private val seatsPerRow: Int) {

    data class TicketPrice(val front: Int, val back: Int)

    private var seating = Array(rows) { Array(seatsPerRow) { "S" } }
    private val frontTicketPrice = 10
    private val backTicketPrice = 8
    private val totalSeats = rows * seatsPerRow
    private var ticketPrice: TicketPrice = TicketPrice(10, 10)
    private var profit = 0

    init {
        ticketPrice = if (totalSeats <= 60) {
            TicketPrice(frontTicketPrice, frontTicketPrice)
        } else {
            TicketPrice(frontTicketPrice, backTicketPrice)
        }
    }

    fun book(row: Int, seat: Int) {
        val price = getTicketPrice(row)
        println("\nTicket price: $$price\n")
        seating[row - 1][seat - 1] = "B"
    }

    private fun isFrontRow(row: Int): Boolean = row in 1..(rows / 2)

    private fun getTicketPrice(row: Int) =
        if (isFrontRow(row)) ticketPrice.front
        else ticketPrice.back

    fun calculateProfit(): Int {
        val frontRows = rows / 2
        val backRows = rows - frontRows

        profit = (frontRows * seatsPerRow) * ticketPrice.front +
                (backRows * seatsPerRow) * ticketPrice.back
        return profit
    }

    override fun toString(): String {
        var layout = ""
        layout += "Cinema:\n  "
        layout += (1..seatsPerRow).joinToString(" ") + "\n"
        for (row in 1..rows) {
            layout += "$row "
            for (seat in 1..seatsPerRow)
                layout += "${seating[row - 1][seat - 1]} "
            layout += "\n"
        }
        return layout
    }
}


fun main() {
    println("Enter the number of rows:")
    val rows = readLine()!!.toInt()
    println("Enter the number of seats in each row:")
    val seatsPerRow = readLine()!!.toInt()

    val cinema = Cinema(rows, seatsPerRow)
    println(cinema)

    println("Enter a row number:")
    val row = readLine()!!.toInt()
    println("Enter a seat number in that row:")
    val seat = readLine()!!.toInt()

    cinema.book(row, seat)
    println(cinema)

//    val profit: Int = cinema.calculateProfit()
//    println("Total income:\n$$profit")
}
