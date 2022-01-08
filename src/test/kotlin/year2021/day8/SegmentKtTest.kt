package year2021.day8

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class SegmentKtTest {

    @Test
    fun segmentTest(){
        val segment = Segment("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf")
        assertEquals(segment.inputs.size, 10)
        assertEquals(segment.outputs.size, 4)
        println("Segment $segment")
    }

    @Test
    fun getSegments() {
        val segments = getSegments("testA.txt")
        println("Segments $segments")
    }

    @Test
    fun testA() {
        val segments = getSegments("testA.txt")
        val uniquesCount: Int = countUniquesFromSegments(segments)
        println("uniquesCount $uniquesCount")

        val sum = segments.stream().map { s ->
            println("${s.decodeOutput()}")
            s.decodeOutput()
        }.toList().sum()
        println("Sum $sum")

    }

    @Test
    fun inputA() {
        val segments = getSegments("input.txt")
        val uniquesCount: Int = countUniquesFromSegments(segments)
        println("uniquesCount $uniquesCount")

        val sum = segments.stream().map { s ->
            println("${s.decodeOutput()}")
            s.decodeOutput()
        }.toList().sum()
        println("Sum $sum")
    }

    @Test
    fun decodeOutput() {
        val segment = Segment("edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc")
        assertEquals(9781, segment.decodeOutput())
        println("Done")
    }
}