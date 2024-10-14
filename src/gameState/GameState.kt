package gameState

import kotlin.random.Random

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
            randomNumbers.add(Random.nextInt(2, rows.toInt() + 1).toUByte())
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

    
}