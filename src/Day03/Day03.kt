package Day03

import println
import readInput

fun main() {

    fun Char.isValidSymbol() = this != '.' && !this.isLetter() && !this.isDigit()

    fun part1(input: List<String>): Int {
        var number = ""
        var adjToSymbol = false
        var listOfNumbers = mutableListOf<String>()

        input.forEachIndexed { col, list ->
            list.forEachIndexed { row, str ->
                // check all neighbours
                if (str.isDigit()) {
                    val n = input.getOrNull(col - 1)?.getOrNull(row) ?: '.'
                    val nw = input.getOrNull(col - 1)?.getOrNull(row + 1) ?: '.'
                    val w = list.getOrNull(row + 1) ?: '.'
                    val sw = input.getOrNull(col + 1)?.getOrNull(row + 1) ?: '.'
                    val s = input.getOrNull(col + 1)?.getOrNull(row) ?: '.'
                    val se = input.getOrNull(col + 1)?.getOrNull(row - 1) ?: '.'
                    val e = list.getOrNull(row - 1) ?: '.'
                    val ne = input.getOrNull(col - 1)?.getOrNull(row - 1) ?: '.'

                    if (!adjToSymbol) {
                        adjToSymbol = n.isValidSymbol() || nw.isValidSymbol() || w.isValidSymbol() ||
                                sw.isValidSymbol() || s.isValidSymbol() || se.isValidSymbol() ||
                                e.isValidSymbol() || ne.isValidSymbol()
                    }

                    if (str.isDigit()) {
                        number += str
                    }

                    //End of number check
                    if (!w.isDigit()) {
                        if (adjToSymbol) {
                            listOfNumbers.add(number)
                        }
                        number = ""
                        adjToSymbol = false
                    }
                }
            }
        }
        return listOfNumbers.sumOf { it.toInt() }
    }

    fun part2(input: List<String>): Int {
        var number = ""
        val symbolCoords = mutableListOf<Pair<Int, Int>>()
        val listOfNumbers = mutableListOf<Pair<String, Pair<Int, Int>>>()

        input.forEachIndexed { col, list ->
            list.forEachIndexed { row, str ->
                // check all neighbours
                if (str.isDigit()) {
                    val n = input.getOrNull(col - 1)?.getOrNull(row) ?: '.'
                    val nw = input.getOrNull(col - 1)?.getOrNull(row + 1) ?: '.'
                    val w = list.getOrNull(row + 1) ?: '.'
                    val sw = input.getOrNull(col + 1)?.getOrNull(row + 1) ?: '.'
                    val s = input.getOrNull(col + 1)?.getOrNull(row) ?: '.'
                    val se = input.getOrNull(col + 1)?.getOrNull(row - 1) ?: '.'
                    val e = list.getOrNull(row - 1) ?: '.'
                    val ne = input.getOrNull(col - 1)?.getOrNull(row - 1) ?: '.'

                    when {
                        n == '*' -> {
                            symbolCoords.add(Pair(col - 1, row))
                        }

                        nw == '*' -> {
                            symbolCoords.add(Pair(col - 1, row + 1))
                        }

                        w == '*' -> {
                            symbolCoords.add(Pair(col, row + 1))
                        }

                        sw == '*' -> {
                            symbolCoords.add(Pair(col + 1, row + 1))
                        }

                        s == '*' -> {
                            symbolCoords.add(Pair(col + 1, row))
                        }

                        se == '*' -> {
                            symbolCoords.add(Pair(col + 1, row - 1))
                        }

                        e == '*' -> {
                            symbolCoords.add(Pair(col, row - 1))
                        }

                        ne == '*' -> {
                            symbolCoords.add(Pair(col - 1, row - 1))
                        }

                    }

                    if (str.isDigit()) {
                        number += str
                    }

                    //End of number check
                    if (!w.isDigit()) {
                        symbolCoords.toSet().forEach {
                            listOfNumbers.add(Pair(number, it))
                        }

                        number = ""
                        symbolCoords.clear()
                    }
                }
            }
        }
        return listOfNumbers
            .groupBy { it.second }
            .filter { it.value.size == 2 }
            .map { it.value
                .map { it.first } }
            .sumOf { it[0].toInt()*it[1].toInt() }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 4361)
    val testInputPartTwo = readInput("Day03_test")
    check(part2(testInputPartTwo) == 467835)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
