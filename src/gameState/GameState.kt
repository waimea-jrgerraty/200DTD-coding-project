package gameState

import kotlin.random.Random

enum class MoveState {
    WIN, VALID, INVALID
}

class GameState(rows: UByte, coins: UByte) {
    // Clamp the rows to sensible values for gameplay purposes
    val rows = rows.coerceIn(8u, 32u)
    // Coins are stored in a map, where a value may be either Null, false, or true
    // Null indicates no coin at that position
    // false indicates a silver coin
    // true indicates the gold coin
    private var coinData: MutableMap<UByte, Boolean?> = mutableMapOf()

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
            coinData[i] = false
        }
        // Override the gold coin index
        coinData[gold] = true
    }

    fun getCoin(position: Int): Boolean? {
        return coinData[(position - 1).toUByte()]
    }

    // Position is 1 to rows, instead of 0 to rows - 1
    fun pushCoin(position: Int): MoveState {
        val coin = getCoin(position - 1)
        if (coin != null) {
            // Handle win state and coin removal
            if (position == 1) {
                coinData[0u] = null
                return if (coin == true) {
                    MoveState.WIN
                } else {
                    MoveState.VALID
                }
            }
            // Try to push coin to the left
            // If there is a coin to the left we want it to return invalid
            if (getCoin(position - 2) == null) {
                // Push the coin as far to the left as possible
                var i = position - 1
                while (i > 1 && getCoin(i - 1) == null) {
                    i--
                }
                coinData[i.toUByte()] = coin
                coinData[(position - 1).toUByte()] = null
                return MoveState.VALID
            }
        }
        return MoveState.INVALID
    }
}