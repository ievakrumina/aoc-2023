import java.math.BigInteger
import java.math.MathContext
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt

fun main() {


    fun part1(input: List<String>): Int {
        val timeList = input[0].split(" ").drop(1).filter { it.isNotBlank() }.map { it.toInt() }
        val distanceList = input[1].split(" ").drop(1).filter { it.isNotBlank() }.map { it.toInt() }
        return timeList.mapIndexed { index, time ->
            val d = sqrt((time*time-4*distanceList[index]).toDouble())
            val max = (time+d)/2
            val min = (time-d)/2
            val adjustedMax = if (max.mod(10.toDouble()) == 0.toDouble()) max - 1 else floor(max)
            val adjustedMin = if (min.mod(10.toDouble()) == 0.toDouble()) min + 1 else ceil(min)
            adjustedMax - adjustedMin +1
        }.reduce(Double::times).toInt()
    }

    fun part2(input: List<String>): Int {
        val time = input[0].replace(" ","").drop(5).toBigDecimal()
        val distance = input[1].replace(" ","").drop(9).toBigDecimal()
        val b = time*time
        val ac = distance.times(4.toBigDecimal())
        val bac = b.subtract(ac)
        val d = bac.sqrt(MathContext.DECIMAL64)
        val max = (time.add(d)).divide(2.toBigDecimal())
        val min = (time.subtract(d)).divide(2.toBigDecimal())
        println(" XY $max $min")
        return max.subtract(min).toInt()
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 288)
    val testInputPartTwo = readInput("Day06_test")
    check(part2(testInputPartTwo) == 71503)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}

