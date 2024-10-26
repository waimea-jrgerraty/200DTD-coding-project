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


### TEST NAME HERE

TEST DESCRIPTION HERE

#### Test Data / Actions to Use

TEST DATA TO USE DETAILED HERE

#### Expected Outcome

EXPECTED OUTCOME DETAILED HERE


### TEST NAME HERE

TEST DESCRIPTION HERE

#### Test Data / Actions to Use

TEST DATA TO USE DETAILED HERE

#### Expected Outcome

EXPECTED OUTCOME DETAILED HERE


### TEST NAME HERE

TEST DESCRIPTION HERE

#### Test Data / Actions to Use

TEST DATA TO USE DETAILED HERE

#### Expected Outcome

EXPECTED OUTCOME DETAILED HERE


### TEST NAME HERE

TEST DESCRIPTION HERE

#### Test Data / Actions to Use

TEST DATA TO USE DETAILED HERE

#### Expected Outcome

EXPECTED OUTCOME DETAILED HERE


---


## Evidence / Results of Testing

### TEST NAME HERE

ACTUAL RESULTS OF TESTING SHOWN HERE

![](images/placeholder.jpg)

NOTES REGARDING THE RESULTS HERE


### TEST NAME HERE

ACTUAL RESULTS OF TESTING SHOWN HERE

![](images/placeholder.jpg)

NOTES REGARDING THE RESULTS HERE


### TEST NAME HERE

ACTUAL RESULTS OF TESTING SHOWN HERE

![](images/placeholder.jpg)

NOTES REGARDING THE RESULTS HERE


### TEST NAME HERE

ACTUAL RESULTS OF TESTING SHOWN HERE

![](images/placeholder.jpg)

NOTES REGARDING THE RESULTS HERE

