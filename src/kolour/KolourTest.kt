package kolour

/***************************************************************************************
 * Console Colours for Kotlin
 * Steve Copley 2022
 * https://github.com/stevecopley/kotlin-kolour
 ***************************************************************************************/

import kotlin.math.ceil

/***************************************************************************************
 * Show tables of standard TTY colours, and also true-colour sample palette
 */
fun colourTest() {
    val colours = mapOf<String, (text: String) -> String>(
        "(none)" to String::normal,
        "grey" to String::grey,
        "black" to String::black,
        "red" to String::red,
        "yellow" to String::yellow,
        "green" to String::green,
        "cyan" to String::cyan,
        "blue" to String::blue,
        "magenta" to String::magenta
    )

    val bgColours = mapOf<String, (text: String) -> String>(
        "(bg none)" to String::bgNone,
        "bgBlack" to String::bgBlack,
        "bgGrey" to String::bgGrey,
        "bgRed" to String::bgRed,
        "bgYellow" to String::bgYellow,
        "bgGreen" to String::bgGreen,
        "bgCyan" to String::bgCyan,
        "bgBlue" to String::bgBlue,
        "bgMagenta" to String::bgMagenta
    )

    // Standard Colour table ---------------------------------------------------------------
    println()
    var title = "STANDARD COLOURS"
    println("┏━ ${title.bold()} ${"━".repeat(colours.count() * 10 - title.length + 8)}┓")
    bgColours.forEach { bgColName, bgColFun ->
        print("┃ ${bgColName.padStart(9)} ")
        colours.forEach { fgColName, fgColFun ->
            print(bgColFun(fgColFun(" ${fgColName.padEnd(7)} ")).bold() + " ")
        }
        println("┃")
    }
    println("┗${"━".repeat(colours.count() * 10 + 11)}┛")

    // RGB true-colour table ---------------------------------------------------------------
    println()
    title = "RGB TRUE COLOURS"
    val cols = colours.count() * 10 + 9
    val rows = 11
    val swapRow = ceil(rows / 2.0)
    println("┏━ ${title.bold()} ${"━".repeat(cols - title.length - 1)}┓")
    //  row starting at 1, and the rows+1 below, avoid the pure black/white rows
    (1..rows).forEach { row ->
        // Work up the value for the first lot of rows, then down the saturation
        val v = if (row <= swapRow) row.toDouble() / swapRow else 1.0
        val s = if (row <= swapRow) 1.0 else 1.0 - ((row - swapRow).toDouble() / (rows + 1 - swapRow))

        print("┃ ")
        (0 until cols).forEach { col ->
            val h = col.toDouble() / cols
            val (r, g, b) = hsvToRgb(h, s, v)
            print("▉".col(r, g, b).bgBlack())
        }
        println(" ┃")
    }
    println("┗${"━".repeat(cols + 2)}┛")
    println()

    // Examples ---------------------------------------------------------------
    val message = "Colourful Words!"

    print("Foreground Colours: ")
    for (i in 0 until message.length) {
        val colIndex = (i % 6) + 3  // Only the colourful ones - not b&W
        print(colours.values.elementAt(colIndex)(message.get(i).toString()).bold())
    }
    println()

    print("Background Colours: ")
    for (i in 0 until message.length) {
        val colIndex = (i % 6) + 3  // Only the colourful ones - not b&W
        print(bgColours.values.elementAt(colIndex)(message.get(i).toString()).black().bold())
    }
    println()

    print(" Greyscale via RGB: ")
    (0..255 step 17).forEach { print("▉".col(it, it, it)) }
    println()

    print("   Colours via HSV: ")
    (0 until 16).forEach { print("▉".col(it.toDouble() / 16, 1.0, 1.0)) }
    println()

    print("     Tints via HSV: ")
    (15 downTo 0).forEach { print("▉".col(1.0, it.toDouble() / 15, 1.0)) }
    println()

    print("     Tones via HSV: ")
    (15 downTo 0).forEach { print("▉".col(1.0, it.toDouble() / 15, it.toDouble() / 15)) }
    println()

}
