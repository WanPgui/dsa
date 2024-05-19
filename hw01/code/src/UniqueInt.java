import java.io.*;
import java.util.*;

public class UniqueInt {
    public static void processFile(String inputFilePath, String outputFilePath) {
        boolean[] seen = new boolean[2047]; // range from -1023 to 1023
        int offset = 1023; // to handle negative indices

        List<Integer> uniqueIntegers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                try {
                    int num = Integer.parseInt(line);
                    int index = num + offset;
                    if (!seen[index]) {
                        seen[index] = true;
                        uniqueIntegers.add(num);
                    }
                } catch (NumberFormatException e) {
                    // Skip invalid lines
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            return;
        }

        // Convert List to array for sorting
        int[] resultArray = new int[uniqueIntegers.size()];
        for (int i = 0; i < uniqueIntegers.size(); i++) {
            resultArray[i] = uniqueIntegers.get(i);
        }

        // Sort the array using bubble sort
        bubbleSort(resultArray);

        // Write the sorted unique integers to the output file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (int num : resultArray) {
                bw.write(Integer.toString(num));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing output file: " + e.getMessage());
        }
    }

    private static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (array[i - 1] > array[i]) {
                    int temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                    swapped = true;
                }
            }
            n--; // Each pass places the next largest element in its correct position
        } while (swapped);
    }

    public static void main(String[] args) {
        processFile("../sample_inputs/sample_input_01.txt", "../sample_results/sample_input_01.txt_results.txt");
        // Add more files as needed
    }
}
