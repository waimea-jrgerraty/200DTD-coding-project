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

import display.displayState
import display.splash
import gameState.GameState

/**
 * Program entry point
 */
fun main() {
    splash() // Display a big splash screen
    val game = GameState(16u, 4u)
    // test print coin positions
    var lastState = gameState.MoveState.VALID
    while (lastState != gameState.MoveState.WIN) {
        displayState(game)
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

