package year2021.day7

import java.io.File
import kotlin.math.abs


fun getInput(filename: String): List<Int> {
    val path = "src/main/resources/"
    return File(path + filename)
        .readText()
        .split(',')
        .stream()
        .map { it.toInt() }
        .toList()
}

fun mean(values: List<Int>): Double {
    return (1.0 * values.sum()) / values.size
}

fun median(values: List<Int>): Double {
    val sortedArray: List<Int> = values.sorted()
    val size: Int = sortedArray.size

    return if (size % 2 == 0) {
        (sortedArray[size / 2].toDouble() + sortedArray[size / 2 - 1].toDouble()) / 2
    } else {
        sortedArray[size / 2].toDouble()
    }
}


fun getFuelCost(positions: List<Int>, target: Int): Int {
    var fuel = 0
    for (pos in positions) {
        fuel += abs(pos - target)
    }
    return fuel
}

fun getImprovedFuelCost(positions: List<Int>, target: Int): Int {
    var fuel = 0
    for (pos in positions) {
        fuel += getImprovedFuelCost(abs(pos - target))
    }
    return fuel
}

fun getImprovedFuelCost(distance: Int): Int {
    var res = 0
    for (i in 1..distance) {
        res += i
    }
    return res
}