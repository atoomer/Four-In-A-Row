/**
 * Main plays a game on Four in a Row
 * @author Aaron Toomer
 * @date 2/2/24
 */

import java.util.Scanner

val FIR_board = FourInARow()

/** The entry main method (the program starts here)  */
fun main() {
    var currentState: Int = GameConstants.PLAYING
    val input = Scanner(System.`in`)
    var userInput = ""
    FIR_board.printBoard()

    do {
        var userMoved = false
        var compMoved = false

        while (!userMoved) {
            print("Player pick a spot: ")
            userInput = input.next()
            println()

            if (userInput == "q") {
                println("Quitting Game.")
                break
            } else if (userInput == "c") {
                println("Clearing Board\n")
                FIR_board.clearBoard()
                break
            } else {
                if (userInput.toIntOrNull() in 0 .. 35) {
                    FIR_board.setMove(GameConstants.BLUE, userInput.toInt())
                    FIR_board.printBoard()
                    userMoved = true
                    if (FIR_board.checkForWinner() == GameConstants.BLUE_WON) {
                        currentState = GameConstants.BLUE_WON
                    }
                } else {
                    println("input num 0 to 35")
                }
            }
        }


        if (userMoved && currentState == GameConstants.PLAYING) {
            println("Computer turn.\n")
            Thread.sleep(1200)
            FIR_board.setMove(GameConstants.RED, FIR_board.computerMove)
            FIR_board.printBoard()
            if (FIR_board.checkForWinner() == GameConstants.RED_WON) {
                currentState = GameConstants.RED_WON
            }
        }

        if(FIR_board.checkTie())
            currentState = GameConstants.TIE

        when (currentState){
            GameConstants.BLUE_WON -> println("Player Wins!")
            GameConstants.RED_WON -> println("Computer Wins!")
            GameConstants.TIE -> println("It's a Tie!")
        }


    } while (currentState == GameConstants.PLAYING && userInput != "q")
// repeat if not game-over
}
 