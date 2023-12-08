package Day08

import println
import readInput

fun main() {

    fun part1(input: List<String>): Int {
        val directions = input[0]
        val coordinates = input.drop(2)
            .map { row ->
                val str = row.split(" |=|\\(|\\)|,".toRegex()).filter{it.isNotBlank()}
                Coordinates(str[0], Pair(str[1],str[2]))
            }
        var currentPoint = "AAA"
        var directionCount = 0
        var steps = 0
        run whileLoop@{
            while (true) {
                val nextCoord = coordinates.first { it.startPoint == currentPoint }
                currentPoint = if (directions[directionCount] == 'L') nextCoord.nextPoint.first else nextCoord.nextPoint.second
                directionCount = if (directionCount == directions.length - 1) 0 else directionCount+1
                steps ++

                if (currentPoint == "ZZZ") return@whileLoop
            }
        }
        return steps
    }

    fun part2(input: List<String>): Int {
        val directions = input[0]
        val coordinates = input.drop(2)
            .map { row ->
                val str = row.split(" |=|\\(|\\)|,".toRegex()).filter{it.isNotBlank()}
                Coordinates(str[0], Pair(str[1],str[2]))
            }
        var currentPointList = coordinates.filter { it.startPoint[2]=='A' }.map { it.startPoint }.toMutableList()
        /*
        Solution for 1.

        TODO:
        Find the steps for all in currentPointList
        Then find LCM
         */
        var currentPoint = currentPointList[5]
        var directionCount = 0
        var steps = 0
        run whileLoop@{
            while (true) {
                val nextCoord = coordinates.first { it.startPoint == currentPoint }
                currentPoint = if (directions[directionCount] == 'L') nextCoord.nextPoint.first else nextCoord.nextPoint.second
                directionCount = if (directionCount == directions.length - 1) 0 else directionCount+1
                steps ++

                if (currentPoint[2] == 'Z') return@whileLoop
            }
        }
       return steps
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 6)
    val testInputPartTwo = readInput("Day08_test_2")
    //check(part2(testInputPartTwo) == 6)

    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()
}

data class Coordinates( val startPoint: String, val nextPoint: Pair<String,String>)
