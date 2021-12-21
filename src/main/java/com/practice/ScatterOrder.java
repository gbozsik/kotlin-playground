package com.practice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ScatterOrder {

    public static void main(String[] args) {

        List<P> generatedPersons = generatePersonList();


//        List<P> persons = new ArrayList<>();
//        persons.add(new P(10, 7));
//        persons.add(new P(80, 1));
//        persons.add(new P(10, 5));
//        persons.add(new P(20, 4));
//        persons.add(new P(40, 2));
//        persons.add(new P(30, 2));
//        persons.add(new P(90, 0));
//        persons.add(new P(80, 0));

        generatedPersons.forEach(p -> {
//            System.out.println(p.height);
        });
//        System.out.println("/r/n/n\n");
        var startTime = Instant.now().toEpochMilli();
        generatedPersons.sort(new Comparator<P>() {

            public int compare(P o1, P o2) {
                if (o1.height == o2.height) {
                    return -1 * Integer.compare(o1.higherNum, o2.higherNum);
                }
                return -1 * Integer.compare(o1.height, o2.height);
            }
        });

        LinkedList<P> linkedPersons = new LinkedList<>();
        Iterator<P> iterator = null;
        for (P p : generatedPersons) {
//            System.out.println("for...: p -> "+p.toString());
            if (linkedPersons.isEmpty()) {
                linkedPersons.add(p);
                continue;
            }
            iterator = linkedPersons.descendingIterator();
            int pos = -1;
            int counter = p.higherNum;
            while ( iterator.hasNext()) {
                P item = iterator.next();

                if(p.height<=item.height && counter>0) {
                    counter--;
                    pos = linkedPersons.indexOf(item);}
            }
            if (pos!=-1) {
                linkedPersons.add(pos, p);
            } else {
                linkedPersons.addLast(p);
            }
        }
        var endTime = Instant.now().toEpochMilli();
        System.out.println("list size: " + generatedPersons.size());
        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println("Time elapsed: " + (endTime - startTime));
        iterator = linkedPersons.iterator();
        while (iterator.hasNext()) {
            P item = iterator.next();
//            System.out.println(linkedPersons.indexOf(item) +" - "+item.toString());
// System.out.println(linkedPersons.indexOf(item));
        }

    }

    public static List<P> generatePersonList() {
        List<P> list = new ArrayList<>();
        for (int i = 0; i < 4000; i++) {
            int height = getRandomNumber(1, 200);
            int higherNumber = getRandomNumber(1, 400000000);
            list.add(new P(height, higherNumber));
        }
        return list;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    static class P {
        public int height;
        public int higherNum;

        public P(int height, int higherNum) {
            super();
            this.height = height;
            this.higherNum = higherNum;
        }

        @Override
        public String toString() {
            return "P [height=" + height + ", higherNum=" + higherNum + "]";
        }

    }
}

