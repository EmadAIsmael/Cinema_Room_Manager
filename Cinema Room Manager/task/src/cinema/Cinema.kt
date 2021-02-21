package cinema

fun main() {
    val rows = 7
    val seatsPerRow = 8

    println("Cinema:")
    print("  ")
    println((1..seatsPerRow).joinToString(" "))
    for (row in 1..rows) {
        print("$row ")
        for (seat in 1..seatsPerRow)
            print("S ")
        println()
    }
}
