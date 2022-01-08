package year2021.day8

import java.io.File


//  Digits Positions
//   0000
//  1    2
//  1    2
//   3333
//  4    5
//  4    5
//   6666

class Segment(raw: String) {
    val inputs: MutableList<String>
    val outputs: List<String>
    private val digitPositions: MutableMap<Int, String> = mutableMapOf()
    private val decodes: MutableMap<String, Int> = mutableMapOf()

    init {
        val splits = raw.split(" | ")
        inputs = splits[0].split(' ').stream().map { sort(it) }.toList().toMutableList()
        outputs = splits[1].split(' ').stream().map { sort(it) }.toList()
    }

    override fun toString(): String {
        return "Segments(inputs=$inputs, outputs=$outputs)"
    }

    private fun getDecodeString(num: Int): String {
        for (key in decodes.keys) {
            if (decodes[key] == num) {
                return key
            }
        }
        throw RuntimeException("Not found")
    }

    fun decodeOutput(): Int {
        addUniques()
        digitPositions[0] = remove(digitPositions[0]!!, digitPositions[2]!!)

        val fiveIdentifier: String = remove(getDecodeString(4), getDecodeString(1))
        var twoIdentifier: String = remove(getDecodeString(8), getDecodeString(4))
        twoIdentifier = remove(twoIdentifier, digitPositions[0]!!)
        for (input in inputs) {
            if (input.length == 5) {
                if (input.contains(fiveIdentifier[0]) && input.contains(fiveIdentifier[1])) {
                    decodes[input] = 5
                } else if (input.contains(twoIdentifier[0]) && input.contains(twoIdentifier[1])) {
                    decodes[input] = 2
                } else {
                    decodes[input] = 3
                }
            }
        }
        decodes.keys.stream().forEach { inputs.remove(it) }

        for (input in inputs) {
            if (input.length == 6) {
                if (containsDigit(input, getDecodeString(4))) {
                    decodes[input] = 9
                } else if (!containsDigit(input, getDecodeString(1))) {
                    decodes[input] = 6
                } else {
                    decodes[input] = 0
                }
            }
        }
        decodes.keys.stream().forEach { inputs.remove(it) }

        return outputs.stream()
            .map { decodes[it]!! }
            .map { it.toString() }
            .toList()
            .joinToString().replace(",", "")
            .replace(" ", "")
            .toInt()
    }

    private fun containsDigit(target: String, digit: String): Boolean {
        for (char in digit.toCharArray()) {
            if (!target.contains(char)) {
                return false
            }
        }
        return true
    }

    private fun addUniques() {
        for (input in inputs) {
            if (input.length == 2) {
                decodes[input] = 1
                digitPositions[2] = input
                digitPositions[5] = input
            }
            if (input.length == 3) {
                decodes[input] = 7
                digitPositions[0] = input
            }
            if (input.length == 4) {
                decodes[input] = 4
            }
            if (input.length == 7) {
                decodes[input] = 8
            }
        }
        decodes.keys.stream().forEach { inputs.remove(it) }
    }

    private fun remove(target: String, remove: String): String {
        var res = target
        remove.toCharArray().forEach { res = res.replace(it.toString(), "") }
        return res
    }
}

fun getSegments(filename: String): List<Segment> {
    val path = "src/main/resources/input/day8/"
    return File(path + filename)
        .readText()
        .split('\n')
        .stream()
        .map { Segment(it) }
        .toList()
}

fun countUniquesFromSegments(segments: List<Segment>): Int {
    var count = 0
    segments.stream()
        .map { s -> s.outputs }
        .forEach { outputs ->
            count += countUniques(outputs)
        }

    return count
}

fun countUniques(ouputs: List<String>): Int {
    var count = 0
//    1 4 7 8
    val matches = listOf(2, 4, 3, 7)
    for (o in ouputs) {
        if (matches.contains(o.length)) {
            count += 1
        }
    }
    return count
}


private fun sort(input: String): String {
    return input.toCharArray().sorted().joinToString("")
}

