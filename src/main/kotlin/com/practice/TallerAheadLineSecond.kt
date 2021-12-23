package com.practice

import java.time.Duration
import java.time.Instant
import java.util.*
import kotlin.collections.ArrayList

val personList2 = listOf(
    Person(2, 6),
    Person(1, 6),
    Person(5, 1),
    Person(9, 0),
    Person(6, 6),
    Person(4, 7),
    Person(9, 0),
    Person(7, 1),
    Person(4, 3),
    Person(7, 4),
    Person(10, 0),
    Person(3, 9),
    Person(8, 3)
)

private val invalidPersonList = getDataMy(4000)

fun getDataMy(count: Int): List<Person> {
    val result: MutableList<Person> = ArrayList()
    val random = Random()
    for (i in 0 until count) {
        result.add(Person(random.nextInt(count), random.nextInt(count / 10)))
    }
    return result
}

tailrec fun getValidList(list: MutableList<Person>, length: Int): MutableList<Person> {
    if (length < 1) return list
    val randomHeight = Random().nextInt(200)

//    var tallerCount = 0
//    list.forEach { if (it.height > randomHeight) tallerCount++ }

    //instead forEach, tallerCount immutable with this approach
    val tallerCount = list.map { it.height }.fold(0, {acc, height -> if (height > randomHeight) acc + 1 else acc})

    list.add(Person(randomHeight, tallerCount))
    return getValidList(list, length - 1)
}

fun getOriginalOrderOfLine2(list: MutableList<Person>): LinkedList<Person> {
    println("Calc started...")
    val start = Instant.now()
    val comparator = compareBy<Person> { it.height }.reversed()
        .thenComparator({ p1, p2 -> compareValues(p2.tallerAheadCount, p1.tallerAheadCount) })
    val sortedList = list.sortedWith(comparator)
    val linkedList = LinkedList<Person>()
    sortedList?.forEach { person: Person ->
        placePersonInOriginalLineLinkedList(linkedList, person)
    }
    val end = Instant.now()
    println("time elapsed: ${Duration.between(start, end).toMillis()}")
    println("list size: ${linkedList.size}")
    return linkedList
}

private fun placePersonInOriginalLineLinkedList(
    linkedList: LinkedList<Person>,
    person: Person
) {
    var tallerAheadCounter = 0
    var inserted = false
    for (i in 0 until linkedList.size) {
        if (person.height < linkedList[i].height && person.tallerAheadCount == linkedList[i].tallerAheadCount) {
            linkedList.add(i, person)
            inserted = true
            break
        }
        if (person.height < linkedList[i].height && person.tallerAheadCount > tallerAheadCounter) {
            tallerAheadCounter++
        }
        if (person.height < linkedList[i].height && person.tallerAheadCount == tallerAheadCounter) {
            linkedList.add(i + 1, person)
            inserted = true
            break
        }
    }
    if (!inserted) {
        linkedList.addLast(person)
    }
}


fun main() {
    val list = getValidList(ArrayList(), 4000)
//    println(list)
//    println("______________________________________________")
    val originalList = list.toList()
    list.shuffle()
    val resultList = getOriginalOrderOfLine2(list)
//    resultList.forEach { println(it) }
    println("bad results: ${evaluateResult(originalList, resultList, 0, 0)}")
}

tailrec fun evaluateResult(originalList: List<Person>, resultList: List<Person>, index: Int, errors: Int): Int {
    if (index > originalList.size - 1) return errors
    if (originalList[index].height != resultList[index].height || originalList[index].tallerAheadCount != resultList[index].tallerAheadCount) {
        println("invalid result at index: $index,  original Person: ${originalList[index]}, resulted Person: ${resultList[index]}")
        return evaluateResult(originalList, resultList, index + 1, errors + 1)
    } else {
        return evaluateResult(originalList, resultList, index + 1, errors)
    }
}
