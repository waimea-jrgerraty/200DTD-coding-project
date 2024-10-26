package display

import kolour.*
import gameState.GameState

// Instructions to draw the coins in the grid
val coinPatterns = listOf(
    "   ▟████▙   ",
    "  ████████  ",
    "  ████████  ",
    "  ████████  ",
    "   ▜████▛   "
)

// Generate the CoinInstructions list by mapping over coinPatterns
// This means we can index it by line AND coinData to get the different colors
val CoinInstructions = coinPatterns.map { pattern ->
    mapOf(
        null to "            ",
        false to pattern.grey(),
        true  to pattern.yellow()
    )
}

// row being the line, type being the coinData value returned through getCoin()
fun getCoinInstruction(row: Int, type: Boolean?): String {
    return CoinInstructions[row][type]!! // !! to assert it will be a string
}

// Display the current state of the game as a table
fun displayState(state: GameState) {
    // Build table header
    val header = StringBuilder() // Using string builder for faster concat
    header.append("┌")

    // Loop through the number of slots to build each header cell
    repeat(state.slots.toInt()) { c ->
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

        repeat(state.slots.toInt()) { c ->
            row.append(getCoinInstruction(i, state.getCoin(c + 1)) + "│")
        }
        println("│${row}")
    }

    // Make the bottom border
    println("└${"────────────┴".repeat(state.slots.toInt() - 1)}────────────┘")
}

// Generate a splash screen with instructions at runtime
fun splash() {
    // ASCII render generated at https://manytools.org/hacker-tools/convert-image-to-ansi-art/
    println((
            "                  ▒▓▓▓▓▒                                                        \n" +
            "             ░░▓▓▓▓▓▓▓▓▓▓▓▓▓      ▓▓▓▓▓▓▓         ░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒░             \n" +
            "            ▒▓▓▓▓▓▓    ░▓▓▓▓▓▒    ▓▓▓▓▓▓▓         ░▓▓▓▓▓▓   ░▓▓▓▓▓▓▒░           \n" +
            "           ▓▓▓▓▓▓▓      ▓▓▓▓▓▓▒   ▓▓▓▓▓▓▓         ░▓▓▓▓▓▓     ▒▓▓▓▓▓▓           \n" +
            "          ░▓▓▓▓▓▓       ▒▓▓▓▓▓▓░  ▓▓▓▓▓▓▓         ░▓▓▓▓▓▓      ▓▓▓▓▓▓░          \n" +
            "          ░▓▓▓▓▓▓        ▓▓▓▓▓▓░  ▓▓▓▓▓▓▓         ░▓▓▓▓▓▓      ░▓▓▓▓▓▓          \n" +
            "          ░▓▓▓▓▓▓        ▓▓▓▓▓▓░  ▓▓▓▓▓▓▓         ░▓▓▓▓▓▓      ░▓▓▓▓▓▓          \n" +
            "          ░░▓▓▓▓▓░      ▓▓▓▓▓▓▒   ▓▓▓▓▓▓▓         ░▓▓▓▓▓▓      ▓▓▓▓▓▓░          \n" +
            "           ░▓▓▓▓▓▓      ▓▓▓▓▓▓░   ▓▓▓▓▓▓▓         ░▓▓▓▓▓▓     ░▓▓▓▓▓▒           \n" +
            "            ▓▒▓▓▓▓▓▓░░▓▓▓▓▓▓▓     ▓▓▓▓▓▓▓░▓▒▓▓▓▓  ░▓▓▓▓▓▓▓▓░▓▓▓▓▓▓▓░            \n" +
            "               ░▒▓▓▓▓▓▓▓▓▒░       ▓▓▓▓▓▓▓▓▓▓▓▓▓▓  ░▓▓▓▓▓▓▓▓▓▓▓░▒░               \n" +
            "                                                                                \n").grey())
    println((
            "    ▒▒▓▓▓▓▓▓▓▓▓▓▓▒       ▓▓▓▓▓▓▓▓▓▓▓▓▒      ░▓▓▓▓▓▓          ▒▓▓▓▓▓▓▓▓▓▓▓▓▓▒░   \n" +
            "  ▒▓▓▓▓▓▒░  ░▓▓▓▓░     ▒▓▓▓▓▓▒░  ▓▓▓▓▓▓▓     ▓▓▓▓▓▓          ░▓▓▓▓▓▒░░░░▓▓▓▓▓░░ \n" +
            " ▒▓▓▓▓▓▓      ░▓▓     ░▓▓▓▓▓░     ░▓▓▓▓▓▒    ▓▓▓▓▓▓          ░▓▓▓▓▓▒    ░▓▓▓▓▓▓░\n" +
            "▒▓▓▓▓▓▓              ▓▓▓▓▓▓▒       ▓▓▓▓▓▓▒   ▓▓▓▓▓▓          ░▓▓▓▓▓▒     ▓▓▓▓▓▓▒\n" +
            "▒▓▓▓▓▓▓   ▓▒▒▒▒▒▒▒▒  ▒▓▓▓▓▓▓       ▒▓▓▓▓▓▓   ▓▓▓▓▓▓          ░▓▓▓▓▓▒     ░▓▓▓▓▓▓\n" +
            "▒▓▓▓▓▓▓   ▓▓▓▓▓▓▓▓▓  ▒▓▓▓▓▓▒       ░▓▓▓▓▓▓   ▓▓▓▓▓▓          ░▓▓▓▓▓▒     ░▓▓▓▓▓▓\n" +
            "▓▓▓▓▓▓▓     ▓▓▓▓▓▓▓  ▓▓▓▓▓▓▒       ▓▓▓▓▓▓▒   ▓▓▓▓▓▓          ░▓▓▓▓▓▒     ▓▓▓▓▓▓▒\n" +
            " ▓▓▓▓▓▓▓    ▓▓▓▓▓▓▓   ░▓▓▓▓▓░     ▒▓▓▓▓▓▒    ▓▓▓▓▓▓          ░▓▓▓▓▓▒    ░▓▓▓▓▓▓░\n" +
            "  ▓▓▓▓▓▓▓░  ▓▓▓▓▓▓▓    ░▓▓▓▓▓▒  ░▓▓▓▓▓▓▓     ▓▓▓▓▓▓ ▒░░▓░▓▒  ░▓▓▓▓▓▒░░▓▓▓▓▓▓▓░░ \n" +
            "    ▒░▓▓▓▓▓▓▓▓▓▓▓▓▒      ▓▓▓▓▓▓▓▓▓▓▓▓▒░      ▓▓▓▓▓▓▓▓▓▓▓▓▓▒  ░▓▓▓▓▓▓▓▓▓▓▓▓▓▓░   \n" +
            "         ░░░░░               ░░░░░                                              \n").yellow())
    // Display the rules
    println("In this game, your goal is to be the person to remove the golden coin from the board.\n")

    println("This game is turn based, so each player will take turns choosing one coin to slide")
    println("to the left as many slots as they like, until the coin hits another coin.")
    println("If you choose a coin on the very left, the coin will be taken off the board.")
    println("The player who removes the gold coin from the board wins\n")
}