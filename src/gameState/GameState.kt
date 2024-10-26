package gameState

import kotlin.random.Random

enum class MoveState {
    WIN, VALID, INVALID
}

class GameState(rows: UByte, coins: UByte) {
    // Clamp the rows to sensible values for gameplay purposes
    val rows = rows.coerceIn(8u, 32u)
    // Coins are stored in a list, where a value may be either null, false, or true
    // Null indicates no coin at that position
    // false indicates a silver coin
    // true indicates the gold coin
    private var coinData: MutableList<Boolean?> = MutableList(rows.toInt()) {null}

    init {
        // Chose an appropriate number of random values to use as indices for coins
        val clampedCoins = coins.coerceIn(2u, rows)
        val randomNumbers = mutableSetOf<UByte>()

        while (randomNumbers.size.toUByte() < clampedCoins) {
            randomNumbers.add(Random.nextInt(0, rows.toInt()).toUByte())
        }

        // Pick one of these random values to use as the gold coin
        val gold = randomNumbers.random()

        // Assign each random index as a silver coin
        for (i in randomNumbers) {
            coinData[i.toInt()] = false
        }
        // Override the gold coin index
        coinData[gold.toInt()] = true
    }

    fun getCoin(position: Int): Boolean? {
        return coinData[position - 1]
    }

    // Position is 1 to rows, instead of 0 to rows - 1
    fun pushCoin(position: Int): MoveState {
        val coin = getCoin(position)
        if (coin != null) {
            // Handle win state and coin removal
            if (position == 1) {
                coinData[0] = null
                return if (coin == true) {
                    MoveState.WIN
                } else {
                    MoveState.VALID
                }
            }
            // Try to push coin to the left
            // If there is a coin to the left we want it to return invalid
            if (getCoin(position - 1) == null) {
                // See how far we can push the coin

                var i = position - 1 // Convert to 0 based index
                // Iterate until we reach the wall or there is a coin to the left
                while (i > 0 && getCoin(i) == null) {
                    i--
                }

                // Ask the user how far they want to push the coin
                val maxDist = (position - 1) - i
                print("How far do you wish to push the coin? [1] to [$maxDist]: ")
                val slots = readln().toIntOrNull() ?: 0 // If invalid, convert to a 0
                // Handle slots not being in the valid range
                if (slots !in 1..maxDist) {
                    return MoveState.INVALID
                }
                // Get the slot position in the coins array
                val slot = position - 1 - slots

                // Move the coin by overwriting the new index with the current value
                // And setting the old index to null
                coinData[slot] = coin
                coinData[position - 1] = null
                return MoveState.VALID
            }
        }
        return MoveState.INVALID
    }
}