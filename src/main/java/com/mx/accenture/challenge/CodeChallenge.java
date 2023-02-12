package com.mx.accenture.challenge;

/*
**** Code Challenge JAVA 2023 ****

* Instructions:
Given an array of integers, and an integer representing a target sum.
Write a Java program that can read the array and display a pair of numbers that sum up to the target sum.
Example:
    Input:
        [2,7,11,15], target =18
    Expected output:
        7,11

* Test Cases:
    TC 1: Happy path
    Input: [2,7,11,15], target =18
    Expected output: 7,11

    TC 2: There is no pair to achieve the target sum
    Input: [2,7,11,15], target =11
    Expected output: null

    TC 3: 2 possible solutions
    Input: [2,7,11,15,3], target =18
    Expected output: 7,11

    TC 4: negative case,
    Input: [], target =0
    Expected output: null
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CodeChallenge {
    public Integer[]  resolve(Integer[] numbers, Integer target){

        if (numbers.length == 0) {
            return null;
        }

        Map<Integer, Integer> m = new HashMap<>();
        Integer[] solution = new Integer[2];

        for (int i : numbers) {
            int aux = target - i;
            if (m.containsKey(aux)) {
                solution[0] = aux;
                solution[1] = i;
                return solution;
            }
            m.put(i, i);
        }

        return null;
    }
}
