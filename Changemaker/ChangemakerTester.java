//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P06 Changemaker
// Course: CS 300 Spring 2023
//
// Author: Nandini jagtiani
// Email: jagtiamo@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Ayushi Saigal
// Partner Email: asaigal2@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// __X_ Write-up states that pair programming is allowed for this assignment.
// __X_ We have both read and understand the course Pair Programming Policy.
// __X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: No helped was given or received (identify each by name and describe how they helped)
// Online Sources: No helped was given or received (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class is the tester class for P06 Changemaker
 *
 *
 *Author: Nandini Jagtiani Author: Ayushi Saigal
 **/

import java.util.Arrays;

public class ChangemakerTester {

    public static boolean testCountBase() {
        // Scenario 1: value is negative
        int count1 = Changemaker.count(new int[] {1, 5, 10, 25}, new int[] {3, 1, 2, 1}, -10);
        if (count1 != 0) {
            return false;
        }

        // Scenario 2: value is positive but there is no way to make change
        int count2 = Changemaker.count(new int[] {1, 5, 10, 25}, new int[] {0, 0, 0, 0}, 3);
        if (count2 != 0) {
            return false;
        }

        // Scenario 3: value is zero
        int count3 = Changemaker.count(new int[] {1, 5, 10, 25}, new int[] {3, 1, 2, 1}, 0);
        if (count3 != 1) {
            return false;
        }

        // All scenarios passed
        return true;
    }


    public static boolean testCountRecursive() {
        // Scenario 1: multiple ways to make change
        int count1 = Changemaker.count(new int[] {1, 5, 10, 25}, new int[] {1, 1, 1, 1}, 36);
        if (count1 != 6) {
            return false;
        }

        // Scenario 2: multiple optimal ways to make change
        int count2 = Changemaker.count(new int[] {2, 5, 7, 10}, new int[] {1, 1, 1, 1}, 12);
        if (count2 != 4) {
            return false;
        }

        // Scenario 3: non-greedy optimal way to make change
        int count3 = Changemaker.count(new int[] {1, 5, 6, 9}, new int[] {2, 1, 1, 1}, 11);
        if (count3 != 5) {
            return false;
        }

        // All scenarios passed
        return true;
    }

    public static boolean testMinCoinsBase() {
        // Scenario 1: value is negative
        int count1 = Changemaker.minCoins(new int[] {1, 5, 10, 25}, new int[] {3, 1, 2, 1}, -10);
        if (count1 != -1) {
            return false;
        }
        // Scenario 2: value is positive but there is no way to make change
        int count2 = Changemaker.minCoins(new int[] {1, 5, 10, 25}, new int[] {0, 0, 0, 0}, 3);
        if (count2 != -1) {
            return false;
        }
        int count3 = Changemaker.minCoins(new int[] {1, 5, 10, 25}, new int[] {3, 1, 2, 1}, 0);
        if (count3 != 0) {
            return false;
        }

        // All scenarios passed
        return true;

    }

    public static boolean testMinCoinsRecursive() {
        // Scenario 1: using at least 3 different coins
        int count1 = Changemaker.minCoins(new int[] {1, 5, 10, 25}, new int[] {1, 1, 1, 1}, 36);
        if (count1 != 3) {
            return false;
        }

        // Scenario 2: multiple ways to make change using same optimal number of coins
        int count2 = Changemaker.minCoins(new int[] {2, 5, 7, 10}, new int[] {1, 1, 1, 1}, 12);
        if (count2 != 2) {
            return false;
        }

        // Scenario 3: non-greedy optimal way to make change
        int count3 = Changemaker.minCoins(new int[] {1, 5, 6, 9}, new int[] {2, 1, 1, 1}, 11);
        if (count3 != 2) {
            return false;
        }

        // All scenarios passed
        return true;
    }

    public static boolean testMakeChangeBase() {
        // Scenario 1: value is negative
        int[] result1 = Changemaker.makeChange(new int[] {1, 5, 10, 25}, new int[] {3, 1, 2, 1}, -10);
        if (result1 != null) {
            return false;
        }

        // Scenario 2: value is positive but there is no way to make change
        int[] result2 = Changemaker.makeChange(new int[] {1, 5, 10, 25}, new int[] {0, 0, 0, 0}, 3);
        if (result2 != null) {
            return false;
        }

        // Scenario 3: value is zero
        int[] expected3 = new int[] {0, 0, 0, 0};
        int[] result3 = Changemaker.makeChange(new int[] {1, 5, 10, 25}, new int[] {3, 1, 2, 1}, 0);
        if (!Arrays.equals(result3, expected3)) {
            return false;
        }

        // All scenarios passed
        return true;
    }

    public static boolean testMakeChangeRecursive() {
        // Scenario 1: using at least 3 different coins
        int[] expected1 = new int[] {1, 0, 1, 1};
        int[] result1 = Changemaker.makeChange(new int[] {1, 5, 10, 25}, new int[] {1, 1, 1, 1}, 36);
        if (!Arrays.equals(result1, expected1)) {
            return false;
        }

        // Scenario 2: multiple ways to make change using same optimal number of coins
        int[] expected2 = new int[] {1, 0, 0, 1}; // either {1, 0, 0, 1} or {0, 1, 1, 0}
        int[] expectedTwo = new int[] {0, 1, 1, 0};
        int[] result2 = Changemaker.makeChange(new int[] {2, 5, 7, 10}, new int[] {1, 1, 1, 1}, 12);
        if (!Arrays.equals(result2, expected2) && !Arrays.equals(result2, expectedTwo)) {
            return false;
        }

        // Scenario 3: non-greedy optimal way to make change
        int[] expected3 = new int[] {0, 1, 1, 0};
        int[] result3 = Changemaker.makeChange(new int[] {1, 5, 6, 9}, new int[] {2, 1, 1, 1}, 11);
        if (!Arrays.equals(result3, expected3)) {
            return false;
        }

        // All scenarios passed
        return true;
    }
}
