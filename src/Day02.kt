fun main() {
    fun part1(input: List<String>): Int {
        val nums = input.mapIndexed { i, line ->
            val color2Max = mutableMapOf<String, Int>()
            line.substringAfter(": ").split("; ").map { set ->
                set.split(", ").map { numAndColor ->
                    val temp = numAndColor.split(" ")
                    val num = temp[0].toInt()
                    val color = temp[1]
                    if (color2Max.getOrPut(color) { num } < num) {
                        color2Max[color] = num
                    }
                }
            }
            if (color2Max.getOrDefault("red", 0) <= 12
                && color2Max.getOrDefault("green", 0) <= 13
                && color2Max.getOrDefault("blue", 0) <= 14) {
                return@mapIndexed i + 1
            }
            return@mapIndexed 0
        }
        return nums.sum()
    }

    fun part2(input: List<String>): Int {
        val nums = input.mapIndexed { i, line ->
            val color2Max = mutableMapOf<String, Int>()
            line.substringAfter(": ").split("; ").map { set ->
                set.split(", ").map { numAndColor ->
                    val temp = numAndColor.split(" ")
                    val num = temp[0].toInt()
                    val color = temp[1]
                    if (color2Max.getOrPut(color) { num } < num) {
                        color2Max[color] = num
                    }
                }
            }
            return@mapIndexed color2Max.values.reduce(Int::times)
        }
        return nums.sum()
    }

    check(part1(readInput("Day02_test1")) == 8)
    part1(readInput("Day02")).println()

    check(part2(readInput("Day02_test2")) == 2286)
    part2(readInput("Day02")).println()
}
