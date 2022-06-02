package com.practice

class GreedyFlowersKotlin {
}

fun main() {
    val arr = arrayOf(390225, 426456, 688267, 800389, 990107, 439248, 240638, 15991, 874479, 568754, 729927, 980985, 132244, 488186, 5037, 721765, 251885, 28458, 23710, 281490, 30935, 897665, 768945, 337228, 533277, 959855, 927447, 941485, 24242, 684459, 312855, 716170, 512600, 608266, 779912, 950103, 211756, 665028, 642996, 262173, 789020, 932421, 390745, 433434, 350262, 463568, 668809, 305781, 815771, 550800)
    print(CalculateMinPrice(arr, 3))
}

fun CalculateMinPrice(c: Array<Int>, k: Int): Int {
    var bought = 0
    var totalSum = 0
    var tempCount = 0
    c.sort()
    for (i in c.size - 1 downTo 0) {
        if (tempCount == k) {
            bought++
            tempCount = 0
        }
        totalSum += (bought + 1) * c[i]
        tempCount++
    }
    return totalSum
}