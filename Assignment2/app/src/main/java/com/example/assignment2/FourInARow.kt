package com.example.assignment2

import com.example.assignment2.GameConstants
import com.example.assignment2.IGame
import java.util.Scanner

class FourInARow

    : IGame {
    // game board in 2D array initialized to zeros
    private val board = Array(GameConstants.ROWS) { IntArray(GameConstants.COLS) { 0 } }

    /**
     * clear board and set current player
     */
    override fun clearBoard() {
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                board[row][col] = 0
            }
        }
    }

    /**
     * sets a spot on the board to a player
     */
    override fun setMove(player: Int, input: Int) {
        val row: Int = input / 6
        val col: Int = input % 6
        if (board[row][col] == GameConstants.EMPTY) {
                board[row][col] = player
        }
    }

    /**
     * returns the next open space
     */
    override val computerMove: Int
        get() {
            var move = 0
            for (row in 0 until GameConstants.ROWS) {
                for (col in 0 until GameConstants.COLS) {
                    if (board[row][col] == 0) {
                        move = row * 6 + col
                        return move
                    }
                }
            }

            return move
        }

    /**
     * checks for a four in a row and returns the player that won
     */
    override fun checkForWinner(): Int {
        //if 4 in a row, return winner
        for (row in 0 until 3) {
            for (col in 0 until GameConstants.COLS) {
                if (board[row][col] != GameConstants.EMPTY) {
                    if (board[row][col] == board[row + 1][col] && board[row][col] == board[row + 2][col] && board[row][col] == board[row + 3][col]) {
                        return board[row][col]

                    }
                }
            }
        }
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until 3) {
                if (board[row][col] != GameConstants.EMPTY) {
                    if (board[row][col] == board[row][col + 1] && board[row][col] == board[row][col + 2] && board[row][col] == board[row][col + 3]) {
                        return board[row][col]

                    }
                }
            }
        }
        for (row in 0 until 3) {
            for (col in 0 until 3) {
                if (board[row][col] != GameConstants.EMPTY) {
                    if (board[row][col] == board[row + 1][col + 1] && board[row][col] == board[row + 2][col + 2] && board[row][col] == board[row + 3][col + 3]) {
                        return board[row][col]
                    }
                }
            }
        }
        for (row in 0 until 3) {
            for (col in 3 until GameConstants.COLS) {
                if (board[row][col] != GameConstants.EMPTY) {
                    if (board[row][col] == board[row + 1][col - 1] && board[row][col] == board[row + 2][col - 2] && board[row][col] == board[row + 3][col - 3]) {
                        return board[row][col]
                    }
                }
            }
        }

        return -1
    }

    fun checkTie(): Boolean {
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                if (board[row][col] == 0)
                    return false
            }
        }
        return true
    }

    /**
     * Print the game board
     */
    fun printBoard() {
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                printCell(board[row][col]) // print each of the cells
                if (col != GameConstants.COLS - 1) {
                    print("|") // print vertical partition
                }
            }
            println()
            if (row != GameConstants.ROWS - 1) {
                println("----------------------") // print horizontal partition
            }
        }
        println()
    }

    /**
     * helper function that takes a row and column and returns the corresponding int from 0-35
     */
    fun toNumberedMove(row: Int, col: Int): Int {
        return (row * 6 + col)
    }

    /**
     * helper function that takes an int from 0-35 and returns the corresponding row col pair
     */
    fun toCoordPair(space: Int): Pair<Int, Int> {
        val row = space / 6
        val col = space % 6
        return Pair(row, col)
    }

    /**
     * Print a cell with the specified "content"
     * @param content either BLUE, RED or EMPTY
     */
    fun printCell(content: Int) {
        when (content) {
            GameConstants.EMPTY -> print("   ")
            GameConstants.BLUE -> print(" B ")
            GameConstants.RED -> print(" R ")
        }
    }
}


