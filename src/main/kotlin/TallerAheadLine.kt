import com.practice.ScatterOrder
import java.time.Instant
import java.util.*

data class Person(val height: Int, val tallerAheadCount: Int)

val persons = generatePersonList()

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

fun generatePersonList(): List<Person>? {
    val list: MutableList<Person> = ArrayList()
    for (i in 0..399999) {
        val height = ScatterOrder.getRandomNumber(1, 200)
        val higherNumber = ScatterOrder.getRandomNumber(1, 400000000)
        list.add(Person(height, higherNumber))
    }
    return list
}

fun getOriginalOrderOfLine(persons: List<Person>): List<Person> {
    val start = Instant.now().toEpochMilli()
    val tallerAheadPersonListMap: Map<Int, List<Person>> =
        persons.groupBy { it.tallerAheadCount }.toSortedMap()

    val finalLineLinkedList = getLineFromListsGroupedByTallerAheadCount(tallerAheadPersonListMap)
    val end = Instant.now().toEpochMilli()
    println(start)
    println(end)
    println(end - start)
    return finalLineLinkedList.reversed()
}

private fun getLineFromListsGroupedByTallerAheadCount(tallerAheadPersonListMap: Map<Int, List<Person>>): LinkedList<Person> {
    var baseListSet = false
    val finalLineLinkedList = LinkedList<Person>()
    tallerAheadPersonListMap.forEach {
        if (!baseListSet) {
            finalLineLinkedList.addAll(it.value.sortedBy { it.height })
            baseListSet = true
        } else {
            val personListWithSameTallerAheadCount = it.value.sortedBy { it.height }
            placeEachPersonInLine(personListWithSameTallerAheadCount, finalLineLinkedList)
        }
    }
    return finalLineLinkedList
}

private fun placeEachPersonInLine(
    personListWithSameTallerAheadCount: List<Person>,
    finalLineLinkedList: LinkedList<Person>
) {
    personListWithSameTallerAheadCount.forEach { person ->
        placePersonInLine(finalLineLinkedList, person)
    }
}

private fun placePersonInLine(finalLineLinkedList: LinkedList<Person>, person: Person) {
    var countOfTallerPersons = 0
    for (i in 0 until finalLineLinkedList.size) {
        if (person.height < finalLineLinkedList[i].height && person.tallerAheadCount > countOfTallerPersons) {
            countOfTallerPersons++
        }
        if (person.height < finalLineLinkedList[i].height && person.tallerAheadCount == countOfTallerPersons) {
            finalLineLinkedList.add(i + 1, person)
            break
        }
    }
}

fun main() {
    if (persons != null) {
        println("list size: ${persons.size}")
        getOriginalOrderOfLine(persons).forEach {
            println("${it.height} - ${it.tallerAheadCount}")
        }
    }
}