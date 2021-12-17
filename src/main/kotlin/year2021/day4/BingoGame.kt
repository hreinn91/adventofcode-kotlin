package year2021.day4

import java.io.File
import java.lang.IndexOutOfBoundsException

class BingoGame(filename: String) {

    val bingoDraws: List<Int>
    val bingoBoards: List<BingoBoard>

    init {
        val rawInput: String = getRawInput(filename)
        val inputSplits = rawInput.split('\n')
        bingoDraws = inputSplits[0].split(',')
            .stream()
            .map { s -> s.toInt() }
            .toList()
        bingoBoards = rawInput.split("\n\n").drop(1).map { s ->
            BingoBoard(s)
        }.toList()
    }

    private fun getRawInput(filename: String): String {
        val path = "src/main/resources/"
        return File(path + filename).readText()
    }

}

class BingoBoard(board: String) {
    val boardNumbers: List<Int>
    private val unmarked: MutableList<Int> = mutableListOf()
    private val drawnCoordinates: MutableMap<Pair<Int, Int>, Boolean> = mutableMapOf()
    private val boardSize = 5

    init {
        boardNumbers = board.split("\n")
            .stream()
            .map { s -> s.split(" ") }
            .flatMap { s -> s.stream() }
            .filter { s -> s.isNotBlank() }
            .map { s -> s.toInt() }
            .toList()
        boardNumbers.stream().forEach { n -> unmarked.add(n) }
    }

    fun draw(number: Int): Boolean {
        if (unmarked.contains(number)) {
            drawnCoordinates[getCoordinates(number)] = true
            unmarked.remove(number)
        }
        return checkWin()
    }

    private fun getCoordinates(number: Int): Pair<Int, Int> {
        for (row in 0 until boardSize) {
            for (column in 0 until boardSize) {
                if (boardNumbers[row * boardSize + column] == number)
                    return Pair(row, column)
            }
        }
        throw IndexOutOfBoundsException("Number not found")
    }

    private fun checkWin(): Boolean {
        if (drawnCoordinates.size >= boardSize) {
            for (i in 0 until boardSize) {
                var colCount = 0
                var rowCount = 0
                for (j in 0 until boardSize) {
                    if (drawnCoordinates.contains(Pair(i, j))) {
                        colCount++
                    }
                    if (drawnCoordinates.contains(Pair(j, i))) {
                        rowCount++
                    }
                }
                if (colCount == 5 || rowCount == 5) {
                    return true
                }
            }
        }
        return false
    }
}