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
 * This class is the main class for P06 Changemaker
 * The class Changemaker provides methods for computing the number of ways to
 * make change for a given value using a
 * set of coin denominations, as well as the minimum number of coins required to
 * make that change.
 *
 * Author: Nandini Jagtiani Author: Ayushi Saigal
 **/

public class Changemaker {

    /**
     * Number of possible ways to make change for a given
     * value with a limited number of coins
     * 
     * @param denominations-  array describes the value of each type of coin in your
     *                        register
     * @param coinsRemaining- array describes the quantity of each type of coin in
     *                        your register
     * @param value-parameter describes the total amount of change to be dispensed
     *                        to the customer
     * @returns total number of ways
     */

    public static int count(int[] denominations, int[] coinsRemaining, int value) {
        // If the value is zero, there is one way to represent it - by not using
        // any coins.
        if (value == 0) {
            return 1;
        }

        // If the value is negative or there are no denominations or coins remaining,
        // there are no ways to represent the value.
        if (value < 0 || denominations.length == 0 || coinsRemaining.length == 0) {
            return 0;
        }

        // Initialize the number of ways to represent the value to zero.
        int ways = 0;

        // Loop through all available coin denominations.
        for (int i = 0; i < denominations.length; i++) {
            // If there are coins remaining for the current denomination, try using
            // one of them to represent the remaining value.
            if (coinsRemaining[i] > 0) {
                coinsRemaining[i]--; // Reduce the number of remaining coins for the current denomination.
                ways += count(denominations, coinsRemaining, value - denominations[i]); // Recursively count the number
                                                                                        // of ways to represent the
                                                                                        // remaining value.
                coinsRemaining[i]++; // Restore the number of remaining coins for the current denomination.
            }
        }
        // Return the total number of ways to represent the value.
        return ways;
    }

    /**
     * Most optimal way to make change for a given
     * value with a limited number of coins
     * 
     * @param denominations-  array describes the value of each type of coin in your
     *                        register
     * @param coinsRemaining- array describes the quantity of each type of coin in
     *                        your register
     * @param value-parameter describes the total amount of change to be dispensed
     *                        to the customer
     * @returns most optimal way
     */

    public static int minCoins(int[] denominations, int[] coinsRemaining, int value) {

        // If the value to be represented is zero, it can be represented with zero
        // coins.
        if (value == 0) {
            return 0;
        }

        // If the value is negative or there are no denominations or coins remaining,
        // it cannot be represented.
        if (value < 0 || denominations.length == 0 || coinsRemaining.length == 0) {
            return -1;
        }

        // Initialize the minimum number of coins required to represent the value to be
        // a large value, so that any other value is guaranteed to be smaller.
        int minCoins = Integer.MAX_VALUE;

        // Loop through all available coin denominations.
        for (int i = 0; i < denominations.length; i++) {
            // If there are coins remaining for the current denomination and the
            // denomination
            // is less than or equal to the remaining value, try using one of them to
            // represent
            // the remaining value.
            if (coinsRemaining[i] > 0 && denominations[i] <= value) {
                coinsRemaining[i]--; // Reduce the number of remaining coins for the current denomination.
                int numCoins = minCoins(denominations, coinsRemaining, value - denominations[i]); // Recursively compute
                                                                                                  // the minimum number
                                                                                                  // of coins required
                                                                                                  // to represent the
                                                                                                  // remaining value.
                coinsRemaining[i]++; // Restore the number of remaining coins for the current denomination.

                // If the current denomination can be used to represent the remaining value,
                // update the minimum number of coins required.
                if (numCoins != -1) {
                    minCoins = Math.min(minCoins, numCoins + 1);
                }
            }
        }

        // If the minimum number of coins required could not be updated, it means that
        // the value
        // cannot be represented using the given denominations and coin quantities.
        if (minCoins == Integer.MAX_VALUE) {
            return -1;
        } else {
            return minCoins;
        }
    }

    /**
     * Computes an array to represent the exact number of each type of coins
     * required to make change
     * optimally
     * 
     * @param denominations-  array describes the value of each type of coin in your
     *                        register
     * @param coinsRemaining- array describes the quantity of each type of coin in
     *                        your register
     * @param value-parameter describes the total amount of change to be dispensed
     *                        to the customer
     * @returns best combination of coins found
     */

    public static int[] makeChange(int[] denominations, int[] coinsRemaining, int value) {
        if (value == 0) {
            int[] result = new int[denominations.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = 0;
            }
            return result;
        }

        if (value < 0 || denominations.length == 0 || coinsRemaining.length == 0) {
            return null;
        }

        int[] bestCoins = null;
        int bestCoinCount = Integer.MAX_VALUE;

        // iterate over all denominations
        for (int i = 0; i < denominations.length; i++) {
            // if there are remaining coins of the current denomination and it is not
            // greater than the
            // target value
            if (coinsRemaining[i] > 0 && denominations[i] <= value) {
                // decrement the count of remaining coins of the current denomination
                coinsRemaining[i]--;

                // recursively compute the optimal coins for the remaining value
                int[] coins = makeChange(denominations, coinsRemaining, value - denominations[i]);

                // increment the count of remaining coins of the current denomination
                coinsRemaining[i]++;

                // if a valid combination of coins was found
                if (coins != null) {
                    // compute the total number of coins used in the combination
                    int coinCount = 0;
                    for (int j = 0; j < coins.length; j++) {
                        coinCount += coins[j];
                    }
                    coinCount++; // add the current denomination to the count

                    // if this combination uses fewer coins than the previous best combination
                    if (coinCount < bestCoinCount) {
                        // update the best combination of coins found so far
                        bestCoinCount = coinCount;
                        bestCoins = new int[coins.length];
                        for (int j = 0; j < coins.length; j++) {
                            bestCoins[j] = coins[j];
                        }
                        bestCoins[i]++;
                    }
                }
            }
        }

        // return the best combination of coins found
        return bestCoins;
    }
}
