package com.practice

import java.math.BigDecimal
import kotlin.system.measureTimeMillis

class FibonacciEfficient {
}

fun main() {
    val n = 10000
    val arr: Array<BigDecimal?> = arrayOfNulls(n + 1)
//    val elapsed = measureTimeMillis {
//        println(fib(n, arr))
//    }
//    println("recursive: $elapsed")
    val elapsed2 = measureTimeMillis {
        println(finBootomUp(n))
    }
    println("bottom up: $elapsed2")
    val elapsed3 = measureTimeMillis {
        println(fibTailrec(n, 0, Array<BigDecimal>(n+1){i -> BigDecimal.ZERO }))
    }
    println("tailrec: $elapsed3")
}

fun fib(n: Int, memoize: Array<BigDecimal?>): BigDecimal {
    if (memoize[n] != null) return memoize[n]!!
    val result: BigDecimal
    if ((n == 1) || (n == 2)) result = BigDecimal.ONE
    else {
        result = fib(n - 1, memoize) + fib(n - 2, memoize)
    }
    memoize[n] = result
    return result
}

fun finBootomUp(n: Int): BigDecimal {
    if (n == 1 || n == 2) return BigDecimal.ONE
    val bottomUp = Array<BigDecimal>(n + 1) { i -> BigDecimal.ZERO }
    bottomUp[1] = BigDecimal.ONE
    bottomUp[2] = BigDecimal.ONE
    for (i in 3..n) {
        bottomUp[i] = bottomUp[i - 1] + bottomUp[i - 2]
    }
    return bottomUp[n]
}

tailrec fun fibTailrec(n: Int, index: Int, bottomUp: Array<BigDecimal>): BigDecimal {
    if (n == 1 || n == 2) return BigDecimal.ONE
//    val bottomUp = Array<BigDecimal>(n + 1) {i -> BigDecimal.ZERO }
    if (index == 0 || index == 1 || index == 2) {
        bottomUp[1] = BigDecimal.ONE
        bottomUp[2] = BigDecimal.ONE
        return fibTailrec(n, index + 3, bottomUp)
    }

//    for (i in 3..n) {
    bottomUp[index] = bottomUp[index - 1] + bottomUp[index - 2]
    if (index == n) {
        return bottomUp[n]
    }
//    }
    return fibTailrec(n, index + 1, bottomUp)
}