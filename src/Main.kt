/**
 * ------------------------------------------------------------------------
 * Old Gold
 * Level 2 programming project
 *
 * by James Gerraty
 *
 * BRIEF PROJECT DESCRIPTION HERE
 * BRIEF PROJECT DESCRIPTION HERE
 * BRIEF PROJECT DESCRIPTION HERE
 * ------------------------------------------------------------------------
 */

import kolour.*
import gameState.GameState as GameState

fun printState(game: GameState) {
    // test print coin positions
    repeat(game.rows.toInt()) { i ->
        print("${i + 1}:${game.getCoin(i)} ")
    }
}


/**
 * Program entry point
 */
fun main() {
    println("Hello World!")
//    colourTest()
    val game = GameState(16u, 4u)
    // test print coin positions
    printState(game)
    println("")
    print("Chose a position to push: ")
    val pos = readln().toIntOrNull()
    if (pos != null) {
        val moveState = game.pushCoin(pos)
        println(moveState)
        printState(game)
    }
}

