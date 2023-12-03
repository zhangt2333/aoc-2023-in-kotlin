fun main() {
    fun part1(input: List<String>): Int {
        val nums = input.map { line ->
            line.first { it.isDigit() }.digitToInt() * 10 + line.last { it.isDigit() }.digitToInt()
        }
        return nums.sum()
    }

    val englishNumber = listOf(
        "_",
        "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
    )

    fun findFirstDigit(line: String, it: Iterable<IndexedValue<Char>>): Int {
        for ((i1, c) in it) {
            if (c.isDigit()) return c.digitToInt()
            englishNumber.forEachIndexed { i2, s ->
                if (line.indexOf(s, startIndex = i1) == i1) {
                    return i2
                }
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        val nums = input.map { line ->
            val first = findFirstDigit(line, line.withIndex())
            val last = findFirstDigit(line, line.withIndex().reversed())
            return@map first * 10 + last
        }
        return nums.sum()
    }

    check(part1(readInput("Day01_test1")) == 142)
    part1(readInput("Day01")).println()

    check(part2(readInput("Day01_test2")) == 281)
    part2(readInput("Day01")).println()

}
