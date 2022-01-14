package year2021.day9

import java.io.File
import kotlin.random.Random


class Basins(private val heightmap: List<List<Int>>) {
    private val parsedValues: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()
    private val basins: MutableList<List<Int>> = mutableListOf()
    private val rowSize: Int = heightmap.size
    private val columnsSize: Int = heightmap[0].size

    init {
        findAllBasins()
    }

    fun calcScore(): Int{
        val basinSizes: List<Int> = basins.map { basin -> basin.size }.sortedDescending()
        return basinSizes[0] * basinSizes[1] * basinSizes[2]
    }

    private fun findAllBasins() {
        while (parsedValues.size != rowSize * columnsSize) {
            val basin = mutableListOf<Int>()
            traverseBasin(Random.nextInt(rowSize), Random.nextInt(columnsSize), basin)
            if (basin.size > 0) {
                basins.add(basin)
            }
        }
    }

    private fun hasParsed(row: Int, column: Int): Boolean {
        return parsedValues.containsKey(Pair(row, column))
    }

    private fun isBoarder(row: Int, column: Int): Boolean {
        return row < 0 || row >= rowSize ||
                column < 0 || column >= columnsSize

    }

    private fun traverseBasin(row: Int, column: Int, basin: MutableList<Int>) {
        if (hasParsed(row, column) || isBoarder(row, column)) {
            return
        }
        if (heightmap[row][column] == 9) {
            parsedValues[Pair(row, column)] = 1
            return
        }
        parsedValues[Pair(row, column)] = 1
        basin.add(heightmap[row][column])

        traverseBasin(row - 1, column, basin)
        traverseBasin(row + 1, column, basin)
        traverseBasin(row, column - 1, basin)
        traverseBasin(row, column + 1, basin)
    }

}

fun getMinValues(heightmap: List<List<Int>>): List<Int> {
    val rowsSize = heightmap.size
    val colSize = heightmap[0].size
    val minValues = mutableListOf<Int>()

    for (row in 0 until rowsSize) {
        for (column in 0 until colSize) {
            if (hasMinValue(row, column, heightmap)) {
                minValues.add(heightmap[row][column])
            }
        }
    }
    return minValues
}

fun hasMinValue(row: Int, column: Int, heightmap: List<List<Int>>): Boolean {
    val rowsSize = heightmap.size
    val columnsSize = heightmap[0].size
    val target = heightmap[row][column]
    var hasMinValue = true
    if (row - 1 >= 0) {
        hasMinValue = hasMinValue && target < heightmap[row - 1][column]
    }
    if (row + 1 <= rowsSize - 1) {
        hasMinValue = hasMinValue && target < heightmap[row + 1][column]
    }
    if (column - 1 >= 0) {
        hasMinValue = hasMinValue && target < heightmap[row][column - 1]
    }
    if (column + 1 <= columnsSize - 1) {
        hasMinValue = hasMinValue && target < heightmap[row][column + 1]
    }
    return hasMinValue
}

fun calcScore(heightmapValues: List<Int>): Int {
    return heightmapValues.stream().map { it + 1 }.toList().sum()
}

fun getHeightmapFromInput(filePath: String): List<List<Int>> {
    val path = "src/main/resources/"
    return File(path + filePath)
        .readText()
        .split('\n')
        .stream()
        .map { row -> stringOfIntsToList(row) }
        .toList()
}

fun stringOfIntsToList(stringOfInts: String): List<Int> {
    return stringOfInts.map { numberString -> numberString.toString().toInt() }
        .toList()
}

fun main() {
    val heightmap = getHeightmapFromInput("input/day9/input.txt")
    val minValues = getMinValues(heightmap)
    val score = calcScore(minValues)
    println("Score: $score")
}