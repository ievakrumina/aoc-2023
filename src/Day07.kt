fun main() {

    fun getCombination(str: String): PokerValues {
        val numberOfJokers = str.filter { it == '0' }.length
        return when {
            str.contains("([A-Z\\d])\\1\\1\\1\\1".toRegex()) -> PokerValues.FIVE
            str.contains("([A-Z\\d])\\1\\1\\1".toRegex()) -> if (numberOfJokers>0) PokerValues.FIVE else PokerValues.FOUR
            str.contains("([A-Z\\d])\\1\\1".toRegex()) -> {
                if (numberOfJokers == 3) {
                    val t = str.replace("([A-Z\\d])\\1\\1".toRegex(), "")
                    if (t.contains("([A-Z\\d])\\1".toRegex())) {
                        PokerValues.FIVE
                    } else {
                        PokerValues.FOUR
                    }
                } else {
                    val t = str.replace("([A-Z\\d])\\1\\1".toRegex(), "")
                    if (t.contains("([A-Z\\d])\\1".toRegex())) {
                        when (numberOfJokers) {
                            2 -> PokerValues.FIVE
                            else -> PokerValues.FULL_HOUSE
                        }
                    } else {
                        when (numberOfJokers) {
                            1 -> PokerValues.FOUR
                            else -> PokerValues.THREE
                        }
                    }
                }
            }
            str.contains("([A-Z\\d])\\1".toRegex()) -> {
                if (numberOfJokers == 2) {
                    val t = str.replace("([A-Z\\d])\\1".toRegex(), "")
                    if (t.contains("([A-Z\\d])\\1\\1".toRegex())) {
                        PokerValues.FIVE
                    } else if (t.length == 1) {
                        PokerValues.FOUR
                    } else {
                        PokerValues.THREE
                    }
                } else {
                    val t = str.replace("([A-Z\\d])\\1".toRegex(), "")
                    if (t.contains("([A-Z\\d])\\1\\1".toRegex())) {
                        PokerValues.FULL_HOUSE
                    } else if (t.length == 1) {
                        if (numberOfJokers > 0) PokerValues.FULL_HOUSE else PokerValues.TWO_PAIRS
                    } else {
                        if (numberOfJokers > 0) PokerValues.THREE else PokerValues.PAIR
                    }
                }
            }

            else -> if (numberOfJokers > 0) PokerValues.PAIR else PokerValues.HIGH_CARD
        }
    }
    fun String.sortString() = this.toCharArray().sorted().joinToString("")

    fun part1(input: List<String>): Int {
        val listOfCamelPoker = input.map { inp ->
            val fromInput = inp.split(" ")
            val adjustHand = fromInput[0].map {
                when (it) {
                    'A' -> 'Z'
                    'K' -> 'Y'
                    'Q' -> 'X'
                    'J' -> 'W'
                    else -> it
                }
            }.joinToString("")
            val combination = getCombination(adjustHand.sortString())
            CamelPoker(adjustHand, fromInput[1].toInt(), combination)
        }.sortedWith(compareBy<CamelPoker> {it.combination}.reversed().thenBy { it.hand } )
        return listOfCamelPoker.mapIndexed { index, value -> value.bid*(index+1) }.sum()
    }

    fun part2(input: List<String>): Int {
        val listOfCamelPoker = input.map { inp ->
            val fromInput = inp.split(" ")
            val adjustHand = fromInput[0].map {
                when (it) {
                    'A' -> 'Z'
                    'K' -> 'Y'
                    'Q' -> 'X'
                    'J' -> '0'
                    else -> it
                }
            }.joinToString("")
            val combination = getCombination(adjustHand.sortString(), true)
            CamelPoker(adjustHand, fromInput[1].toInt(), combination)
        }.sortedWith(compareBy<CamelPoker> {it.combination}.reversed().thenBy { it.hand } )
        return listOfCamelPoker.mapIndexed { index, value -> value.bid*(index+1) }.sum()
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 6440)
    val testInputPartTwo = readInput("Day07_test")
    check(part2(testInputPartTwo) == 5905)

    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}

enum class PokerValues {
    FIVE, FOUR, FULL_HOUSE, THREE, TWO_PAIRS, PAIR, HIGH_CARD
}

data class CamelPoker(val hand: String, val bid: Int, val combination: PokerValues = PokerValues.HIGH_CARD)

