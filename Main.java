import java.util.Scanner;
import java.util.Arrays;

public class Main {    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== Sorting Algorithm Program ===");
            System.out.println("1. Enter new numbers to sort");
            System.out.println("2. Exit program");
            
            int mainChoice = getValidIntInput(scanner, 1, 2);
            
            if (mainChoice == 2) {
                System.out.println("Program has exited");
                break;
            }
            
            // Get numbers from user
            int[] numbers = getNumbersFromUser(scanner);
            if (numbers == null) continue;
            
            // Show sorting algorithm options
            // FUTURE1: Add new algorithms here
            while (true) {
                System.out.println("\nOriginal array: " + Arrays.toString(numbers));
                System.out.println("\nAvailable Sorting Algorithms:");
                System.out.println("1. Bubble Sort");
                System.out.println("2. Merge Sort");
                System.out.println("4. Return to main menu");
                
                int algorithmChoice = getValidIntInput(scanner, 1, 4);
                
                if (algorithmChoice == 4) break;
                
                // Create a copy for manipulation
                int[] numbersCopy = Arrays.copyOf(numbers, numbers.length);
                
                // Execute chosen sorting algorithm
                long startTime = System.nanoTime();
                
                // FUTURE2: Add new algorithms here
                switch (algorithmChoice) {
                    case 1:
                        SortingAlgorithms.bubbleSort(numbersCopy);
                        displaySortingResults("Bubble Sort", numbersCopy, startTime);
                        break;
                    case 2:
                        SortingAlgorithms.mergeSort(numbersCopy, 0, numbersCopy.length - 1);
                        displaySortingResults("Merge Sort", numbersCopy, startTime);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
        scanner.close();
    }

    private static int[] getNumbersFromUser(Scanner scanner) {
        System.out.println("\nPlease enter numbers separated by spaces: ");
        String input = scanner.nextLine();
        String[] numberStrings = input.trim().split("\\s+");
        
        try {
            int[] numbers = new int[numberStrings.length];
            for (int i = 0; i < numberStrings.length; i++) {
                numbers[i] = Integer.parseInt(numberStrings[i]);
            }
            return numbers;
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid integers only.");
            return null;
        }
    }

    private static int getValidIntInput(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) {
                    return choice;
                }
                System.out.printf("Please enter a number between %d and %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.printf("Invalid input. Please enter a number between %d and %d: ", min, max);
            }
        }
    }

    private static void displaySortingResults(String algorithmName, int[] sortedArray, long startTime) {
        long endTime = System.nanoTime();
        double timeInMilliseconds = (endTime - startTime) / 1_000_000.0;
        
        System.out.println("\nResults for " + algorithmName + ":");
        System.out.println("Sorted array: " + Arrays.toString(sortedArray));
        System.out.printf("Time taken: %.3f ms%n", timeInMilliseconds);
    }
}