fun main() {

    fun List<String>.getInputMap(startValue: String): List<String> =
        this.drop(this.indexOf(startValue) + 1).takeWhile { it != "" }

    fun part1(input: List<String>): Int {
        val seeds = input[0].substring(7).split(" ").map { it.toLong() }
        val soilMap = input.getInputMap("seed-to-soil map:")
        val fertilazerMap = input.getInputMap("soil-to-fertilizer map:")
        val waterMap = input.getInputMap("fertilizer-to-water map:")
        val lightMap = input.getInputMap("water-to-light map:")
        val tempMap = input.getInputMap("light-to-temperature map:")
        val humidityMap = input.getInputMap("temperature-to-humidity map:")
        val locationMap = input.getInputMap("humidity-to-location map:")
        val res = seeds.map { seed ->
            var mappedValue = seed
            soilMap.forEach {
                val a = it.split(" ").map { it.toLong() }
                if (seed >= a[1] && seed < a[1] + a[2]) {
                    mappedValue = a[0] + (seed - a[1])
                }
            }
            mappedValue
        }
            .map { seed ->
                var mappedValue = seed
                fertilazerMap.forEach {
                    val a = it.split(" ").map { it.toLong() }
                    if (seed >= a[1] && seed < a[1] + a[2]) {
                        mappedValue = a[0] + (seed - a[1])
                    }
                }
                mappedValue
            }
            .map { seed ->
                var mappedValue = seed
                waterMap.forEach {
                    val a = it.split(" ").map { it.toLong() }
                    if (seed >= a[1] && seed < a[1] + a[2]) {
                        mappedValue = a[0] + (seed - a[1])
                    }
                }
                mappedValue
            }
            .map { seed ->
                var mappedValue = seed
                lightMap.forEach {
                    val a = it.split(" ").map { it.toLong() }
                    if (seed >= a[1] && seed < a[1] + a[2]) {
                        mappedValue = a[0] + (seed - a[1])
                    }
                }
                mappedValue
            }
            .map { seed ->
                var mappedValue = seed
                tempMap.forEach {
                    val a = it.split(" ").map { it.toLong() }
                    if (seed >= a[1] && seed < a[1] + a[2]) {
                        mappedValue = a[0] + (seed - a[1])
                    }
                }
                mappedValue
            }
            .map { seed ->
                var mappedValue = seed
                humidityMap.forEach {
                    val a = it.split(" ").map { it.toLong() }
                    if (seed >= a[1] && seed < a[1] + a[2]) {
                        mappedValue = a[0] + (seed - a[1])
                    }
                }
                mappedValue
            }
            .map { seed ->
                var mappedValue = seed
                locationMap.forEach {
                    val a = it.split(" ").map { it.toLong() }
                    if (seed >= a[1] && seed < a[1] + a[2]) {
                        mappedValue = a[0] + (seed - a[1])
                    }
                }
                mappedValue
            }
        return res.min().toInt()
    }

    fun part2(input: List<String>): Int {
        // TODO
        return 1
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 35)
    val testInputPartTwo = readInput("Day05_test")
    check(part2(testInputPartTwo) == 1)

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}

