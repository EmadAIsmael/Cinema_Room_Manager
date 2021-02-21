package cinema

fun main() {
    println("Enter the number of rows:")
    val rows = readLine()!!.toInt()
    println("Enter the number of seats in each row:")
    val seatsPerRow = readLine()!!.toInt()
    var profit = 0

    val totalSeats = rows * seatsPerRow
    if (totalSeats <= 60) {
        val ticket = 10
        profit = totalSeats * ticket
    }
    else {
        val frontRows = rows / 2
        val backRows = rows - frontRows
        val frontTickets = 10
        val backTickets = 8
        profit = (frontRows * seatsPerRow) * frontTickets + (backRows * seatsPerRow) * backTickets
    }

    println("Total income:\n$$profit")

//    println("Cinema:")
//    print("  ")
//    println((1..seatsPerRow).joinToString(" "))
//    for (row in 1..rows) {
//        print("$row ")
//        for (seat in 1..seatsPerRow)
//            print("S ")
//        println()
//    }
}
