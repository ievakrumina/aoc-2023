fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf {
            val first = it.first { it.isDigit() }
            val last = it.last { it.isDigit() }
            "$first$last".toInt()
        }
    }

    val mapOfRegex = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    fun part2(input: List<String>): Int {
        val newInputTwo = input.map {
            val res = it.findAnyOf(mapOfRegex.keys)
            val firstDigitIndex = it.indexOfFirst { it.isDigit() }
            val resTwo = it.findLastAnyOf(mapOfRegex.keys)
            val lastDigitIndex = it.indexOfLast { it.isDigit() }

            val first = if (res != null && res.first < firstDigitIndex || firstDigitIndex == -1) {
                mapOfRegex[res?.second]
            } else {
                it[firstDigitIndex]
            }

            val second = if (resTwo != null && resTwo.first > lastDigitIndex) {
                mapOfRegex[resTwo.second]
            } else {
                it[lastDigitIndex]
            }
            "$first$second"
        }
        return part1(newInputTwo)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)
    val testInputPartTwo = readInput("Day01_part2_test")
    check(part2(testInputPartTwo) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
