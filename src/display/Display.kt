package display

import kolour.*
import gameState.GameState
import java.awt.Taskbar

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
    val header = StringBuilder() // Using string builder for faster concat
    header.append("┌")

    // Loop through the number of rows to build each header cell
    repeat(state.rows.toInt()) { c ->
        val slotNumber = (c + 1).toString().bold()
        header.append("─$slotNumber")
        // Since we have the bold escape code applied, we need to factor in the extra 8 characters
        header.append("${"─".repeat(11 - (slotNumber.length - 8))}┬")
    }

    // Remove the last '┬' and add the closing corner
    header.setLength(header.length - 1)
    header.append("┐")
    println(header.toString())

    // Build the coins line by line
    repeat(5) { i ->
        val row = StringBuilder()

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