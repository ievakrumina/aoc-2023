package Day04

import println
import readInput
import kotlin.math.pow

fun main() {

    fun countOfWinsPerCard(input: List<String>) = input.map { str ->
        val game = str.dropWhile { it != ':' }.drop(2).split(" | ")
        val win = game[0].split(" ").filter { it != "" }
        val playerNumbers = game[1].split(" ").filter { it != "" }
        playerNumbers.map {
            it in win
        }.filter { it }.size
    }

    fun part1(input: List<String>): Int {
        return countOfWinsPerCard(input)
            .filter { it != 0 }
            .sumOf { 2.toDouble().pow((it - 1).toDouble()) }.toInt()
    }

    fun part2(input: List<String>): Int {
        val baseCardCount = countOfWinsPerCard(input)

        val finalCardCount = MutableList(size = input.size) { 1 }
        val gameVector = List(input.size) {List(input.size) {0} }.mapIndexed { idx, ints ->
            ints.mapIndexed { index, _ ->
                if (index in (idx + 1..baseCardCount[idx]+idx)) {
                    1
                } else {
                    0
                }
            }
        }

        input.indices.forEach {idx ->
            input.indices.forEach { index ->
                finalCardCount[index] += finalCardCount[idx] * gameVector[idx][index]
            }
        }
        return finalCardCount.sum()
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)
    val testInputPartTwo = readInput("Day04_test")
    check(part2(testInputPartTwo) == 30)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
