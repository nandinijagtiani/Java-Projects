import java.util.Arrays; // consider using Arrays.deepEquals() to test the contents of a 2D array

// Javadoc style class header comes here
public class UrgentCareAdmissionsTester {

  /**
   * This test method is provided for you in its entirety, to give you a model for the rest of this
   * class. This method tests the getAdmissionIndex() method on a non-empty, non-full array of
   * patient records which we create and maintain here.
   * 
   * This method tests three scenarios:
   * 
   * 1. Adding a patient with a HIGHER triage priority than any currently present in the array. To
   * do this, we create an array with no RED priority patients and get the index to add a RED
   * priority patient. We expect this to be 0, so if we get any other value, the test fails.
   * 
   * 2. Adding a patient with a LOWER triage priority than any currently present in the array. To do
   * this, we create an array with no GREEN priority patients and get the index to add a GREEN
   * priority patient. We expect this to be the current size of the oversize array, so if we get any
   * other value, the test fails.
   * 
   * 3. Adding a patient with the SAME triage priority as existing patients. New patients at the
   * same priority should be added AFTER any existing patients. We test this for all three triage
   * levels on an array containing patients at all three levels.
   * 
   * @author hobbes
   * @return true if and only if all test scenarios pass, false otherwise
   */
  public static boolean testGetIndex() {

    // The non-empty, non-full oversize arrays to use in this test.
    // Note that we're using the UrgentCareAdmissions named constants to create these test records,
    // rather than their corresponding literal int values.
    // This way if the numbers were to change in UrgentCareAdmissions, our test will still be valid.
    int[][] patientsListAllLevels =
        new int[][] {{32702, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.YELLOW},
            {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW},
            {31501, 1, UrgentCareAdmissions.GREEN}, null, null, null};
    int allLevelsSize = 5;

    int[][] patientsListOnlyYellow = new int[][] {{21801, 2, UrgentCareAdmissions.YELLOW},
        {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW}, null,
        null, null, null, null};
    int onlyYellowSize = 3;

    // scenario 1: add a patient with a higher priority than any existing patient
    {
      int expected = 0;
      int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED,
          patientsListOnlyYellow, onlyYellowSize);
      if (expected != actual)
        return false;
    }

    // scenario 2: add a patient with a lower priority than any existing patient
    {
      int expected = onlyYellowSize;
      int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN,
          patientsListOnlyYellow, onlyYellowSize);
      if (expected != actual)
        return false;
    }

    // scenario 3: verify that a patient with the same priority as existing patients gets
    // added after all of those patients
    {
      int expected = 1;
      int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED,
          patientsListAllLevels, allLevelsSize);
      if (expected != actual)
        return false;

      expected = 4;
      actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.YELLOW,
          patientsListAllLevels, allLevelsSize);
      if (expected != actual)
        return false;

      expected = allLevelsSize;
      actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN,
          patientsListAllLevels, allLevelsSize);
      if (expected != actual)
        return false;
    }

    // and finally, verify that the arrays were not changed at all
    {
      int[][] allLevelsCopy = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}, null,
          null, null};
      if (!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy))
        return false;

      int[][] onlyYellowCopy = new int[][] {{21801, 2, UrgentCareAdmissions.YELLOW},
          {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW}, null,
          null, null, null, null};
      if (!Arrays.deepEquals(patientsListOnlyYellow, onlyYellowCopy))
        return false;
    }

    return true;
  }

  // Tests the behavior of the addPatient method using a non-empty, non-full array. Each test
  // should verify that the returned size is as expected and that the array has been updated
  // (or not) appropriately
  public static boolean testAddPatient() {

    int[][] patientsListAllLevels =
        new int[][] {{32702, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.YELLOW},
            {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW},
            {31501, 1, UrgentCareAdmissions.GREEN}, null, null, null};

    int allLevelsSize = 5;

    int[] patientrecord1 = new int[] {34001, 3, UrgentCareAdmissions.GREEN};
    int[] patientrecord2 = new int[] {34002, 4, UrgentCareAdmissions.YELLOW};
    int[] patientrecord3 = new int[] {34003, 5, UrgentCareAdmissions.YELLOW};
    int length = 8;


    // (1) add a patient to the END of the patientsList
    {
      int expectedSize = 6;
      int actualSize = UrgentCareAdmissions.addPatient(patientrecord1, 5, patientsListAllLevels, 5);
      if (expectedSize != actualSize) {
        return false;
      }
    }


    // (2) add a patient to the MIDDLE of the patientsList
    {
      int expectedSize = 7;
      int actualSize = UrgentCareAdmissions.addPatient(patientrecord2, 3, patientsListAllLevels, 6);
      if (expectedSize != actualSize) {
        return false;
      }
    }

    // (3) add a patient using an invalid (out-of-bounds) index
    {
      int expectedSize = 7;
      int actualSize = UrgentCareAdmissions.addPatient(patientrecord3, 8, patientsListAllLevels, 7);
      if (expectedSize != actualSize) {
        return false;
      }
    }

    return true;
  }

  // Tests the behavior of the removeNextPatient method using a non-empty, non-full array. Each
  // test should verify that the returned size is as expected and that the array has been updated
  // (or not) appropriately
  public static boolean testRemovePatient() {

    int[][] patientsListAllLevels =
        new int[][] {{32702, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.YELLOW},
            {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW},
            {31501, 1, UrgentCareAdmissions.GREEN}, null, null, null};

    int allLevelsSize = 5;

    // (1) remove a patient from a patientsList containing more than one record
    {
      int expectedSize = 4;
      int actualSize = UrgentCareAdmissions.removeNextPatient(patientsListAllLevels, 5);
      if (actualSize != expectedSize) {
        return false;
      }
    }

    // (2) remove a patient from a patientsList containing only one record

    int[][] onePatientList = new int[][] {{12345, 1, UrgentCareAdmissions.RED}};

    int onePatientSize = 1;

    {
      int expectedSize = 0;
      int actualSize = UrgentCareAdmissions.removeNextPatient(patientsListAllLevels, 1);
    }

    return true;
  }

  // Tests the behavior of the getPatientIndex method using a non-empty, non-full array.
  public static boolean testGetPatientIndex() {
    
    // (1) look for a patient at the end of the list
    {
      int[][] patientsList = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}, null,
          null, null};
      if (UrgentCareAdmissions.getPatientIndex(31501, patientsList, 5) != 4) {
        return false;
      }
    }

    // (2) look for a patient in the middle of the list
    {
      int[][] patientsList = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}, null,
          null, null};
      if (UrgentCareAdmissions.getPatientIndex(22002, patientsList, 5) != 2) {
        return false;
      }
    }

    // (3) look for a patient not present in the list
    {
      int[][] patientsList = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}, null,
          null, null};
      if (UrgentCareAdmissions.getPatientIndex(12345, patientsList, 5) != -1) {
        return false;
      }
    }
    return true;
  }

  // Tests the getLongestWaitingPatientIndex method using a non-empty, non-full array. When
  // designing these tests, recall that arrivalOrder values will all be unique!
  public static boolean testLongestWaitingPatient() {

    int[][] patientsListAllLevels =
        new int[][] {{32702, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.YELLOW},
            {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW},
            {31501, 1, UrgentCareAdmissions.GREEN}, null, null, null};

    int allLevelsSize = 5;

    int[][] onePatientList = new int[][] {{12345, 1, UrgentCareAdmissions.RED}};

    int onePatientSize = 1;

    // (1) call the method on a patientsList with only one patient
    {
      int expectedSize = 0;
      int actualSize = UrgentCareAdmissions.getLongestWaitingPatientIndex(onePatientList, 1);
      if (actualSize != expectedSize) {
        return false;
      }
    }

    // (2) call the method on a patientsList with at least three patients
    {
      int expectedSize = 4;
      int actualSize = UrgentCareAdmissions.getLongestWaitingPatientIndex(patientsListAllLevels, 5);
      if (actualSize != expectedSize) {
        return false;
      }
    }
    return true;
  }

  // Tests the edge case behavior of the UrgentCareAdmissions methods using empty and full arrays
  public static boolean testEmptyAndFullArrays() {

    int[][] patientsListAllLevels =
        new int[][] {{32702, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.YELLOW},
            {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW},
            {31501, 1, UrgentCareAdmissions.GREEN}, null, null, null};

    int allLevelsSize = 5;

    int[][] testEmptyandFullList = new int[0][0];

    int[] patientrecord1 = new int[] {34001, 3, UrgentCareAdmissions.GREEN};


    // (1) test getAdmissionIndex using an empty patientsList array and any triage level
    {
      int expectedSize = 0;
      int actualSize = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED, testEmptyandFullList, 0);
      if (actualSize != expectedSize) {
        //System.out.println(1);
        return false;
      }
    }

    // (2) test getAdmissionIndex using a full patientsList array and any triage level
    {
      int expectedSize = 1;
      int actualSize = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED, patientsListAllLevels, 5);
      if (actualSize != expectedSize) {
        //System.out.println(2);
        return false;
      }
    }

    // (3) test addPatient using a full patientsList array
    {
      int expectedSize = 6;
      int actualSize = UrgentCareAdmissions.addPatient(patientrecord1, 5, patientsListAllLevels, 5);
      if (actualSize != expectedSize) {
        //System.out.println(3);
        return false;
      }
    }

    // (4) test removeNextPatient using an empty patientsList array
    {
      int expectedSize = 5;
      int actualSize = UrgentCareAdmissions.removeNextPatient(patientsListAllLevels, 6);
      if (actualSize != expectedSize) {
        //System.out.println(4);
        return false;
      }
    }

    // (5) test getPatientIndex using an empty patientsList array
    {
      int expectedSize = -1;
      int actualSize = UrgentCareAdmissions.getPatientIndex(32702, testEmptyandFullList, 0);
      if (actualSize != expectedSize) {
        //System.out.println(5);
        return false;
      }
    }

    // (6) test getLongestWaitingPatientIndex using an empty patientsList array
    {
      int expectedSize = -1;
      int actualSize = UrgentCareAdmissions.getLongestWaitingPatientIndex(testEmptyandFullList, 0);
      if (actualSize != expectedSize) {
        //System.out.println(6);
        return false;
      }
    }
    return true;
  }

  // Tests the getSummary method using arrays in various states
  public static boolean testGetSummary() {
    
    int[][] patientsListAllLevels =
        new int[][] {{32702, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.YELLOW},
            {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW},
            {31501, 1, UrgentCareAdmissions.GREEN}, null, null, null};
            
    int allLevelsSize = 5;
    
    int[][] patientsListAllTriagesLevels = 
        new int [][] {{32702, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.YELLOW},
      {31501, 1, UrgentCareAdmissions.GREEN}, null, null, null};
      
    int triagesSize = 3;
    int[][] newList = new int [0][0];
    
    // (1) test getSummary using an empty patientsList
    {
      String actualOutput = String.format("Total number of patients: %d\nRED: %d\nYELLOW: %d\nGREEN: %d", 0, 0, 0, 0);
      String expectedOutput = UrgentCareAdmissions.getSummary(newList, 0);
     
      if (!actualOutput.equals(expectedOutput)) {
        
        return false;
        }
    }
    
    // (2) test getSummary using a patientsList with multiple patients at a single triage level
      {
        newList = new int [][] {{32702, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.RED},
          {31501, 1, UrgentCareAdmissions.RED}};
        String actualOutput = String.format("Total number of patients: %d\nRED: %d\nYELLOW: %d\nGREEN: %d", 3, 3, 0, 0);
        String expectedOutput = UrgentCareAdmissions.getSummary(newList, 3);
    
        if (!actualOutput.equals(expectedOutput)) {
          
          return false;
      }
        
      }

    // (3) test getSummary using a patientsList with patients at all triage levels
      {
        newList = new int [][] {{32702, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.YELLOW},
          {31501, 1, UrgentCareAdmissions.GREEN}};
        String actualOutput = String.format( "Total number of patients: %d\nRED: %d\nYELLOW: %d\nGREEN: %d", 3, 1, 1, 1);
        String expectedOutput = UrgentCareAdmissions.getSummary(newList, 3);
        
        if (!actualOutput.equals(expectedOutput)) {
          
          return false;
          }
 
      }
      
        
    return true;
  
  }

  /**
   * Runs each of the tester methods and displays their result
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("get index: " + testGetIndex());
    System.out.println("add patient: " + testAddPatient());
    System.out.println("remove patient: " + testRemovePatient());
    System.out.println("get patient: " + testGetPatientIndex());
    System.out.println("longest wait: " + testLongestWaitingPatient());
    System.out.println("empty/full: " + testEmptyAndFullArrays());
    System.out.println("get summary: " + testGetSummary());
  }

}
