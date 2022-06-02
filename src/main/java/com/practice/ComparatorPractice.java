package com.practice;

import java.util.HashMap;
import java.util.Map;

public class ComparatorPractice {

  public static void main(String[] args) {
    ComparatorPractice m = new ComparatorPractice();
    m.solution();
  }

  private void solution() {
    String s = "edaeaccc";
    char[] charArr = s.toCharArray();

    Map<Character, Integer> map = new HashMap<>();
    for (char c : charArr) {
      map.put(c, map.getOrDefault(c, 0)+1);
    }
    map.entrySet().forEach(System.out::println);

    var list = map.entrySet().stream()
        .sorted((o1, o2) -> {
          if (o1.getValue().equals(o2.getValue())) {
            return o1.getKey().compareTo(o2.getKey());
          } else {
            return o2.getValue().compareTo(o1.getValue());
          }
        })
        .map(Map.Entry::getKey);
    list.forEach(System.out::println);
  }
}
