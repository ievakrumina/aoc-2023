fun main() {

    fun List<String>.findMaxValueOfColor(color: String): Int {
        return this.filter { it.contains(color) }.maxOf { it.replace(color, "").toInt() }
    }

    fun part1(input: List<String>): Int {
        return input.map {
            it.replace(" ", "").split(":|;|,".toRegex())
        }
            .filter {
                val greenMax = it.findMaxValueOfColor("green")
                val blueMax = it.findMaxValueOfColor("blue")
                val redMax = it.findMaxValueOfColor("red")
                greenMax <= 13 && redMax <= 12 && blueMax <= 14
            }
            .sumOf {
                it[0].replace("Game", "").toInt()
            }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf {
            val subValue = it.replace(" ", "").split(":|;|,".toRegex())
            val greenMax = subValue.findMaxValueOfColor("green")
            val blueMax = subValue.findMaxValueOfColor("blue")
            val redMax = subValue.findMaxValueOfColor("red")
            greenMax * blueMax * redMax
        }

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)
    val testInputPartTwo = readInput("Day02_test")
    check(part2(testInputPartTwo) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
