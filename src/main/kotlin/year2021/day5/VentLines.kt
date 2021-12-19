package year2021.day5

import java.io.File
import kotlin.math.abs

class VentLines(filename: String) {

    private val lines: List<Line>
    private val overlapPoints: MutableMap<Point, Int> = mutableMapOf()
    private val overlapPointsIncludingDiagonals: MutableMap<Point, Int> = mutableMapOf()
    val overLapCount: Int
    val overLapCountIncludingDiagonal: Int
    private var xSize: Int = 0
    private var ySize: Int = 0

    init {
        lines = getRawInput(filename)
            .split('\n')
            .map { coordinatesString -> Line(coordinatesString) }
            .toList()

        overLapCount = calculateOverlap()
        overLapCountIncludingDiagonal = calculateOverlapIncludingDiagonal()
        overlapPoints.forEach { (key, value) ->
            if (key.x > xSize) {
                xSize = key.x
            }
            if (key.y > ySize) {
                ySize = key.y
            }
        }
    }

    private fun getRawInput(filename: String): String {
        val path = "src/main/resources/"
        return File(path + filename).readText()
    }

    private fun calculateOverlap(): Int {
        lines.stream().map { line -> line.getPoints() }
            .flatMap { points -> points.stream() }
            .forEach { addOverlap(it, overlapPoints) }

        var overlapCount = 0
        overlapPoints.forEach { (key, value) ->
            if (value >= 2) {
                overlapCount += 1
            }
        }
        return overlapCount
    }

    private fun calculateOverlapIncludingDiagonal(): Int {
        lines.stream().map { line -> line.getPointsIncludingDiagonal() }
            .flatMap { points -> points.stream() }
            .forEach { addOverlap(it, overlapPointsIncludingDiagonals) }

        var overlapCount = 0
        overlapPointsIncludingDiagonals.forEach { (key, value) ->
            if (value >= 2) {
                overlapCount += 1
            }
        }
        return overlapCount
    }

    fun get(i: Int): Line {
        return lines[i]
    }

    private fun addOverlap(point: Point, overlapMap: MutableMap<Point, Int>) {
        if (overlapMap.containsKey(point)) {
            overlapMap[point] = overlapMap[point]!! + 1
        } else {
            overlapMap[point] = 1
        }
    }

    override fun toString(): String {
        var res = ""
        for (row in 0..ySize) {
            for (col in 0..xSize) {
                res += if (overlapPoints.containsKey(Point(col, row))) {
                    val value: Int = overlapPoints[Point(col, row)]!!
                    "$value"
                } else {
                    "."
                }
            }
            res += "\n"
        }
        return res
    }

    fun toStringWithDiagonals(): String {
        var res = ""
        for (row in 0..ySize) {
            for (col in 0..xSize) {
                res += if (overlapPointsIncludingDiagonals.containsKey(Point(col, row))) {
                    val value: Int = overlapPointsIncludingDiagonals[Point(col, row)]!!
                    "$value"
                } else {
                    "."
                }
            }
            res += "\n"
        }
        return res
    }
}

class Line(coordinateString: String) {
    val p1: Point
    val p2: Point

    init {
        val coordinates = coordinateString.split(" -> ")
            .map { split -> split.split(',') }
            .flatten()
        p1 = Point(coordinates[0].toInt(), coordinates[1].toInt())
        p2 = Point(coordinates[2].toInt(), coordinates[3].toInt())
    }

    fun getPoints(): List<Point> {
        if (p1.y == p2.y) {
            val start: Int = if (p1.x < p2.x) p1.x else p2.x
            val stop: Int = if (p1.x > p2.x) p1.x else p2.x
            return (start..stop).map { x -> Point(x, p1.y) }.toList()
        }

        if (p1.x == p2.x) {
            val start: Int = if (p1.y < p2.y) p1.y else p2.y
            val stop: Int = if (p1.y > p2.y) p1.y else p2.y
            return (start..stop).map { y -> Point(p1.x, y) }.toList()
        }
        return emptyList()
    }

    fun getPointsIncludingDiagonal(): List<Point> {
        if (p1.y == p2.y) {
            val start: Int = if (p1.x < p2.x) p1.x else p2.x
            val stop: Int = if (p1.x > p2.x) p1.x else p2.x
            return (start..stop).map { x -> Point(x, p1.y) }.toList()
        }
        if (p1.x == p2.x) {
            val start: Int = if (p1.y < p2.y) p1.y else p2.y
            val stop: Int = if (p1.y > p2.y) p1.y else p2.y
            return (start..stop).map { y -> Point(p1.x, y) }.toList()
        }
        if (abs(p1.x - p2.x) == abs(p1.y - p2.y)) {
            return (0..abs(p1.x - p2.x))
                .map { Point(if (p1.x < p2.x) {p1.x + it} else {p1.x - it},
                    if (p1.y < p2.y) {p1.y + it} else {p1.y - it}) }
                .toList()
        }


        return emptyList()
    }
}

class Point(val x: Int, val y: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

    override fun toString(): String {
        return "Point(x=$x, y=$y)"
    }
}