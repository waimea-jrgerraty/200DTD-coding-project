/**
 * ------------------------------------------------------------------------
 * Old Gold
 * Level 2 programming project
 *
 * by James Gerraty
 *
 * This is an implementation of the game 'Old Gold' running in the terminal, built using kotlin.
 * Feature robust error handling, simple and easy to follow graphics, and a repeatable gameplay loop.
 * BRIEF PROJECT DESCRIPTION HERE
 * ------------------------------------------------------------------------
 */

import display.displayState
import display.splash
import gameState.GameState
import gameState.MoveState
import kolour.*

/**
 * Program entry point
 */
fun main() {
    splash() // Display a big splash screen and rules
    // Get both users names
    print("Player 1's name: ")
    val p1Name = readln()
    print("Player 2's name: ")
    val p2Name = readln()
    println()

    /**
     * Main game loop
     */
    var round = 1
    var p1Score = 0
    var p2Score = 0
    while (true) {
        println("Round $round".blue().bold())
        if (round != 1) {
            println("$p1Name has $p1Score wins.")
            println("$p2Name has $p2Score wins.")
        }
        // Get number of slots
        print("Number of slots to use for this round [8 to 32]: ")
        val slotsToUse = readln().toUByteOrNull() ?: 16u // If input error, default to 16

        // Get number of coins
        print("Number of coins to use for this round [2 to ${slotsToUse.coerceIn(8u, 32u)}]: ")
        val coinsToUse = readln().toUByteOrNull() ?: 4u // If input error, default to 4
        println()

        // Begin game
        val game = GameState(slotsToUse, coinsToUse)

        var turn = true // true=player1 false=player2
        var lastState = MoveState.VALID
        while (lastState != MoveState.WIN) {
            // Get current player
            val currPlr = if (turn) { p1Name } else p2Name
            println("${toPossessive(currPlr)} turn:".green().bold())

            // Display the current game state
            displayState(game)

            // Get the user's input for this turn
            print("\nChose a slot to slide: ")
            val pos = readln().toIntOrNull()

            // Attempt their turn
            lastState = if (pos != null) {
                game.pushCoin(pos)
            } else {
                MoveState.INVALID
            }
            if (lastState == MoveState.INVALID) {
                println("$lastState".red())
            }

            // If the state was a win, play a cool animation and increment score

            println()

            // Flip turn when valid move is made
            // If invalid move is made, the user will be told and their turn will restart
            if (lastState == MoveState.VALID) {
                turn = !turn
            }
        }

        // Display the state one last time
        displayState(game)
        // Announce the winner and add score
        val currPlr = if (turn) { p1Name } else p2Name
        repeat(10) { i ->
            // Flash between grey and yellow
            if (i % 2 == 0) {
                print("\r $currPlr has won!".grey().bold())
            } else {
                print("\r $currPlr has won!".yellow().bold())
            }
            // Wait 0.25 seconds
            Thread.sleep(250)
        }
        if (turn) {
            p1Score++
        } else {
            p2Score++
        }

        round++
        // Ask users if they want to play again
        // Assume inputs that are not y are no
        print("\nWould you like to play another round? [Y/N]: ")
        if (readln().lowercase() != "y") {
            break
        }

        println()
    }
}

// Converts a player's name to possessive form factoring in ending with an s
fun toPossessive(name: String): String {
    return if (name.endsWith("s", ignoreCase = true)) {
        // If the name ends with 's', just add an apostrophe
        "${name}'"
    } else {
        // Otherwise, add 's
        "${name}'s"
    }
}