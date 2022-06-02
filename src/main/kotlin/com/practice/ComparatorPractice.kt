package com.practice

import java.util.function.Consumer
import java.util.function.Function

fun main() {
    val s = "eddddaeaccc"
    val charArr = s.toCharArray()

    val map: MutableMap<Char, Int> = HashMap()
    for (c in charArr) {
        map[c] = map.getOrDefault(c, 0) + 1
    }
    map.entries.forEach(Consumer<Map.Entry<Char, Int>> { x: Map.Entry<Char, Int>? ->
        println(
            x
        )
    })

    val list = map.entries.stream()
        .sorted { (key, value): Map.Entry<Char, Int>, (key1, value1): Map.Entry<Char, Int> ->
            if (value == value1) {
                return@sorted key.compareTo(key1)
            } else {
                return@sorted value1.compareTo(value)
            }
        }
        .map({ (key, _) -> key })
    list.forEach { x: Char? -> println(x) }
}
