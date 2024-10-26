package display

import kolour.*
import gameState.GameState

// Instructions to draw the coins in the grid
// For each line, it will be a map that gives instructions for each type of coin
val coinPatterns = listOf(
    "   ▟████▙   ",
    "  ████████  ",
    "  ████████  ",
    "  ████████  ",
    "   ▜████▛   "
)

// Generate the CoinInstructions list by mapping over coinPatterns
val CoinInstructions = coinPatterns.map { pattern ->
    mapOf(
        null to "            ",
        false to pattern.grey(),
        true  to pattern.yellow()
    )
}

fun getCoinInstruction(row: Int, type: Boolean?): String {
    return CoinInstructions[row][type]!! // !! to assert it will be a string
}

fun buildTable(state: GameState) {
    // Build table header
    println("┌${"────────────┬".repeat(state.rows.toInt() - 1)}────────────┐")
    // Build the coins line by line
    repeat(5) { i ->
        val row = StringBuilder() // Using string builder for faster concat

        repeat(state.rows.toInt()) { c ->
            row.append(getCoinInstruction(i, state.getCoin(c + 1)) + "│")
        }
        println("│${row}")
    }
    // Make the bottom border
    println("└${"────────────┴".repeat(state.rows.toInt() - 1)}────────────┘")
}

fun displayState(state: GameState) {
    buildTable(state)
}