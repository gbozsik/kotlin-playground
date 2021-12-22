package com.practice

import java.time.Instant
import java.util.*

data class Person(val height: Int, val tallerAheadCount: Int)

val persons = getData(4000)

fun getOriginalOrderOfLine(persons: List<Person>): List<Person> {
    //println("persons-count= ${persons.size}")
    val start = Instant.now().toEpochMilli()
    val tallerAheadPersonListMap: Map<Int, List<Person>> =
        persons.groupBy { it.tallerAheadCount }.toSortedMap()

    val endOfMapGeneration = Instant.now().toEpochMilli()
    println("map generation: ${endOfMapGeneration - start}")

    val finalLineLinkedList = getLineFromListsGroupedByTallerAheadCount(tallerAheadPersonListMap)
    //println("count-end= ${finalLineLinkedList.size}")
    val end = Instant.now().toEpochMilli()
    println(start)
    println(end)
    println(end - start)
    return finalLineLinkedList.reversed()
}

private fun getLineFromListsGroupedByTallerAheadCount(tallerAheadPersonListMap: Map<Int, List<Person>>): LinkedList<Person> {
    var sumOfSorting: Long = 0;
    val finalLineLinkedList = LinkedList<Person>()
    tallerAheadPersonListMap.forEach {

        val startOfSort = Instant.now().toEpochMilli()
        val personListWithSameTallerAheadCount = it.value.sortedBy { it.height }.reversed()
        val endOfSort = Instant.now().toEpochMilli()
        sumOfSorting += (endOfSort - startOfSort)
        placeEachPersonInLine(personListWithSameTallerAheadCount, finalLineLinkedList)
    }
    println("sum of sorting: $sumOfSorting")
    //println("finalLineLinkedList-count= ${finalLineLinkedList.size}")
    return finalLineLinkedList
}

private fun placeEachPersonInLine(
    personListWithSameTallerAheadCount: List<Person>,
    finalLineLinkedList: LinkedList<Person>
) {
    var sumOfListFilling: Long = 0
    personListWithSameTallerAheadCount.forEach { person ->
        val startOfFill = Instant.now().toEpochMilli()
        placePersonInLine(finalLineLinkedList, person)
        val endOfFill = Instant.now().toEpochMilli()
        sumOfListFilling += (endOfFill - startOfFill)
    }
    println("sum of filling: $sumOfListFilling")
}

private fun placePersonInLine(finalLineLinkedList: LinkedList<Person>, person: Person) {
    var countOfTallerPersons = 0
    var inserted = false
    for (i in 0 until finalLineLinkedList.size) {
        if (person.height < finalLineLinkedList[i].height && person.tallerAheadCount > countOfTallerPersons) {
            countOfTallerPersons++
        }
        if (person.height < finalLineLinkedList[i].height && person.tallerAheadCount == countOfTallerPersons) {
            finalLineLinkedList.add(i + 1, person)
            inserted = true
            break
        }
    }
    if (!inserted) {
        finalLineLinkedList.addLast(person)
    }
}

fun main() {
    if (persons != null) {
        println("list size: ${persons.size}")
        val list = getOriginalOrderOfLine(persons)
        println("result size: ${list.size}")
//        list.forEach {
//            println("${it.height} - ${it.tallerAheadCount}")
    }
//        println("last size: ${list.size}")
//    }
}

fun getData(count: Int): List<Person>? {
    val result: MutableList<Person> = ArrayList()
    val random = Random()
    for (i in 0 until count) {
        result.add(Person(random.nextInt(count), random.nextInt(count / 10)))
    }
    return result
}