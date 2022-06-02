package com.practice

import java.util.*

class UnfairNessArray {
}

fun main() {
    val arr = arrayOf(10, 100, 300, 200, 1000, 20, 30)
    val arr2 = arrayOf(4504,
        1520,
        5857,
        4094,
        4157,
        3902,
        822,
        6643,
        2422,
        7288,
        8245,
        9948,
        2822,
        1784,
        7802,
        3142,
        9739,
        5629,
        5413,
        7232)
    println(maxMin(5, arr2))
}

fun maxMin(k: Int, arr: Array<Int>): Int {
    // Write your code here
    arr.sort()
    val linkedList: LinkedList<Int> = LinkedList()
    if (arr.size >= k) {
        for (i in 0..k-1) {
            linkedList.add(arr[i])
        }
        var diff = linkedList.get(k-1) - linkedList.get(0)
        for (i in 0..arr.size - (k+1)) {
            linkedList.removeFirst()
            linkedList.add(arr[k + i])
            val tempDiff = linkedList[k-1] - linkedList[0]
            if (diff > tempDiff) {
                diff = tempDiff
            }
        }
    return diff
    }
    return 0
}
