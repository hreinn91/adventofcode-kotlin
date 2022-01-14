package year2021.day9

import org.junit.jupiter.api.Test

internal class SmokeBasinKtTest {

    @Test
    fun getBasinsTest() {
        val heightmap = getHeightmapFromInput("input/day9/test.txt")
        val basins = Basins(heightmap)
        println("${basins.calcScore()}")
    }

    @Test
    fun getBasins() {
        val heightmap = getHeightmapFromInput("input/day9/input.txt")
        val basins = Basins(heightmap)
        println("${basins.calcScore()}")
    }
}