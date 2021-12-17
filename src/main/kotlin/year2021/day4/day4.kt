package year2021.day4

import java.io.File
import java.nio.file.Paths


fun readInput(filename: String){

    val readText = {}.javaClass.getResource("day4/test1.txt").readText()
    File(filename).forEachLine { line -> println(line) }
}

fun helloWorld(){

}


fun main(){

    readInput("")
}