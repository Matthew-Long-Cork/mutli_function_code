
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static java.lang.Math.sqrt;

public class Main {

    /**
     * TODO: add  - quick sort & binary search
     *       test - quick sort & binary search
     */

    private static Scanner inputScanner = new Scanner(System.in);

    private static int menuChoice = 0, testNumber = 0;
    private static int arr[] = new int[]{1,5,2,7,8,9,3,6,4};
    private static String testWord= "";

    public static void main(String[] args) {

        do {
            printMenu();
            menuChoice = requestAndValidateMenuInput(menuChoice);

            switch (menuChoice) {

                case 1:
                    processAgeData();
                    break;
                case 2:
                    testWord = requestAndValidateWordInput(testWord);
                    checkIfPalindrome(testWord);
                    break;
                case 3:
                    testNumber = requestAndValidateNumberInput(testNumber);
                    checkIfPrime(testNumber);
                    break;
                case 4:
                    bubbleSort(arr);
                    break;
                case 0:
                    try {
                        System.out.println("EXITING...");
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    System.exit(0);
            }
        }while(menuChoice != 0);
    }

    /**
     * THESE ARE THE OPTIONS CALLED
     */

    private static void processAgeData() {
        // enhance for loop to process the data
        // get the valves, extract the two ages, parse to int, keep trace of longest life expectancies and averages

        String[] tempAgeElement;
        int currentFemaleAge = 0, currentMaleAge = 0, totalFemaleAge = 0, totalMaleAge = 0;
        double averageFemaleAge, averageMaleAge, averageOverAll, numOfCountries;
        // having numOfCountries as a double will auto-box the results to double

        int longestForMales = 0, longestForFemales = 0;
        String countryForMales = "", countryForFemales = "";

        // create an empty Tree Map, fill it with data, process that
        TreeMap<String, String> tMap = new TreeMap<>();
        tMap = addData(tMap);
        numOfCountries = tMap.size();

        for (Map.Entry dataElement : tMap.entrySet()) {

            tempAgeElement = dataElement.getValue().toString().split("\\|", 2);

            currentFemaleAge = Integer.parseInt(tempAgeElement[0]);
            currentMaleAge = Integer.parseInt(tempAgeElement[1]);

            if (currentMaleAge > longestForMales) {
                longestForMales = currentMaleAge;
                countryForMales = dataElement.getKey().toString();
            }
            if (currentFemaleAge > longestForFemales) {
                longestForFemales = currentFemaleAge;
                countryForFemales = dataElement.getKey().toString();
            }
            totalFemaleAge += currentFemaleAge;
            totalMaleAge += currentMaleAge;
        }

        averageFemaleAge = totalFemaleAge / numOfCountries;
        averageMaleAge = totalMaleAge / numOfCountries;
        averageOverAll = (averageFemaleAge + averageMaleAge) / 2;

        System.out.println("Results: ");
        System.out.println("Average Female life expectancy: " + String.format("%.1f", averageFemaleAge) + " years");
        System.out.println("Average Male life expectancy: " + String.format("%.1f", averageMaleAge) + " years");
        System.out.println("Average life expectancy overall: " + String.format("%.1f", averageOverAll) + " years");

        System.out.println("\n" + countryForFemales + " has the longest Female life expectancy at:" + longestForFemales
                + " years");
        System.out.println(countryForMales + " has the longest Male life expectancy at:" + longestForMales + " years");

    }

    private static void checkIfPalindrome(String testWord) {
        // reverse the String, compare their contents

        String reverseWord = "";

        for (int i = testWord.length() - 1; i >= 0; i--)
            reverseWord += testWord.charAt(i);

        if (testWord.equals(reverseWord))
            System.out.println(testWord + " is a palindrome.");
        else
            System.out.println(testWord + " is not a palindrome.");
    }

    private static void checkIfPrime(int testNumber) {
        /*
         * check if testNumber is a prime number [optimised to √N]
         *iterate through numbers looking for result of modulus 0 for non valid number
         */

        boolean isPrime = true;

        for (int i = 2; i <= sqrt(testNumber); i++) {
            if (testNumber % i == 0) {
                isPrime = false;
                break;
            }
        }

        if (isPrime)
            System.out.println(testNumber + " is a Prime number");
        else
            System.out.println(testNumber + " is not a Prime number");
    }

    private static void bubbleSort(int arr[]) {
        /*
         *For each iteration, iterate through the array, comparing [index] and [index=1],
         *if [index+1] is larger - swap. If no swap in iteration then stop as its finished!
         */

        int length = arr.length;
        int temp;
        boolean swapped;

        for(int i=0; i < length-1; i++){
            swapped = false;

            for(int k =0; k < length-1; k++){

                if(arr[k] > arr[k+1]){
                    temp = arr[k];
                    arr[k] = arr[k+1];
                    arr[k+1] = temp;
                    swapped = true;
                }
            }
            System.out.print("\nAfter iteration: ");
            for (int element: arr)
                System.out.print(element);
            if(!swapped)
                break;
        }

    }
    /**
     * THESE ARE THE FUNCTIONS OF THE MAIN
     */

    private static void printMenu() {
        System.out.println("\n\n");
        System.out.println("============================");
        System.out.println("            MENU            ");
        System.out.println("============================\n");

        System.out.println("1. Process life expectancy data.");
        System.out.println("2. Check if Palindrome.");
        System.out.println("3. Check if Prime Number.");
        System.out.println("4. Bubble sort function.");
        System.out.println("0. Exit the program");
        System.out.println("============================");
    }

    private static int requestAndValidateMenuInput(int menuChoice) {
        // Request a number input from the user and make sure it is valid before returning it

        boolean isValid = false;

        do{
            System.out.println("Please select one of the commands above (1-3):");

            if (!inputScanner.hasNextInt()) {
                System.out.println("That is not valid. Input must be a number.");
                inputScanner.next();
            } else {
                menuChoice = inputScanner.nextInt();
                if (menuChoice < 0 || menuChoice < 5)
                    isValid = true;
                else
                    System.out.println("That is not a valid number.");
            }
        }while(!isValid);

        return menuChoice;
    }

    private static String requestAndValidateWordInput(String testWord) {
        // Request text input from the user and make sure it is valid before returning it

        boolean isValid;

        do{
            isValid = true;
            System.out.println("Please enter the word to check:");

            if (inputScanner.hasNextInt()) {
                System.out.println("Numbers are not valid. Input must be a single word.");
                isValid = false;
                inputScanner.next();
            }
            else if (inputScanner.hasNext()) {
                testWord = inputScanner.next();

                for (char c : testWord.toCharArray()) {
                    if (Character.isDigit(c)) {
                        System.out.println("Numbers are not valid. Only [a-z][A-Z].");
                        isValid = false;
                        break;
                    }
                }
            }

        }while(!isValid);

        return testWord;
    }

    private static int requestAndValidateNumberInput(int testNumber) {
        // Request a number input from the user and make sure it is a value int

        boolean isValid =  false;

        do{
            System.out.println("Please enter a number:");

            if (!inputScanner.hasNextInt()) {
                System.out.println("That is not valid. Input must be a number.");
                inputScanner.next();
            } else {
                testNumber = inputScanner.nextInt();
                isValid = true;
            }
        }while(!isValid);

        return testNumber;
    }


    private static TreeMap addData(TreeMap tMap) {
        // Manually add some data to a tree map and return it

        tMap.put("Afghanistan", "62|61");
        tMap.put("Cuba", "80|77");
        tMap.put("Democratic Republic of Congo", "54|51");
        tMap.put("Iran", "76|72");
        tMap.put("Israel", "84|81");
        tMap.put("Italy", "85|80");
        tMap.put("Japan", "87|80");
        tMap.put("Latvia", "79|69");
        tMap.put("Russian Federation", "75|63");
        tMap.put("Sierra Leone", "46|46");
        tMap.put("Singapore", "85|81");
        tMap.put("South Africa", "64|57");
        tMap.put("Spain", "86|80");
        tMap.put("Switzerland", "85|81");
        tMap.put("Tonga", "70|74");
        tMap.put("Ukraine", "79|66");
        tMap.put("United Kingdom", "81|79");
        tMap.put("United States", "81|76");
        tMap.put("Venezuela", "80|72");

        return tMap;
    }
}
