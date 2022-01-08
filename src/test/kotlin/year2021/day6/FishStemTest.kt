package year2021.day6

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class FishStemTest {

    @Disabled
    @Test
    fun createStem() {
        val fishStem = FishStem("input/day6/test1.txt")

        assertTrue(5 == fishStem.stem.size)
        assertTrue(
            fishStem.stem.containsAll(
                listOf(
                    LightFish(3),
                    LightFish(4),
                    LightFish(3),
                    LightFish(1),
                    LightFish(2),
                )
            )
        )
    }

    @Disabled
    @Test
    fun simulateStem_test() {
        val fishStem = FishStem("input/day6/test1.txt")

        fishStem.step(79)

        assertEquals(5934, fishStem.stem.size)
    }

    @Disabled
    @Test
    fun simulateStem() {
        val fishStem = FishStem("input/day6/input.txt")

        fishStem.step(79)

        assertEquals(365131, fishStem.stem.size)
    }

    @Disabled
    @Test
    fun simulateStemSmall() {
        val stem = getFishFromFile("input/day6/test1.txt")

        assertEquals(26, fishCount(stem, 18))
    }

    @Disabled
    @Test
    fun simulateStemMedium() {
        val stem = getFishFromFile("input/day6/test1.txt")

        assertEquals(5934, fishCount(stem, 80))
    }

    @Disabled
    @Test
    fun simulateStemBig() {
        val stem = getFishFromFile("input/day6/input.txt")

        assertEquals(365131, fishCount(stem, 80))
    }

    @Disabled
    @Test
    fun simulateStemBigBig() {
        val stem = getFishFromFile("input/day6/test1.txt")
        assertEquals(26984457539, fishCount(stem, 256))
    }

    @Disabled
    @Test
    fun simulateStemBigBigBig() {
        val stem = getFishFromFile("input/day6/input.txt")
        val day6_output = fishCount(stem, 256)
        println("Days 256: $day6_output")
    }
}


fun fishCount(stem: List<LightFish>, days: Int): Long {
    var res = 0L
    val cache :MutableMap<Int, Long> = mutableMapOf()
    for (fish in stem) {
        res += cache.getOrPut(fish.state){fishCountRecursive(days, fish.state)}
    }
    return res
}


fun fishCountRecursive(days: Int, state: Int): Long {
    if (days == 0) {
        return 1
    }
    if (state == 0) {
        return fishCountRecursive(days - 1, 6) + fishCountRecursive(days - 1, 8)
    }

    return fishCountRecursive(days - 1, state - 1)
}