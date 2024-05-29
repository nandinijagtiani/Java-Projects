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
 * This class is the main class for P04-Exceptional Care Admissions
 *
 * @author Nandini Jagtiani
 * @author Ayushi Saigal
 */



import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;


public class ExceptionalCareAdmissions {
    // Data Fields
    private PatientRecord[] patientsList; // An oversize array containing the PatientRecords currently
    // active in this object

    private int size; // The number of values in the oversize array

    // Constructor
    /**
     * Creates a new, empty ExceptionalCareAdmissions object with the given capacity
     *
     * @param capacity
     * @throws IllegalArgumentException
     */
    public ExceptionalCareAdmissions(int capacity) throws IllegalArgumentException {
        // checking for exception
        if (capacity <= 0) {
            throw new IllegalArgumentException("The capacity level you entered is not valid :(");
        }

        patientsList = new PatientRecord[capacity];
        size = 0;
    }

    /**
     * An accessor method to determine if the patientsList is currently full
     *
     * @return true if the patientsList is full, false otherwise
     */
    public boolean isFull() {
        if (size == patientsList.length) {
            return true;
        }
        return false;
    }

    /**
     * Accesses the current number of patient records in the patientsList
     *
     * @return the current number of PatientRecord objects in this ExceptionalCareAdmissions object
     */
    public int size() {
        return size;
    }

    /**
     * Accesses the current number of patient records in this patientsList representing patients who
     * have already been seen (and could be removed from the list)
     *
     * @return the current count of patientRecords for which the hasBeenSeen() method returns true
     */
    public int getNumberSeenPatients() {
        int count = 0;
        // using for each loop, iterating through each element in patientsList
        for (PatientRecord record : patientsList) {
            if (record != null && record.hasBeenSeen()) {
                count++;
            }
        }
        return count;
    }

    /**
     * As before, this helper method will find the correct index to insert the new patient record into
     * patientsList, maintaining triage priority order. This method's logic is the same as that from
     * P01, but uses objects and exceptions rather than primitive values.
     *
     * This method must NOT modify this.patientsList in any way.
     *
     * @param rec the PatientRecord to be added to the list
     * @return the correct index of patientsList at which rec should be added
     * @throws IllegalStateException if the patientsList is full: - with the message
     *                               "cleanPatientsList()" if the patientsList contains any patients
     *                               who have been seen, or - with the message "Cannot admit new
     *                               patients" if there are NO seen patients in the patientsList
     */
    // helper method
    public int getAdmissionIndex(PatientRecord rec) throws IllegalStateException {

        int index = 0; // Initializing the admission index to 0

        // Checks if the patient list is already full
        if (this.size == patientsList.length) {
            // If any patients have already been seen, throw an IllegalStateException
            // using for-each loop, iterating through each element in patientsList to check if any
            // patients
            // have been seen
            for (PatientRecord record : patientsList) {
                if (record != null && record.hasBeenSeen()) {
                    throw new IllegalStateException("cleanPatientsList()");
                }
            }
            // Otherwise, throw an IllegalStateException indicating that new patients cannot be admitted
            throw new IllegalStateException("Cannot admit new patients");
        }

        else {
            int triage = rec.getTriage(); // Get the triage level of the new patient
            // If the triage level is RED, then add the new patient at the end of list of patients with
            // the triage level of RED.
            if (rec.getTriage() == PatientRecord.RED) {
                for (int i = 0; i < size; i++) {
                    if (patientsList[i].getTriage() == PatientRecord.YELLOW) {
                        index = i;
                        break;
                    }

                }
                // If the triage level is YELLOW, add the new patient at the end of the list of patients
                // with YELLOW triage level
            } else if (rec.getTriage() == PatientRecord.YELLOW) {
                for (int i = 0; i < size; i++) {
                    if (patientsList[i].getTriage() == PatientRecord.GREEN) {
                        index = i;
                        break;
                    }
                }
                // If the triage level is GREEN, add the new patient at the end of the list of patients
            } else {
                index = size;
            }

            // Return the admission index for the new patient
            return index;
        }
    }

    /**
     * Adds the provided PatientRecord at the provided position in the oversize patientsList array.
     * This method must maintain the ordering of the patientsList as before, and rather than returning
     * the new size, maintains the size field in this ExceptionalCareAdmissions object appropriately.
     *
     * @param rec   the PatientRecord to be added
     * @param index the index at which the PatientRecord should be added to patientsList, which you
     *              may assume is the same as the output of getAdmissionIndex()
     * @throws IllegalStateException    - if the patientsList is full: - with the message
     *                                  "cleanPatientsList()" if the patientsList contains any
     *                                  patients who have been seen, or - with the message "Cannot
     *                                  admit new patients" if there are NO seen patients in the
     *                                  patientsList
     * @throws IllegalArgumentException - with a descriptive error message if the patientsList is NOT
     *                                  full and the index is not a valid index into the oversize
     *                                  array
     */
    public void addPatient(PatientRecord rec, int index)
            throws IllegalStateException, IllegalArgumentException {
        // Checks if the specified index is valid
        if (index < 0 || index > this.size) {
            throw new IllegalArgumentException("Invalid index value");
        }
        // Check if the patient list is full
        if (size == patientsList.length) {
            // Checks if any patient has already been seen
            for (int i = 0; i < size; i++) {
                if (patientsList[i].hasBeenSeen()) {
                    throw new IllegalStateException("cleanPatientsList()");
                }
            }
            throw new IllegalStateException("Cannot admit new patients");
        }
        // Shift the elements in the list to make space for the new patient record
        for (int i = size; i > index; i--) {
            patientsList[i] = patientsList[i - 1];
        }
        // Add the new patient record at the specified index
        patientsList[index] = rec;
        // Increment the size of the list
        size++;
    }


    /**
     * Marks the patient with the given caseID as having been seen. This method will require you to
     * find the patient with the given caseID within the patientsList before you can modify their
     * status.
     *
     * This method may only modify one PatientRecord, and may not modify the patientsList array or its
     * size.
     *
     * @param caseID the CASE_NUMBER of the PatientRecord to be marked as having been seen
     * @throws IllegalStateException    if the patientsList is currently empty
     * @throws IllegalArgumentException if no PatientRecord with the given caseID is found
     */
    public void seePatient(int caseID) throws IllegalStateException, IllegalArgumentException {
        // throws an exception if the patient list is empty
        if (size == 0) {
            throw new IllegalStateException("The patients list is currently empty.");
        }
        boolean foundPatient = false;
        // loop through the patient list to find the patient with the given case ID
        for (PatientRecord patient : patientsList) {
            if (patient != null && patient.CASE_NUMBER == caseID) {
                // mark the patient as seen and break out of the loop
                foundPatient = true;
                patient.seePatient();
                break;
            }
        }
        // if no patient was found with the given case ID, throw an exception
        if (!foundPatient) {
            throw new IllegalArgumentException("No PatientRecord found with caseID " + caseID);
        }
    }



    /**
     * Creates a formatted String summary of the current state of the patientsList, incorporating an
     * additional component from the PatientRecord class. See below for details. Total number of
     * patients: 5 Total number seen: 3 RED: 1 YELLOW: 3 GREEN: 1
     *
     * The first line displays the current size of the array. The next displays the current number of
     * patients who have been seen already, followed by the number of patients at each triage level.
     * Any of these numbers may be 0.
     *
     * This method must not modify the patientsList array or its size in any way.
     *
     * @return a String summarizing the patientsList as shown in this comment
     */
    public String getSummary() {
        int totalPatients = patientsList.length;
        int totalSeen = 0;
        int redCount = 0;
        int yellowCount = 0;
        int greenCount = 0;

        // loop through the patient list to count the number of patients seen and the number of patients
        // in each triage level
        for (int i = 0; i < this.size; i++) {
            if (patientsList[i].hasBeenSeen()) {
                totalSeen++;
            }
            if (patientsList[i].getTriage() == PatientRecord.RED) {
                redCount++;
            } else if (patientsList[i].getTriage() == PatientRecord.YELLOW) {
                yellowCount++;
            } else if (patientsList[i].getTriage() == PatientRecord.GREEN) {
                greenCount++;
            }
        }

        // generates a summary string and return it
        String summary =
                "Total number of patients: " + totalPatients + "\n" + "Total number seen: " + totalSeen
                        + "\n" + "Number of RED patients: " + redCount + "\n" + "Number of YELLOW patients: "
                        + yellowCount + "\n" + "Number of GREEN patients: " + greenCount + "\n";
        return summary;
    }

    /**
     * This method runs occasionally to record the current state of the patientsList and save any
     * records for seen patients to a file, while removing them from the patientsList to make more
     * room.
     *
     * Every output file begins with the current summary of the patientsList, followed by the string
     * representation of each SEEN patient on a single line (PatientRecord's toString() method will be
     * helpful here). The patient records do not need to be in any particular order. If there are NO
     * seen patients when this method is called, the file will contain only the patientsList summary
     * (with "Total number seen: 0" as a part of it). If the provided file cannot be written to, do
     * not modify the patientsList in any way and just return from the method.
     *
     * By the end of this method, all SEEN patients should be recorded in the file and removed from
     * the patientsList array, and the array's size should be updated accordingly.
     *
     * For an example of how this method works, please refer to page 6 of the write-up.
     *
     * @param file the file object to use for recording the data
     */
    public void cleanPatientsList(File file) throws IOException {
        try {
            // Create a PrintWriter object to write to the specified file.
            PrintWriter printer = new PrintWriter(file);

            // Iterate through the patients list and write the information of all seen patients to the
            // file.
            for (int i = 0; i < size; i++) {
                if (patientsList[i] != null) {
                    if (patientsList[i].hasBeenSeen()) {
                        String seenPatientString = patientsList[i].toString();
                        printer.println(seenPatientString);
                        patientsList[i] = null;
                        size--;
                    }
                }
            }
            // Close the PrintWriter object.
            printer.close();
        } catch (IOException e) {
            // If there is an error while writing to the file, print an error message.
            System.out.println("Exception " + e.getMessage());
        }
        // Iterate through the patients list and shift all remaining patients to the beginning of the
        // list, setting the remaining empty slots to null.
        for (int i = 0; i < size; i++) {
            int indexNotNull = 0;
            if (patientsList[i] == null) {
                for (int j = i + 1; j < size - 1; j++) {
                    if (patientsList[j] != null) {
                        indexNotNull = j;
                        break;
                    }
                }
                patientsList[i] = patientsList[indexNotNull];
                patientsList[indexNotNull] = null;
            }
        }

    }


    /**
     * For testing purposes: this method creates and returns a string representation of the
     * patientsList, as the in-order string representation of each patient in the list on a separate
     * line. If patientsList is empty, returns an empty string.
     *
     * @return a string representation of the contents of patientsList
     */
    @Override
    public String toString() {
        String returnValue = "";
        for (PatientRecord r : patientsList) {
            // concatenates the string representation of a PatientRecord object to the returnValue string
            returnValue += (r != null) ? r.toString() + "\n" : "";
        }
        // removes leading and trailing whitespaces from the returnValue string and returns it
        return returnValue.trim();
    }

}
