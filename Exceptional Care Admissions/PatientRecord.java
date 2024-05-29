//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P04-Exceptional Care Admissions
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
 * This class models  patient's record for the admissions program
 *
 * @author Nandini Jagtiani
 * @author Ayushi Saigal
 */




import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class PatientRecord {

    // Triage levels
    public static final int RED = 0;
    public static final int YELLOW = 1;
    public static final int GREEN = 2;

    // Counts the number of patients created. Begins at 1, and advances to the next value after each
    // caseID is generated.
    private static int patientCounter = 1;

    // This patient's age.
    private int age;

    // This patient's single-character gender marker. The standard values are F=female, M=male,
    // X = nonbinary; but all other char values are also allowed.
    private char gender;

    // The generated case number associated with this patient record. Since this is a constant, we
    // have chosen to make it public and not use an accessor method.
    public final int CASE_NUMBER;

    // Whether this patient has been marked as "seen".
    private boolean hasBeenSeen;

    // The order in which this patient arrived; taken from the value of patientCounter when this
    // record was created.
    private int orderOfArrival;

    // This patient's triage level, one of [RED, YELLOW, GREEN].
    private int triage;

    /**
     * Creates a new patient record and assigns it a CASE_NUMBER using the provided information. Be
     * careful to record the orderOfArrival before creating a CASE_NUMBER, as the counter will advance
     * when the static helper method generateCaseNumber() is called.
     *
     * @param gender - a single-character representation of this patient's reported gender
     * @param age    - the age of this patient in years
     * @param triage - the age of this patient in years
     */
    public PatientRecord(char gender, int age, int triage) throws IllegalArgumentException {
        // Checking for an invalid triage value
        if (triage != RED && triage != YELLOW && triage != GREEN) {
            throw new IllegalArgumentException("The triage value you entered is not valid :(");
        }

        // Set the order of arrival to the current patient counter value
        this.orderOfArrival = patientCounter;

        // Store the patient's gender, age, and triage level
        this.gender = gender;
        this.age = age;
        this.triage = triage;

        // Generate a unique case number based on the patient's gender and age
        this.CASE_NUMBER = generateCaseNumber(gender, age);

        // Set the initial hasBeenSeen flag to false
        this.hasBeenSeen = false;
    }

    /**
     * Generates a five-digit case number for this patient using their reported gender and age.
     *
     * @param gender
     * @param age
     * @return
     */
    //helper method
    public static int generateCaseNumber(char gender, int age) {
        // Determine the first digit of the case number based on the patient's gender
        int firstDigit;
        if (gender == 'F') {
            firstDigit = 1;
        } else if (gender == 'M') {
            firstDigit = 2;
        } else if (gender == 'X') {
            firstDigit = 3;
        } else {
            firstDigit = 4;
        }

        // Get the next two digits of the case number based on the patient's age
        int nextTwoDigits = age % 100;

        // Get the last two digits of the case number based on the current patient counter value
        int lastTwoDigits = (patientCounter) % 100;

        // Increment the patientCounter value to ensure each case number is unique
        patientCounter++;

        // Combine the three digits to create a unique case number
        return (firstDigit * 10000) + (nextTwoDigits * 100) + lastTwoDigits;
    }


    /**
     * For tester class purposes only: resents PatientRecord.patientCounter to 1.
     */
    public static void resetCounter() {
        patientCounter = 1;
    }

    /**
     * Accessor method for triage
     *
     * @return - the triage level of the patient
     */
    public int getTriage() {
        return triage;
    }

    /**
     * Accessor method for gender
     *
     * @return - this PatientRecord's gender marker
     */
    public char getGender() {
        return gender;
    }

    /**
     * Accessor method for age
     *
     * @return - the age of the patient
     */
    public int getAge() {
        return age;
    }

    /**
     * Accessor method for order of arrival
     *
     * @return - this PatientRecord's orderOfArrival value
     */
    public int getArrivalOrder() {
        return orderOfArrival;
    }

    /**
     * Marks this patient as having been seen.
     */
    public void seePatient() {
        hasBeenSeen = true;
    }

    /**
     * Accessor method for hasBeenSeen
     *
     * @return - either true or false
     */
    public boolean hasBeenSeen() {
        return hasBeenSeen;
    }


    /**
     * Creates and returns a String representation of this PatientRecord using its data field values:
     */
    public String toString() {

        // Convert the triage level to a string representation
        String triageString;
        if ( triage == RED ) {
            triageString = "RED";
        } else if ( triage == YELLOW ) {
            triageString = "YELLOW";
        } else if ( triage == GREEN ) {
            triageString = "GREEN";
        } else {
            triageString = "UNKNOWN";
        }
        // Combine the fields into a single string and return it
        return CASE_NUMBER + ": " + age + gender + " (" + triageString + ")";
    }


}
