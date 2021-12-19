package year2021.day6

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class FishStemTest{

    @Test
    fun createStem(){
        val fishStem = FishStem("input/day6/test1.txt")

        assertTrue(5 == fishStem.stem.size)
        assertTrue(fishStem.stem.containsAll(listOf(
            LightFish(3),
            LightFish(4),
            LightFish(3),
            LightFish(1),
            LightFish(2),
        )))
    }
}