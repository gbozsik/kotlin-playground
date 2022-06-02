package com.practice;

import java.util.Arrays;

public class GreedyFlowers {

  public static void main(String[] args) {
    int[] arr = new int[]{1, 3, 5, 7, 9};
    System.out.println(CalculateMinPrice(arr, 3));
  }

  static int CalculateMinPrice(int[] flowers,int k){
    //sort all flower prices in ascending order
    Arrays.sort(flowers);
    int i = flowers.length-1;
    int bought = 0;
    int total=0;
    //start backwards from the most expensive flower, stop when there is no more flowers left
    while(i>=0){
      //Calculate total
      //increment bought by 1 when everyone in the group has bought equal number of flowers
      for(int j=0;j<k && i>=0;j++){
        total+=(1+bought)*flowers[i];
        i--;
      }
      bought++;
    }
    return total;
  }
}
