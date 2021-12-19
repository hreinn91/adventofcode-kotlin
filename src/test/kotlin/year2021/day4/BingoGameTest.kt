package year2021.day4

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class BingoGameTest {

    @Test
    fun getBoardInput() {
        val game = BingoGame("input/day4/test1.txt")
        assertTrue(game.bingoDraws.containsAll(
            listOf(7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1)))
        assertEquals(3, game.bingoBoards.size)
        game.bingoBoards.stream().map { b -> b.boardNumbers.size }
            .forEach{s -> assertEquals(25, s,  "All bingo boars should contain 5x5 = 25 coordinates.")}
        println("Done")
    }

    @Test
    fun draw() {
        val game = BingoGame("input/day4/test1.txt")
        val draws = game.bingoDraws
        val winningBoard = game.bingoBoards[2]
        for(number in draws){
            winningBoard.draw(number)
        }

        println("Done")
    }

    @Test
    fun winningRow() {
        val board = BingoBoard(" 3 15  0  2 22\n" +
                " 9 18 13 17  5\n" +
                "19  8  7 25 23\n" +
                "20 11 10 24  4\n" +
                "14 21 16 12  6")

        val drawn: List<Int> = listOf(20, 11, 10, 24, 4)
        val win = drawn.stream().map { board.draw(it) }.filter { it }.findAny().orElseThrow()
        assertTrue(win)
    }

    @Test
    fun winningColumn() {
        val board = BingoBoard(" 3 15  0  2 22\n" +
                                     " 9 18 13 17  5\n" +
                                     "19  8  7 25 23\n" +
                                     "20 11 10 24  4\n" +
                                     "14 21 16 12  6")

        val drawn: List<Int> = listOf(2, 17, 25, 24,12)
        val win = drawn.stream().map { board.draw(it) }.filter { it }.findAny().orElseThrow()
        assertTrue(win)
    }

    @Test
    fun test1_play(){
        val game = BingoGame("input/day4/test1.txt")
        println(game.play())
    }

    @Test
    fun test2_play(){
        val game = BingoGame("input/day4/test1.txt")
        println(game.playToLose())
    }

    @Test
    fun input1_play(){
        val game = BingoGame("input/day4/input.txt")
        println(game.play())
    }

    @Test
    fun input2_play(){
        val game = BingoGame("input/day4/input.txt")
        println(game.playToLose())
    }
}