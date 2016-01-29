/**
 * @title: VentureHack
 * @author: @AmruthPillai
 * @description: Write a program to accept as input a string from the user, and outputs all legal words that can be formed from the word sorted in alphabetical order.
 */

package com.amruthpillai.venturehack;

// Apache Commons IO Library to handle File IO gracefully

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// Basic Java Libraries

public class VentureHack {

    // Global Variables
    static List<String> legalWords; // Dictionary to hold List of Legal Words
    static List<String> resultWords = new ArrayList<>(); // List to hold the resulting words

    public static void main(String[] args) {

        // Private Variables to Main Function
        String inputString; // inputString to hold raw user input
        Scanner userInput = new Scanner(System.in); // Scanner Object to take user input

        do {
            // Take input from user, only the first word entered will be considered
            System.out.println("Enter a single word within the range of 2 to 7 letters: ");
            inputString = userInput.next();

            // Ignore case of user input and convert to lowercase
            inputString = inputString.toLowerCase();

            // If input is in admissible range, then break loop
            if (inputString.length() >= 2 && inputString.length() <= 7 && isAlpha(inputString))
                break;

            System.out.println("Please try again, use alphabets only and make sure it is within the range of 2 to 7 letters!\n");
        } while (true);

        // Using Try-Catch Block to make sure the Dictionary was found in the package
        try {
            legalWords = FileUtils.readLines(new File("LegalWords.txt"), Charsets.UTF_8);
        } catch (IOException e) {
            System.out.println("The Legal Words Dictionary was not found, please re-download package!");
        }

        findPermutations(inputString);

        // Sort the resulting list in alphabetical order
        Collections.sort(resultWords);

        // Iterate through the list and display all results
        resultWords.forEach(resultWord -> System.out.println(resultWord));

    }

    public static void findPermutations(String inputString) {
        StringBuffer outputString = new StringBuffer(); // outputString to hold the final result
        char[] inputStringArray = inputString.toCharArray(); // inputStringArray to hold array of letters from input string
        int inputLength = inputString.length(); // inputLength to hold the length of input string
        boolean[] used = new boolean[inputLength]; // used Boolean to check if letter has been used or not

        executePermutations(inputStringArray, outputString, used, inputLength, 0);
    }

    public static void executePermutations(char[] inputStringArray, StringBuffer outputString, boolean[] used, int inputLength, int level) {
        for (int i = 0; i < inputLength; i++) {

            // If letter has been used already, then skip loop
            if (used[i])
                continue;

            // Append the unused letter to new output string
            outputString.append(inputStringArray[i]);

            // Check if the new output string is in the Dictionary of Legal Words
            if (legalWords.contains(outputString.toString()))
                resultWords.add(outputString.toString());

            // Use Recursion to iterate through all possible combinations
            used[i] = true;
            executePermutations(inputStringArray, outputString, used, inputLength, level + 1);
            used[i] = false;
            outputString.setLength(outputString.length() - 1);
        }
    }

    // Function to check is string contains only alphabets, using Regular Expression
    public static boolean isAlpha(String string) {
        return string.matches("[a-zA-Z]+");
    }

}
