package year2021.day5

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class VentLinesTest {

    @Test
    fun readInput() {
        val lines = VentLines("input/day5/test1.txt")

        assertEquals(0, lines.get(0).p1.x)
        assertEquals(9, lines.get(0).p1.y)
        assertEquals(5, lines.get(0).p2.x)
        assertEquals(9, lines.get(0).p2.y)

        assertEquals(5, lines.get(9).p1.x)
        assertEquals(5, lines.get(9).p1.y)
        assertEquals(8, lines.get(9).p2.x)
        assertEquals(2, lines.get(9).p2.y)
        println("Done.")
    }

    @Test
    fun getPointsFromLine() {
        val verticalLine = Line("7,0 -> 7,4").getPoints()
        assertEquals(5 , verticalLine.size )
        assertTrue(verticalLine.containsAll(listOf(
            Point(7, 0),
            Point(7, 1),
            Point(7, 2),
            Point(7, 3),
            Point(7, 4),
        )))

        val horizontalLine = Line("1,4 -> 3,4").getPoints()
        assertEquals(3 , horizontalLine.size )
        assertTrue(horizontalLine.containsAll(listOf(
            Point(1, 4),
            Point(2, 4),
            Point(3, 4),
        )))

        val diagonalLine = Line("0,2 -> 4,8").getPoints()
        assertTrue(diagonalLine.isEmpty() )

        val pointLine = Line("0,2 -> 0,2").getPoints()
        assertTrue(pointLine.size == 1 )
        println("Done.")
    }

    @Test
    fun calculateOverlapTest1() {
        val ventLines = VentLines("input/day5/test1.txt")

        assertEquals(5, ventLines.overLapCount)

        println(ventLines.toString())
        println("test1 overlap ${ventLines.overLapCount}")
    }

    @Test
    fun calculateOverlapInput1() {
        val ventLines = VentLines("input/day5/input.txt")
        println("test1 overlap ${ventLines.overLapCount}")
    }

    @Test
    fun getPointsIncludingDiagonal() {
        val diagonalLine = Line("1,1 -> 3,3").getPointsIncludingDiagonal()
        assertTrue(diagonalLine.containsAll(listOf(
            Point(1,1),
            Point(2,2),
            Point(3,3),
        )) )
    }

    @Test
    fun getPointsIncludingReverseDiagonal() {
        val diagonalLine = Line("9,7 -> 7,9").getPointsIncludingDiagonal()
        assertTrue(diagonalLine.containsAll(listOf(
            Point(7,9),
            Point(8,8),
            Point(9,7),
        )) )

        val lineDiagonal = Line("7,9 -> 9,7").getPointsIncludingDiagonal()
        assertTrue(lineDiagonal.containsAll(listOf(
            Point(9, 7),
            Point(8, 8),
            Point(7, 9),
        )) )
    }


    @Test
    fun calculateOverlap_IncludingDiagonal_Test2() {
        val ventLines = VentLines("input/day5/test1.txt")

        assertEquals(12, ventLines.overLapCountIncludingDiagonal)

        println(ventLines.toStringWithDiagonals())
        println("test2 overlap ${ventLines.overLapCountIncludingDiagonal}")
    }


    @Test
    fun calculateOverlap_IncludingDiagonal_Input2() {
        val ventLines = VentLines("input/day5/input.txt")
        println("input2 overlap ${ventLines.overLapCountIncludingDiagonal}")
    }
}