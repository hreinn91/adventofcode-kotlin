package year2021.day6

import java.io.File

class FishStem(filename: String) {
    val stem: MutableList<LightFish> = mutableListOf()
    private var day = 0
    init {
        getRawInput(filename).split(',')
            .stream().forEach{ stem.add(LightFish(it.toInt())) }
    }

    fun step(days: Int){
        (0..days).forEach{ _ ->
            println(this)
            step()
        }
    }

    private fun step(){
        day += 1
        val newFishCount = stem.stream().filter { fish -> fish.step() }.count()
        for(i in 0 until newFishCount){
            stem.add(LightFish(8))
        }
    }

    override fun toString(): String {
        var res = ""
        if(stem.size < 30){
            stem.stream().forEach{ fish -> res+="${fish.state}," }
        }
        return "FishStem day $day count: ${stem.size}  $res"
    }

}

class LightFish(initState: Int){
    var state: Int = initState
    val resetState: Int = 6

    fun step(): Boolean{
        state -= 1
        if(state < 0) {
            state = resetState
            return true
        }
        return false
    }

    override fun toString(): String {
        return "LightFish(state=$state)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LightFish

        if (state != other.state) return false
        if (resetState != other.resetState) return false

        return true
    }

    override fun hashCode(): Int {
        var result = state
        result = 31 * result + resetState
        return result
    }
}

fun getRawInput(filename: String): String {
    val path = "src/main/resources/"
    return File(path + filename).readText()
}

fun getFishFromFile(filename: String): List<LightFish> {
    return getRawInput(filename).split(',')
        .stream().map{ LightFish(it.toInt()) }.toList()
}
