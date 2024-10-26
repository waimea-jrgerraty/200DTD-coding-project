# Old Gold

### Level 2 Programming Project by James Gerraty

This project is assessed against [AS91896](https://www.nzqa.govt.nz/nqfdocs/ncea-resource/achievements/2019/as91896.pdf)


## Project Description

The project involves the programming of a two-player game.

This is a kotlin implementation of the game "Old Gold" that runs in the terminal.\
It is a two-player game in which you have to be the player to take the golden coin\
off the board. You can move any of the coins on the board as many spaces to the left\
as possible until the coin will hit another coin, or the edge. A coin on the left edge\
of the board can be removed.


## Source Code

The project is written in [Kotlin](https://kotlinlang.org/)

The main source file is [Main.kt](src/Main.kt)


## Documentation

Evidence of testing can be found in [testing.md](testing.md)


## Running the Program

You can either clone this whole repo, open it using [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) and run from source; or you can run the compiled program:

1. Install the [Java runtime (JRE)](https://www.java.com/en/download/) installed to run the program.
2. Go to the [out/artifacts](out/artifacts) folder
3. Locate and download the compiled **JAR file** (e.g. FILENAME.jar)
4. Run the following command:
    ```bash
    java -jar FILENAME.jar
    ```
