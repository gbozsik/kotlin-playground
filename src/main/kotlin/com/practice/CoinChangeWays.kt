package com.practice


class CoinChangeWays {
}

fun main() {
    getWays(166, arrayOf(5, 37, 8, 39, 33, 17, 22, 32, 13, 7, 10, 35, 40, 2, 43, 49, 46, 19, 41, 1, 12, 11, 28))
}

fun getWays(n: Int, c: Array<Long>): Long {
    var partialSum: Long = 0
    var result: Long = 0
    c.sortDescending()
    for (i in c.indices) {
        partialSum = 0
        var checkedIndex = 0
        var resetPartialSum = c[i]
        var resetPartialSumIndex = 0
        var index = i
        while (partialSum < n && index < c.size) {
            if (partialSum < n) {
                partialSum += c[index]
                if (c[index] == resetPartialSum && partialSum < n) {
                    resetPartialSum = partialSum
                    resetPartialSumIndex = index
                }
            }
            if (partialSum == n.toLong()) {
                result++
                partialSum = resetPartialSum
                if (index == c.size-1) {
                    resetPartialSum = 0
                    break
                } else {
                    index++
                }
            }
            if (partialSum > n) {
                if (index == c.size - 1) {
                    checkedIndex++
                    index = checkedIndex
                    partialSum = resetPartialSum
                } else {
                    partialSum -= c[index]
                    index++
                }
            }
        }
    }
    println(result)
    return result;
}