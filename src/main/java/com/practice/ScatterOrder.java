package com.practice;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class ScatterOrder {

    private static final int COUNT = 4000;

    public static void main(String[] args) {

        List<Person> persons = TallerAheadLineSecondKt.getValidList(new ArrayList<>(), 40);
//        persons.add(new Person(10, 7));
//        persons.add(new Person(80, 1));
//        persons.add(new Person(10, 5));
//        persons.add(new Person(20, 4));
//        persons.add(new Person(40, 2));
//        persons.add(new Person(30, 2));
//        persons.add(new Person(90, 0));
//        persons.add(new Person(80, 0));

        System.out.println("Start....\n\n\n");
        Instant start = Instant.now();
        var originalList = new ArrayList<>(persons);

        Collections.shuffle(persons);
        persons.sort(new Comparator<Person>() {

            public int compare(Person o1, Person o2) {
                if (o1.getHeight() == o2.getHeight()) {
                    return -1 * Integer.compare(o1.getTallerAheadCount(), o2.getTallerAheadCount());
                }
                return -1 * Integer.compare(o1.getHeight(), o2.getHeight());
            }
        });
        Instant endOfSort = Instant.now();

        LinkedList<Person> linkedPersons = new LinkedList<>();
        Iterator<Person> iterator = null;
        for (Person p : persons) {

            iterator = linkedPersons.descendingIterator();
            int pos = -1;
            int counter = p.getTallerAheadCount();
            while (iterator.hasNext()) {
                Person item = iterator.next();

                if (p.getHeight() <= item.getHeight() && counter > 0) {
                    counter--;
                    pos = linkedPersons.indexOf(item);
                }
            }
            if (pos != -1) {
                linkedPersons.add(pos, p);
            } else {
                linkedPersons.addLast(p);
            }


        }
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        long timeElapsedSort = Duration.between(start, endOfSort).toMillis();
        System.out.println("count: " + COUNT + "  TIME: " + timeElapsed);
        System.out.println("sort  TIME: " + timeElapsedSort);
        Collections.reverse(originalList);
        System.out.println("bad results: " + TallerAheadLineSecondKt.evaluateResult(originalList, linkedPersons, 0, 0));

        for (int i = 0; i < originalList.size(); i++) {
            System.out.println("original: " + originalList.get(i) + "; resul: " + linkedPersons.get(i));

//        iterator = linkedPersons.iterator();
//        while (iterator.hasNext()) {
//            Person item = iterator.next();
//            System.out.println(linkedPersons.indexOf(item) + " - " + item.toString());
        }
    }
}


class ScatterOrderUtil {

    public static List<Person> getData(int count) {
        List<Person> result = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            result.add(new Person(random.nextInt(count), random.nextInt(count / 10)));
        }

        return result;
    }

}


