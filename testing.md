# Test Plan and Evidence / Results of Testing

## Game Description

The project involves the programming of a two-player game.

In this game, the goal is to be the player to take the golden coin off the board.  
You can move any of the coins on the board as many spaces to the left as possible  
until the coin will hit another coin, or the edge. A coin on the left edge of the  
board can be removed.


### Game Features and Rules

The game has the following features and/or rules:

- Both players may choose usernames and each players wins will be tracked.
- In each round, a variable number of slots and coins may be chosen.
- In a turn, a player may only select coins that can be removed or moved 1 or more slots to the left.
- A coin can be moved to the left for as many slots as the user wants, until it would be blocked by another  
coin or reaches the left end of the board.

---

## Test Plan

The following game features / functionality and player actions will need to be tested:

- Invalid numbers of slots or coins being chosen (negative slots, more coins than slots)
- Player choosing a slot without a coin
- Player choosing a slot with a coin, when there is a coin in the slot to the left
- Player moving a coin out of range of possible moves (less than 1 or above max distance)
- Users inputting non-numeric data (except for username)

The following tests will be run against the completed game. The tests should result in the expected outcomes shown.


### Invalid slot numbers

Attempting to start a round with a number of rows or coins outside of the valid range should
be accounted for, and the rows and coins should automatically be adjusted to fit within the range.

#### Test Data / Actions to Use

- Start a round
- Try setting rows above or below the valid range
- try settings coins above or below the valid range

#### Expected Outcome

If 4 was input for rows, it should automatically clamp to the lower bound of 8. If 40 is chosen for rows it should
clamp to the upper bound of 32.
If coins is less than 2 it should clamp to the lower bound of 2, and if you input more coins than there are rows,
it should clamp to the number of rows, so you would have a full grid, but no overflow that could cause a gold coin
to not generate.


### Selecting slot with no coin

Players should not be able to select a slot where there is no coin, as this would cause an error, or allow for moving empty slots as if they were coins, which is invalid.

#### Test Data / Actions to Use

- Start a round that has at least one empty slot
- In a turn, try to select the empty slot to move it

#### Expected Outcome

Invalid move should be printed and the player's turn should restart.


### Choosing a blocked coin

A player should not be able to select a slot if there is a slot with a coin to the left of that slot, as there would be no valid moves.

#### Test Data / Actions to Use

- Start a round with many coins (as many coins as slots to guarantee)
- Select a coin that is not on the leftmost edge and has another coin in the slot to the left of it

#### Expected Outcome

Invalid move should be printed and the player's turn should restart, as there are no valid moves with that coin.


### Moving coin further than the maximum possible move

Coins should be blocked and not be able to move past other coins, as this would defeat the challenge of the game, or possibly break it if it moved out of the range of the list.

#### Test Data / Actions to Use

- Start a round with 16 slots and 2 coins
- Repeat until you have a round where the very first slot is not filled, and there is a gap between the two coins
- Select the rightmost coin, and try to move it to the slot past the leftmost coin
- Try again but move the coin a large number (> 32) of slots.

#### Expected Outcome

Invalid move should be printed and the turn should restart, as the move is outside the range of possibilities. There should be no errors with list bounds if the coin gets moved far off the board.


### Handling non numeric inputs

The player is expected to insert numeric data in most cases, and it is also expected to be an unsigned integer. The program needs to handle cases where inputs are non numeric - null or letters. The program also needs to handle signed integers being used.

#### Test Data / Actions to Use

For each of the numeric input fields - slots per round, coins per round, slot to push, and distance to push:
- Test just pressing enter without writing anything (null)
- Test inputting random letters
- Test inputting letters and numbers
- Test inputting a negative number

#### Expected Outcome

For slots per round and coins per round, if a non numeric value is inserted it should default to the default amount.
For slot to push and distance to push, if a non numeric value is inserted it should result in an invalid move and the turn should be repeated.

### Test edge cases

Edge cases for the numeric inputs must be tested. The edge cases that must be tested are:
- Number of slots: 8 and 32
- Number of coins: 2 and max number of slots(32)
- Slot to push: Pushing left and right most coin
- Distance to push: Pushing one slot and max slots (31 on 32 slot game with 2 coins with the left coin already pushed off)

#### Test Data / Actions to Use

Number of slots:
- Start a round with 8 slots and 4 coins
- Start a round with 32 slots and 4 coins
  
Number of coins:
- Start a round with 16 slots and 2 coins
- Start a round with 32 slots and 32 coins
  
Slot to push:
- Push the leftmost coin with 32 slots
- Push the rightmost coin with 32 slots
  
Distance to push:
- Start a round with 32 slots and 2 coins
- Repeat until the gold coin is in the rightmost slot
- Push the silver coin off the board so it is only the gold coin in the rightmost position
- Push the gold coin 31 slots

#### Expected Outcome

These edgecases should work and not cause weird issues with the game. The amount of slots and coins should be what entered. The slot to push should line up with what you entered at the edge cases, and the gold coin should be able to move the 31 slots.

---


## Evidence / Results of Testing

### Invalid slot numbers

Selecting less than 8 slots clamps to the minimum of 8 slots.
Selecting more than 32 slots clamps to the maximum of 32 slots.

![](images/slots-bounds-lower-test.png)

![](images/slots-bounds-upper-test.png)
![](images/slots-bounds-upper-test2.png)

As I thought it unlikely you would input an invalid number of slots/coins, I decided to simply clamp the values instead of repeating the question until a valid slot was chosen. It was easier for me to just clamp the value.


### Selecting slot with no coin

Selecting an empty slot results in an invalid move.

![](images/push-null-coin-test.png)

### Choosing a blocked coin

Selecting a coin that is not in slot 1 and has a coin in the slot to the left of it results in an invalid move.

![](images/blocked-coin-test.png)


### Moving coin further than the maximum possible move

Trying to move the coin to the slot past the coin in slot 2 resulted in an invalid move.

![](images/coin-collision-test.png)

It also did not error when the amount of slots to move would have placed the pointer out of the bounds of the list, as this case is handled now.

![](images/coin-move-out-of-list-test.png)

When I first tested this, I discovered an error when moving the coins a very large number of slots, as this would search outside the bounds of the list.


### Handling non numeric inputs

For the number of slots and number of coins inputs, invalid values default to 16 slots and 4 coins.
(Not shown very well, but this applies to slots and coins seperatly, so if an invalid number was entered for slots, you can still select between 2 and 16 coins)

![](images/null-no-test.png)
![](images/-ve-no-test.png)
![](images/string-no-test.png)
![](images/alphanumeric-no-test.png)

All invalid inputs for the slot to push result in invalid moves.

![](images/null-slot-test.png)
![](images/negative-slot-test.png)
![](images/letters-slot-test.png)
![](images/alphanumeric-slot-test.png)

All invalid inputs for the distance to push coin result in invalid moves.

![](images/null-dist-test.png)
![](images/negative-dist-test.png)
![](images/letters-dist-test.png)
![](images/alphanumeric-dist-test.png)


### Test edge cases

Number of slots worked as intended at the edge cases.
(Note that they get truncated at large numbers of slots but you can scroll horizontally)

![](images/edgecase-slots-lower.png)
![](images/edgecase-slots-upper.png)

Number of coins worked as intended at the edge cases.

![](images/edgecase-coins-lower.png)
![](images/edgecase-coins-upper.png)

Pushing the left and right slots works as intended.

![](images/edgecase-push-left.png)
![](images/edgecase-push-right.png)

Pushing with a max dist of 1 is handled differently, and the user is not prompted for the distance they want to push the coin, as there is only one possible value. This is intended.
Pushing the maximum distance of 31 works correctly as well.

![](images/edgecase-dist-min.png)
![](images/edgecase-dist-max.png)
