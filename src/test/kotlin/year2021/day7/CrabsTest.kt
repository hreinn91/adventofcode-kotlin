package year2021.day7

import org.junit.jupiter.api.Test

internal class CrabsTest {

    @Test
    fun getMedianTest() {
        val input: List<Int> = getInput("input/day7/input")

        val median = median(input).toInt()
        val fuelCost = getFuelCost(input, median)
        println("Median $median Fuel cost $fuelCost")
    }

    @Test
    fun getMeanTest() {
        val input: List<Int> = getInput("input/day7/input")

        val mean = mean(input).toInt()
        val fuelCost = getImprovedFuelCost(input, mean)
        println("Mean $mean Improved fuel cost $fuelCost")
    }
}