public class UrgentCareAdmissions {
  
  //fields to indicate the urgency level
  public static final int RED = 0;
  public static final int YELLOW = 1;
  public static final int GREEN = 2;

  /**
   * A helper method to find the correct index to insert a new patient at the given triage level.
   * This should be the index AFTER the last index currently occupied by a patient at that level.
   * 
   * For example, if the list currently contains: [RED, RED, GREEN, GREEN, GREEN, GREEN, null, null,
   * null, null] with a size of 6, a RED patient would be inserted at index 2, a YELLOW patient
   * would also be inserted at index 2, and a GREEN patient would be inserted at index 6.
   * 
   * If the list is FULL, the method should return -1 regardless of the triage value. This method
   * must not modify patientsList in any way.
   * 
   * @param triage       - the urgency level of the next patient's issue, RED YELLOW or GREEN
   * @param patientsList - the current, active list of patient records
   * @param size         - the number of patients currently in the list
   * @return the index at which the patient should be inserted into the list, or -1 if the list is
   *         full
   */
  public static int getAdmissionIndex(int triage, int[][] patientsList, int size) {
    
    int index = 0;
    
    //if the array is empty, return 0
    if (size == 0) {
      return 0;
    }
    
    //if the array is full, return -1
    if (size == patientsList.length) {
      return -1;
    }
    
    //else, get the admission index based on the assigned triage level
    else {

      //if the triage level is RED, then add the new patient at the end of list of patients with
      //the triage level of RED.
      if (triage == RED) {
        for (int i = 0; i < size; i++) {
          if (patientsList[i][2] == YELLOW) {
            index = i;
            break;
          }
         }
      }
      
      //if the triage level is YELLOW, then add the new patient at the end of the list of patients
      //with the triage level YELLOW.
      if (triage == YELLOW) {
        for (int i = 0; i < size; i++) {
          if (patientsList[i][2] == GREEN) {  
            index = i;
            break;
          }
        }
      }
      
      //if the triage level is GREEN, then add the new patient at the end of the list
      if (triage == GREEN) {
        index = size;
      }

      return index;
    }
  }

  /**
   * Adds the patient record, a three-element perfect size array of ints, to the patients list in
   * the given position. This method must maintain the ordering of the rest of the array, so any
   * patients in higher index positions must be shifted out of the way.
   * 
   * If there is no room to add a new patient to the array or the index provided is not a valid
   * index into the oversize array (for adding, valid indexes are 0 through size), the method 
   * should not modify the patientsList array in any way.
   * 
   * @param patientRecord a three-element perfect size array of ints, containing the patient's 
   * 5-digit case ID, their admission order number, and their triage level
   * @param index the index at which the patientRecord should be added to patientsList, assumed 
   * to correctly follow the requirements of getAdmissionIndex()
   * @param patientsList the current, active list of patient records
   * @param size the number of patients currently in the list
   * @return the number of patients in patientsList after this method has finished running
   */
  public static int addPatient(int[] patientRecord, int index, int[][] patientsList, int size) {
    
    // do not modify the patientsList array if there is no room or the index is not valid
    if (size == patientsList.length || index < 0 || index > size) {
      return size;
    } 
    
    // add a patient to the end of the patientsList
    else if (index == size) {
      patientsList[index] = patientRecord;
      size++;
      return size;
    }
    
    // adds the patient at the specified index in the patientsList and increases the size of
    //the list by 1
    else {
      for (int i = size - 1; i >= index; i--) {
        patientsList[i + 1] = patientsList[i];
      }
      patientsList[index] = patientRecord;
      size++;
      return size;
    }
  }

  /**
   * Removes the patient record at index 0 of the patientsList, if there is one, and updates the
   * rest of the list to maintain the oversize array in its current ordering. \
   * 
   * @param patientsList - the current, active list of patient records
   * @param size         - the number of patients currently in the list
   * @return - the number of patients in patientsList after this method has finished running
   */
  public static int removeNextPatient(int[][] patientsList, int size) {
    
    //checking if the patientsList is null/empty or not
    if (patientsList[0] != null) {
      
      //if the patientsList is not null, then remove the patient record at index 0 of the 
      //patientsList
      for (int i = 0; i < size; i++) {
        patientsList[i] = patientsList[i + 1];
        
        //for the last element in the list, assign null to it
        if (i == size - 1) {
          patientsList[i] = null;
        }
      }
      
      return size - 1;
    }
    return size;
  }

  /**
   * Finds the index of a patient given their caseID number. This method must not modify
   * patientsList in any way.
   * 
   * @param caseID       - the five-digit case number assigned to the patient record to find
   * @param patientsList - the current, active list of patient records
   * @param size         - the number of patients currently in the list
   * @return - the index of the patient record matching the given caseID number, or -1 if the list
   *         is empty or the caseID is not found
   */
  public static int getPatientIndex(int caseID, int[][] patientsList, int size) {
    
    //checks if the patientsList is null or not, if it is null, then return -1
    if (patientsList == null) {
      return -1;
    }
    
    //iterates through the patientsList array and returns the index of the element with
    //the same caseID that is being searched
    for (int i = 0; i < size; i++) {
      if (patientsList[i][0] == caseID) {
        return i;
      }
    }
    
    //if the caseID is not found, then it returns -1
    return -1;
  }

  /**
   * Finds the patient who arrived earliest still currently present in the patientsList, and returns
   * the index of their patient record within the patientsList. The arrival value is strictly
   * increasing for each new patient, so you will not need to handle the case where two values are
   * equal.
   * 
   * @param patientsList - the current, active list of patient records
   * @param size         - the number of patients currently in the list
   * @return - the index of the patient record with the smallest value in their arrival integer, or
   *         -1 if the list is empty
   */
  public static int getLongestWaitingPatientIndex(int[][] patientsList, int size) {
    
    //if the size of the patientsList is less than or equal to 0, it is invalid so return -1
    if (size <= 0) {
      return -1;
    }
    
    //declaring a new variable to keep track of the longest waiting patient
    int firstIndex = 0;
    
    //if the size of the patientsList is greater than zero, then find the index of the patient
    //that was first to arrive and return that index
    if (size > 0) {
      int firstToArrive = patientsList[0][1];


      for (int i = 1; i < size; i++) {
        int nextToArrive = patientsList[i][1];
        if (nextToArrive < firstToArrive) {
          firstIndex = i;
          firstToArrive = nextToArrive;
        }
      }
    }
    
    //return the index of the longest waiting patient
    return firstIndex;
  }

  /**
   * Creates a formatted String summary of the current state of the patientsList array, as follows:
   * 
   * for example: Total number of patients: 5 RED: 1 YELLOW: 3 GREEN: 1
   * 
   * The first line displays the current size of the array. The next three lines display counts of
   * patients at each of the three triage levels currently in the patientsList. Any or all of these
   * numbers may be 0.
   * 
   * This method must not modify the patientsList array in any way.
   * 
   * @param patientsList - the current, active list of patient records
   * @param size         - the number of patients currently in the list
   * @return - a String summarizing the patientsList as shown in this comment
   */
  public static String getSummary(int[][] patientsList, int size) {
    
    //declaring three variables to keep track of the patients with different triage levels
    int redCount = 0;
    int yellowCount = 0;
    int greenCount = 0;

    for (int i = 0; i < size; i++) {
      int triageLevel = patientsList[i][2];
      
      //if the patient's triage level is RED, then increase the red count by 1
      if (triageLevel == UrgentCareAdmissions.RED) {
        redCount++;
      }
      
      // if the patient's triage level is YELLOW, then increase the yellow count by 1
      if (triageLevel == UrgentCareAdmissions.YELLOW) {
        yellowCount++;
      }
      
      //if the patient's triage level is GREEN, then increase the green count by 1
      if (triageLevel == UrgentCareAdmissions.GREEN) {
        greenCount++;
      }
    }

    //a string with the summary of all the patients in the patientsList
    String summary = "Total number of patients: " + size + "\n" + "RED: " + redCount + "\n" + "YELLOW: " + yellowCount
        + "\n" + "GREEN: " + greenCount;
        return summary;
  }
}
