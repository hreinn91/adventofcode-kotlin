package year2021.day4

import java.io.File

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

    fun play(): Int {
        for (number in bingoDraws){
            for(board in bingoBoards){
                if(board.draw(number)){
                    return board.getScore(number)
                }
            }
        }
        throw IllegalStateException("No winner")
    }

    fun playToLose(): Int {
        val boards: MutableList<BingoBoard> = bingoBoards.toMutableList()
        for (number in bingoDraws){
            for(board in findWinners(number, boards)){
                if(boards.size == 1){
                    return boards[0].getScore(number)
                }
                boards.remove(board)
            }
        }
        throw IllegalStateException("No final winner.")
    }

    private fun findWinners(number: Int, boards: MutableList<BingoBoard>): List<BingoBoard> {
        val winners = mutableListOf<BingoBoard>()
        for(board in boards){
            if(board.draw(number)){
                winners.add(board)
            }
        }
        return winners
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

    fun getScore(number: Int): Int {
        return unmarked.sum() * number
    }
}