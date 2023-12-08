package DayTemplate

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return 1
    }

    fun part2(input: List<String>): Int {
        return 1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day0X_test")
    check(part1(testInput) == 1)
    val testInputPartTwo = readInput("Day0X_test")
    check(part2(testInputPartTwo) == 1)

    val input = readInput("Day0X")
    part1(input).println()
    part2(input).println()
}
