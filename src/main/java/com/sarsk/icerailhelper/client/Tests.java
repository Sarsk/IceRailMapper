package com.sarsk.icerailhelper.client;

import static com.sarsk.icerailhelper.client.Utilities.findClosestValue;

public class Tests {
    static void Test_FindClosestValue() {
        System.out.println("Test 1: 0 " + findClosestValue(-1, 45));
        System.out.println("Test 1: 0 " + findClosestValue(-20, 45));
        System.out.println("Test 1: 0 " + findClosestValue(0, 45));
        System.out.println("Test 1: 0 " + findClosestValue(15, 45));

        System.out.println("Test 1: 45 " + findClosestValue(30, 45));
        System.out.println("Test 1: 45 " + findClosestValue(44, 45));
        System.out.println("Test 1: 45 " + findClosestValue(46, 45));
        System.out.println("Test 1: 45 " + findClosestValue(60, 45));

        System.out.println("Test 1: -90 " + findClosestValue(-100, 90));
    }

}
