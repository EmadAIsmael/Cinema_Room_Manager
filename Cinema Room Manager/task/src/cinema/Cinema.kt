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

    private fun bookSeat() {
        val (row, seat) = askForSeat()
        val price = getTicketPrice(row)
        println("Ticket price: $$price\n")
        seating[row - 1][seat - 1] = "B"
    }

    private fun isFreeSeat(row: Int, seat: Int): Boolean = seating[row - 1][seat - 1] == "S"

    private fun isValidSeat(row: Int, seat: Int): Boolean = row in 1..rows &&
            seat in 1..seatsPerRow

    private fun askForSeat(): Pair<Int, Int> {
        while (true) {
            println("Enter a row number:")
            val row = readLine()!!.toInt()
            println("Enter a seat number in that row:")
            val seat = readLine()!!.toInt()

            if (!isValidSeat(row, seat)) println("\nWrong input!\n")
            else if (!isFreeSeat(row, seat)) println("\nThat ticket has already been purchased!\n")
            else return Pair(row, seat)
        }
    }

    private fun isFrontRow(row: Int): Boolean = row in 1..(rows / 2)

    private fun getTicketPrice(row: Int) =
        if (isFrontRow(row)) ticketPrice.front
        else ticketPrice.back

    private fun totalIncome(): Int {
        val frontRows = rows / 2
        val backRows = rows - frontRows

        profit = (frontRows * seatsPerRow) * ticketPrice.front +
                (backRows * seatsPerRow) * ticketPrice.back
        return profit
    }

    private fun currentIncome(): Double {
        var income = 0.0
        for (r in 0 until rows)
            for (s in 0 until seatsPerRow)
                if (seating[r][s] == "B") income += getTicketPrice(r + 1)
        return income
    }

    private fun purchasedTickets(): Int {
        var sum = 0
        for (r in 0 until rows)
            for (s in 0 until seatsPerRow)
                if (seating[r][s] == "B") sum++
        return sum
    }

    private fun statistics() {
        val p = purchasedTickets()
        val ci = currentIncome()
        val ti = totalIncome()
        // round(p / totalSeats * 100.0) / 100
        println()
        println(
            """
            Number of purchased tickets: $p
            Percentage: ${"%.2f".format(p.toDouble() / totalSeats * 100)}%
            Current income: $${ci.toInt()}
            Total income: $${ti}
        """.trimIndent()
        )
        println()
    }

    fun menu() {
        while (true) {
            var selection = 10
            while (selection !in arrayOf(0, 1, 2, 3)) {
                println(
                    """
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit
            """.trimIndent()
                )

                selection = readLine()!!.toInt()
            }
            when (selection) {
                1 -> println(this)
                2 -> bookSeat()
                3 -> statistics()
                0 -> return
            }
        }
    }

    override fun toString(): String {
        var layout = "\n"
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
    cinema.menu()

}
