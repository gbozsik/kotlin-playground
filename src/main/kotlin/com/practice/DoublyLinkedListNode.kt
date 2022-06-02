package com.practice

import java.util.*
import kotlin.collections.HashSet

data class DoublyLinkedListNode(val data: Int, var next: DoublyLinkedListNode?, var prev: DoublyLinkedListNode?) {
//         var data: Int = 0
//         var next: DoublyLinkedListNode? = null
//         var prev: DoublyLinkedListNode? = null
     }

fun main() {
    val four = DoublyLinkedListNode(4, null, null)
    val two = DoublyLinkedListNode(2, null, null)
    val three = DoublyLinkedListNode(3, four, two)
    val one = DoublyLinkedListNode(1, two, null)
    four.prev = three
    two.next = three
    two.prev = one
    reverse(one)
    val scan: Scanner = Scanner(System.`in`)
    val set: HashSet<String> = hashSetOf("a", "b")
    set.add("d")
}


fun reverse(list: DoublyLinkedListNode?): DoublyLinkedListNode? {
    var next = list
    while (next?.next != null) {
        next = next?.next
    }
    back(next)
    return null
}

tailrec fun back(node: DoublyLinkedListNode?) {
    if (node == null) {
        println()
        return
    }
    print(" ${node?.data}")
    back(node?.prev)
}
