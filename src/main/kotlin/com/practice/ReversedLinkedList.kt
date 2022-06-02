package com.practice

import java.util.*

fun main() {
//    val list = listOf(2, 18, 24, 3, 5, 7, 9, 6, 12)
//    val evenSubList = ArrayList<Int>()
//    val resultList = ArrayList<Int>()
//
//   for (i in list) {
//       if (i % 2 == 0) {
//           evenSubList.add(i)
//       } else {
//           handleEventSublist(evenSubList, resultList)
//           resultList.add(i)
//       }
//    }

//    handleEventSublist(evenSubList, resultList)
//    resultList.forEach { i -> println(i) }

    minimumAbsoluteDifference(arrayOf(3, -7, 0))

}

private fun handleEventSublist(
    evenSubList: ArrayList<Int>,
    resultList: ArrayList<Int>
) {
    if (evenSubList.isNotEmpty()) {
        evenSubList.reverse()
        resultList.addAll(evenSubList)
        evenSubList.clear()
    }
}

fun minimumAbsoluteDifference(arr: Array<Int>): Int {
    // Write your code here
    var minDiff = Int.MAX_VALUE
    Arrays.sort(arr);
    for (i in 0 until arr.size - 1) {
        val diff = Math.abs(arr[i+1] - arr[i])
        if (diff < minDiff) {
            minDiff = diff
        }
        if (minDiff == 0) {
            return minDiff
        }
    }

    // print("minDiff: ${minDiff}")
    return minDiff
}
