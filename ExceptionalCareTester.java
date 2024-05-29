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
import java.io.File;
import java.io.IOException;
public class ExceptionalCareTester {

    /**
     * This test method is provided for you in its entirety, to give you a model for testing an
     * instantiable class. This method verifies the correctness of your PatientRecord class.
     *
     * In this test, we create two PatientRecords with different information and use the accessor
     * methods to verify that both contain the correct information and have the correct String
     * representation.
     *
     * @author hobbes
     * @return true if and only if all scenarios pass, false otherwise
     */
    public static boolean testPatientRecord() {
        // FIRST: reset the patient counter so this tester method can be run independently
        PatientRecord.resetCounter();

        // (1) create two PatientRecords with different, valid input
        // no exceptions should be thrown, so let's be safe:
        PatientRecord test1 = null, test2 = null;
        try {
            test1 = new PatientRecord('M', 17, PatientRecord.YELLOW);
            test2 = new PatientRecord('X', 21, PatientRecord.GREEN);
        } catch (Exception e) {
            return false;
        }

        // (2) verify their data fields:
        {
            // CASE_NUMBER
            int expected1 = 21701;
            int expected2 = 32102;
            if (test1.CASE_NUMBER != expected1 || test2.CASE_NUMBER != expected2)
                return false;
        }
        {
            // triage
            int expected1 = PatientRecord.YELLOW;
            int expected2 = PatientRecord.GREEN;
            if (test1.getTriage() != expected1 || test2.getTriage() != expected2)
                return false;
        }
        {
            // gender
            char expected1 = 'M';
            char expected2 = 'X';
            if (test1.getGender() != expected1 || test2.getGender() != expected2)
                return false;
        }
        {
            // age
            int expected1 = 17;
            int expected2 = 21;
            if (test1.getAge() != expected1 || test2.getAge() != expected2)
                return false;
        }
        {
            // orderOfArrival
            int expected1 = 1;
            int expected2 = 2;
            if (test1.getArrivalOrder() != expected1 || test2.getArrivalOrder() != expected2)
                return false;
        }
        {
            // hasBeenSeen - try the mutator too
            if (test1.hasBeenSeen() || test2.hasBeenSeen())
                return false;
            test1.seePatient();
            if (!test1.hasBeenSeen() || test2.hasBeenSeen())
                return false;
        }

        // (3) verify their string representations
        {
            String expected1 = "21701: 17M (YELLOW)";
            String expected2 = "32102: 21X (GREEN)";
            if (!test1.toString().equals(expected1) || !test2.toString().equals(expected2))
                return false;
        }

        // (4) finally, verify that the constructor throws an exception for an invalid triage value
        try {
            new PatientRecord('F', 4, -17);
            // if we get here, no exception was thrown and the test fails
            return false;
        } catch (IllegalArgumentException e) {
            // correct exception type, but it should have a message:
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        } catch (Exception e) {
            // incorrect exception type
            return false;
        }

        // if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
        return true;
    }

    public static boolean testAdmissionsConstructorValid() {
        PatientRecord.resetCounter();
        // (1) verify that a normal, valid-input constructor call does NOT throw an exception
        boolean noExceptionThrown = true;
        try {
            ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(5);
        } catch (Exception e) {
            noExceptionThrown = false;
        }
        if (!noExceptionThrown) {
            return false;
        }

        // (2) verify that a just-created object has size 0, is not full, has no seen patients, and
        // its toString() is an empty string
        ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(5);
        if (admissions.size() != 0 || admissions.isFull() || admissions.getNumberSeenPatients() != 0
                || !admissions.toString().equals("")) {
            return false;
        }
        return true;
    }

    public static boolean testAdmissionsConstructorError() {
        PatientRecord.resetCounter();

        try {
            // (1) verify that calling the constructor with capacity <= 0 causes an
            // IllegalArgumentException
            ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(-1);
            return false; // test failed if no exception is thrown
        } catch (IllegalArgumentException e) {
            return true; // test passed if an exception is thrown
        }
    }

    public static boolean testAddPatientValid() {

        PatientRecord.resetCounter();
        boolean result = true;

        ExceptionalCareAdmissions newadmissions = new ExceptionalCareAdmissions(3);
        // (1) add a new patient to an empty list - since you cannot use Arrays.deepEquals() here
        // anymore, verify the contents of the patientsList using ExceptionalCareAdmissions.toString()

        newadmissions.addPatient(new PatientRecord('F', 18, PatientRecord.RED), 0);

        // (2) add a new patient to the end of the list
        if (!newadmissions.toString().equals("11801: 18F (RED)")) {
            result = false;
        }
        // (3) add a new patient to the beginning of the list
       if (!newadmissions.toString().equals("11801: 18F (RED)")) {
           result = false;
       }



        return result;
    }


    /**
     * This test method is provided for you in its entirety, to give you a model for verifying a
     * method which throws exceptions. This method tests addPatient() with two different, full
     * patientsList arrays; one contains seen patients and one does not.
     *
     * We assume for the purposes of this method that the ExceptionalCareAdmissions constructor and
     * PatientRecord constructor are working properly.
     *
     * This method must NOT allow ANY exceptions to be thrown from the tested method.
     *
     * @author hobbes
     * @return true if and only if all scenarios pass, false otherwise
     */
    public static boolean testAddPatientError() {
        // FIRST: reset the patient counter so this tester method can be run independently
        PatientRecord.resetCounter();

        ////// (1) a full Admissions object that contains no seen patients

        // create a small admissions object and fill it with patients. i'm filling the list
        // in decreasing order of triage, so the addPatient() method has to do the least
        // amount of work.
        ExceptionalCareAdmissions full = new ExceptionalCareAdmissions(3);
        full.addPatient(new PatientRecord('M', 18, PatientRecord.RED), 0);
        full.addPatient(new PatientRecord('M', 5, PatientRecord.YELLOW), 1);

        // saving one patient in a local variable so we can mark them "seen" later
        PatientRecord seenPatient = new PatientRecord('F', 20, PatientRecord.GREEN);
        full.addPatient(seenPatient, 2);

        try {
            full.addPatient(new PatientRecord('F', 17, PatientRecord.GREEN), 3);
            // if we get here, no exception was thrown and the test fails
            return false;
        } catch (IllegalStateException e) {
            // this is the correct type of exception, but for this method we expect a specific
            // error message so we have one more step to verify:
            String message = e.getMessage();
            String expected = "Cannot admit new patients";
            if (!message.equals(expected))
                return false;
        } catch (Exception e) {
            // this is the incorrect exception type, and we can just fail the test now
            return false;
        }

        ////// (2) a full Admissions object that contains at least one seen patient

        // since we have a reference to the patient at index 2, we'll just mark them seen here
        seenPatient.seePatient();

        try {
            full.addPatient(new PatientRecord('F', 17, PatientRecord.GREEN), 3);
            // if we get here, no exception was thrown and the test fails
            return false;
        } catch (IllegalStateException e) {
            // this is the correct type of exception again, but we expect a different error
            // message this time:
            String message = e.getMessage();
            String expected = "cleanPatientsList()";
            if (!message.equals(expected))
                return false;
        } catch (Exception e) {
            // this is the incorrect exception type, and the test fails here
            return false;
        }

        // if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
        return true;
    }
//    public static boolean testGetIndexValid() {
//        PatientRecord.resetCounter();
//        // create an Admissions object and add a few Records to it, leaving some space
//        ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(5);
//        admissions.addPatient(new PatientRecord('F', 18, PatientRecord.RED), 0);
//
//        // (1) get the index of a PatientRecord that should go at the END
//        int expectedIndex = 2;
//        admissions.addPatient(new PatientRecord('M', 35, PatientRecord.YELLOW), 2);
//        int actualIndex = admissions.getAdmissionIndex(new PatientRecord('M', 35, PatientRecord.YELLOW));
//        System.out.println( actualIndex );
//        if (actualIndex != expectedIndex) {
//            return false;
//        }
//
//        // (2) get the index of a PatientRecord that should go in the MIDDLE
//        expectedIndex = 1;
//        admissions.addPatient(new PatientRecord('M', 25, PatientRecord.GREEN), 1);
//        actualIndex = admissions.getAdmissionIndex(new PatientRecord('M', 25, PatientRecord.GREEN));
//        if (actualIndex != expectedIndex) {
//            return false;
//        }
//
//        // (3) get the index of a PatientRecord that should go at the BEGINNING
//        expectedIndex = 0;
//        actualIndex = admissions.getAdmissionIndex(new PatientRecord('F', 18, PatientRecord.RED));
//        if (actualIndex != expectedIndex) {
//            return false;
//        }



    public static boolean testGetIndexValid() {
        PatientRecord.resetCounter();
        // create an Admissions object and add a few Records to it, leaving some space
        ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(5);
        PatientRecord patient1 = new PatientRecord('F', 30, PatientRecord.RED);
        PatientRecord patient2 = new PatientRecord('M', 45, PatientRecord.YELLOW);
        PatientRecord patient3 = new PatientRecord('F', 20, PatientRecord.GREEN);
        admissions.addPatient(patient1,0);
        admissions.addPatient(patient2,1);
        admissions.addPatient(patient3,2);
        PatientRecord record1 = patient1;
        PatientRecord record2=patient2;

        // (2) get the index of a PatientRecord that should go at the BEGINNING
        int expectedIndex = 1;
        int actualIndex = admissions.getAdmissionIndex(new PatientRecord('F', 18, PatientRecord.RED));

        if (actualIndex != expectedIndex) {
            return false;
        }
        // (1) get the index of a PatientRecord that should go at the END
         expectedIndex = 3;
        actualIndex = admissions.getAdmissionIndex(new PatientRecord('F', 18, PatientRecord.GREEN));

        if (actualIndex != expectedIndex) {
            return false;
        }



        // (3) get the index of a PatientRecord that should go in the MIDDLE
        expectedIndex = 2;
        actualIndex = admissions.getAdmissionIndex(new PatientRecord('F', 20, PatientRecord.YELLOW));
        if (actualIndex != expectedIndex) {
            return false;
      }

        return true;
    }

    public static boolean testGetIndexError() {
        PatientRecord.resetCounter();
        // create an Admissions object and add Records to it until it is full, as in testAddPatientError
        ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(2);
        admissions.addPatient(new PatientRecord('F', 18, PatientRecord.RED), 0);
        admissions.addPatient(new PatientRecord('M', 20, PatientRecord.GREEN), 1);
        // (1) verify the exception when there are no patients who have been seen in the list
        try {
            admissions.addPatient(new PatientRecord('X', 22, PatientRecord.YELLOW),2);
            return false;
        }
        catch(IllegalStateException e){
            String message = e.getMessage();
            String expected = "Cannot admit new patients";
            if(!message.equals(expected))
                return false;
        }
        catch(Exception e) {
            return false;
        }
        // (2) verify the exception when there is at least one patient who has been seen
        try {
            admissions.seePatient(11801);
            admissions.addPatient(new PatientRecord('X', 22, PatientRecord.YELLOW),2);
            return false;
        }
        catch(IllegalStateException e){
            String message = e.getMessage();
            String expected = "cleanPatientsList()";
            if(!message.equals(expected))
                return false;
        }
        catch(Exception e) {
            return false;
        }

        return true;
    }

    public static boolean testAdmissionsBasicAccessors() {
        PatientRecord.resetCounter();
        // (1) verify isFull() on a non-full and a full Admissions object
        ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(2);
        if (admissions.isFull()) {
            return false;
        }
        admissions.addPatient(new PatientRecord('F', 18, PatientRecord.GREEN),0);
        admissions.addPatient( new PatientRecord('M', 55, PatientRecord.RED),1);
        if (!admissions.isFull()) {
            return false;
        }


        // (2) verify size() before and after adding a PatientRecord
        ExceptionalCareAdmissions newadmissions = new ExceptionalCareAdmissions(2);

        if (newadmissions.size() != 0) {
            return false;
        }
        newadmissions.addPatient(new PatientRecord('M', 33, PatientRecord.YELLOW),0);
        if (newadmissions.size() != 1) {
            return false;
        }
        // (3) verify getNumberSeenPatients() before and after seeing a patient
        // (see testAddPatientError for a model of how to do this while bypassing seePatient())
        int before = admissions.getNumberSeenPatients();
        admissions.seePatient(11801);
        int after = admissions.getNumberSeenPatients();
        if (after != before + 1) {
            return false;
        }

        return true;
    }

    public static boolean testSeePatientValid() {
        PatientRecord.resetCounter();
        // create an Admissions object and add a few Records to it, saving a shallow copy of
        // at least one of the PatientRecord references
        ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(5);
        PatientRecord patient1 = new PatientRecord('F', 30, PatientRecord.GREEN);
        PatientRecord patient2 = new PatientRecord('M', 45, PatientRecord.YELLOW);
        PatientRecord patient3 = new PatientRecord('F', 20, PatientRecord.RED);
        admissions.addPatient(patient1,0);
        admissions.addPatient(patient2,1);
        admissions.addPatient(patient3,2);
        PatientRecord savedPatient = patient2;

        // (1) call seePatient() on the caseID of your saved reference and verify that its
        // hasBeenSeen() accessor return value changes
        try {
            admissions.seePatient(savedPatient.CASE_NUMBER);
        } catch (Exception e) {
            System.out.println("testSeePatientValid failed: " + e.getMessage());
            return false;
        }
        if (!savedPatient.hasBeenSeen()) {
            System.out.println("testSeePatientValid failed: hasBeenSeen() should return true after seePatient() is called");
            return false;
        }

        // (2) verify getNumberSeenPatients() before and after seeing a different patient
        int beforeSeen = admissions.getNumberSeenPatients();
        try {
            admissions.seePatient(patient1.CASE_NUMBER);
        } catch (Exception e) {
            System.out.println("testSeePatientValid failed: " + e.getMessage());
            return false;
        }
        int afterSeen = admissions.getNumberSeenPatients();
        if (afterSeen != beforeSeen + 1) {
            System.out.println("testSeePatientValid failed: getNumberSeenPatients() should return " + (beforeSeen + 1) + " after a patient is seen");
            return false;
        }

        return true;
    }


    public static boolean testSeePatientError() {
        PatientRecord.resetCounter();
        try {
            // (1) verify that seeing a caseID for a patient not in the list causes an
            // IllegalArgumentException
            ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(-1);
            return false; // test failed if no exception is thrown
        } catch (IllegalArgumentException e) {
            return true; // test passed if an exception is thrown
        }
    }


    public static boolean testGetSummary() {
        PatientRecord.resetCounter();
        // (1) choose one getSummary() test from P01; this method has not changed much

        ExceptionalCareAdmissions summary = new ExceptionalCareAdmissions(3);
        summary.addPatient(new PatientRecord('M', 18, PatientRecord.RED), 0);
        summary.addPatient(new PatientRecord('M', 5, PatientRecord.YELLOW), 1);
        PatientRecord seenPatient = new PatientRecord('F', 20, PatientRecord.GREEN);
        seenPatient.seePatient();
        summary.addPatient(seenPatient, 2);

        String actual = summary.getSummary();
        String expected = "Total number of patients: " + 3 + "\n" + "Total number seen: " + 1 + "\n"
                + "Number of RED patients: " + 1 + "\n" + "Number of YELLOW patients: " + 1 + "\n"
                + "Number of GREEN patients: " + 1 + "\n";
        if (!actual.equals(expected)) {
            System.out.println();
            System.out.println(actual);
            System.out.println(expected);
            return false;
        }

        return true;
    }

    public static boolean testCleanList() {
        PatientRecord.resetCounter();
        // create an Admissions object and add a few Records to it
        ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(5);
        admissions.addPatient(new PatientRecord('F', 18, PatientRecord.RED), 0);
        admissions.addPatient(new PatientRecord('M', 5, PatientRecord.YELLOW), 1);

        PatientRecord patient1 = new PatientRecord('X', 5, PatientRecord.YELLOW);
        PatientRecord patient2 = new PatientRecord('M', 5, PatientRecord.GREEN);

        admissions.addPatient(patient1, 2);
        admissions.addPatient(patient2, 3);
        // (1) using ExceptionalCareAdmissions.toString(), verify that a patientsList with NO seen
        // patients does not change after calling cleanPatientsList()
        String expected = admissions.toString();

        File file = new File("file.txt");

        try {
            admissions.cleanPatientsList(file);
            String actual = admissions.toString();

            if (!(expected.equals(actual))) {
                System.out.println("Expected String " + expected);
                System.out.println("Actual String " + actual);
                return false;
            }
        } catch (IOException e) {
            System.out.println("File could not be located");
        }
        // (2) call seePatient() for at least two of the records in your patientsList, and use
        // toString() to verify that they have been removed after calling cleanPatientsList()
        expected = admissions.toString();
        patient1.seePatient();
        patient2.seePatient();



        try {
            admissions.cleanPatientsList(file);
            String actual = admissions.toString();

            if (expected.equals(actual)) {
                System.out.println("Expected String " + expected);
                System.out.println("Actual String " + actual);
                return false;
            }
        } catch (IOException e) {
            System.out.println("File could not be located");
        }





        // NOTE: you do NOT need to verify file contents in this test method; please do so manually
        return true;
    }

    /**
     * Runs each of the tester methods and displays the result. Methods with two testers have their
     * output grouped for convenience; a failed test is displayed as "X" and a passed test is
     * displayed as "pass"
     *
     * @param args unused
     * @author hobbes
     */
    public static void main(String[] args) {
        System.out.println("PatientRecord: " + (testPatientRecord() ? "pass" : "X"));
        System.out
                .println("Admissions Constructor: " + (testAdmissionsConstructorValid() ? "pass" : "X")
                        + ", " + (testAdmissionsConstructorError() ? "pass" : "X"));
        System.out.println("Add Patient: " + (testAddPatientValid() ? "pass" : "X") + ", "
                + (testAddPatientError() ? "pass" : "X"));
        System.out.println("Get Admission Index: " + (testGetIndexValid() ? "pass" : "X") + ", "
                + (testGetIndexError() ? "pass" : "X"));
        System.out.println("Basic Accessors: " + (testAdmissionsBasicAccessors() ? "pass" : "X"));
        System.out.println("See Patient: " + (testSeePatientValid() ? "pass" : "X") + ", "
                + (testSeePatientError() ? "pass" : "X"));
        System.out.println("Get Summary: " + (testGetSummary() ? "pass" : "X"));
        System.out.println("Clean List: " + (testCleanList() ? "pass" : "X"));
    }

}