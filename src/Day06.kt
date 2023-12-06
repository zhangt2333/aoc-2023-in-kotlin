fun main() {

    fun part1(input: List<String>): Int {
        val (timeStr, disStr) = input
        val times = timeStr.substringAfter("Time:")
            .split(' ').filter { it.isNotEmpty() }.map { it.toInt() }
        val diss = disStr.substringAfter("Distance:")
            .split(' ').filter { it.isNotEmpty() }.map { it.toInt() }
        val nums: List<Int> = times.zip(diss).map { (time, dis) ->
            (1..time).filter { i ->
                (time - i) * i > dis
            }.size
        }
        return nums.reduce(Int::times)
    }

    fun part2(input: List<String>): Int {
        val (timeStr, disStr) = input
        val time = timeStr.substringAfter("Time:")
            .replace(" ", "").toInt()
        val distance = disStr.substringAfter("Distance:")
            .replace(" ", "").toLong()
        // binary search
        var l = 1
        var r = time
        var ans1 = -1
        var ans2 = -1
        while (l <= r) {
            val mid = (l + r) / 2
            val dis = 1L * (time - mid) * mid
            if (dis > distance) {
                ans1 = mid
                r = mid - 1
            } else {
                l = mid + 1
            }
        }
        l = 1
        r = time
        while (l <= r) {
            val mid = (l + r) / 2
            val dis = 1L * (time - mid) * mid
            if (dis > distance) {
                ans2 = mid
                l = mid + 1
            } else {
                r = mid - 1
            }
        }
        if (ans1 != -1 && ans2 != -1) {
            return ans2 - ans1 + 1
        }
        throw Exception("No answer")
    }

    check(part1(readInput("Day06_test1")) == 288)
    part1(readInput("Day06")).println()

    check(part2(readInput("Day06_test1")) == 71503)
    part2(readInput("Day06")).println()
}
