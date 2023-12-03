fun main() {

    data class P(val i: Int, val j1: Int, val j2: Int)

    fun List<String>.extendNumber(i: Int, j: Int): P? {
        if (this.getOrNull(i)?.getOrNull(j)?.isDigit() != true) return null
        var j1 = j
        var j2 = j
        while (j1 - 1 >= 0 && this[i][j1 - 1].isDigit()) j1--
        while (j2 + 1 < this[i].length && this[i][j2 + 1].isDigit()) j2++
        return P(i, j1, j2)
    }

    fun part1(input: List<String>): Int {
        val ps = mutableSetOf<P>()
        for ((i, line) in input.withIndex()) {
            for ((j, c) in line.withIndex()) {
                if (c != '.' && !c.isDigit()) {
                    for (di in listOf(-1, 0, +1)) {
                        for (dj in listOf(-1, 0, +1)) {
                            if (di == 0 && dj == 0) continue
                            input.extendNumber(i + di, j + dj)
                                    ?.run { ps.add(this) }
                        }
                    }
                }
            }
        }
        return ps.sumOf { (i, j1, j2) ->
            input[i].substring(j1, j2 + 1).toInt()
        }
    }


    fun part2(input: List<String>): Int {
        var sum = 0
        for ((i, line) in input.withIndex()) {
            for ((j, c) in line.withIndex()) {
                if (c == '*') {
                    val pairs = mutableSetOf<P>()
                    for (di in listOf(-1, 0, +1)) {
                        for (dj in listOf(-1, 0, +1)) {
                            if (di == 0 && dj == 0) continue
                            input.extendNumber(i + di, j + dj)
                                    ?.run { pairs.add(this) }
                        }
                    }
                    if (pairs.size == 2) {
                        val it = pairs.iterator()
                        val p1 = it.next()
                        val p2= it.next()
                        sum += input[p1.i].substring(p1.j1, p1.j2 + 1).toInt() *
                                input[p2.i].substring(p2.j1, p2.j2 + 1).toInt()
                    }
                }
            }
        }
        return sum
    }

    check(part1(readInput("Day03_test1")) == 4361)
    part1(readInput("Day03")).println()

    check(part2(readInput("Day03_test1")) == 467835)
    part2(readInput("Day03")).println()
}
