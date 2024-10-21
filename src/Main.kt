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
    var lastState = gameState.MoveState.VALID
    while (lastState != gameState.MoveState.WIN) {
        printState(game)
        println("")
        print("Chose a position to push: ")
        val pos = readln().toIntOrNull()
        if (pos != null) {
            lastState = game.pushCoin(pos)
            println(lastState)
//            printState(game)
        }
    }
}

