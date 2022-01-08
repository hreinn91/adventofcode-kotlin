package year2021.day6

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class FishStemBigTest {

    @Disabled
    @Test
    fun simulateStemBigBigBig() {
        val stem = getFishFromFile("input/day6/input.txt")
        val days = 256
        val day6_output = fishCount(stem, days)
        println("Days $days: $day6_output")
    }
}