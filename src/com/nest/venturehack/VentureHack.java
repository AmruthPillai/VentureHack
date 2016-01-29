/**
 *
 * @title: VentureHack
 * @author: @AmruthPillai
 * @description: Write a program to accept as input a string from the user, and outputs all legal words that can be formed from the word sorted in alphabetical order.
 *
 */

package com.nest.venturehack;

import java.util.Arrays;
import java.util.Scanner;

public class VentureHack {

    public static void main(String[] args) {
        String inputString;
        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter a string of letters (length: 2 to 7): ");
        inputString = userInput.next();

        String[] inputStringArray = inputString.split("");
        System.out.println(Arrays.toString(inputStringArray));

    }
}
