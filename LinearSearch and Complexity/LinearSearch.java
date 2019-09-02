
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.List;
import java.util.Scanner;

public class LinearSearch {

	public static Random randomVariable = new Random();
	public static Scanner in = new Scanner(System.in);

	// question1
	public static ArrayList<Integer> randomList(int size) {

		ArrayList<Integer> myList = new ArrayList<Integer>(size);

		int store_randomVariable;
		myList.add(3);
		for (int i = 1; i < size; i++) {

			// will store a randomly generated number here
			store_randomVariable = 1 + randomVariable.nextInt();
			// checks if the generated number already exists in the list
			while (myList.contains(store_randomVariable)) {
				store_randomVariable = 1 + randomVariable.nextInt();
			}
			myList.add(store_randomVariable);

		}

		return myList;
	}
	// question2

	public static ArrayList<Integer> orderedList(int size) {

		ArrayList<Integer> myList = new ArrayList<Integer>(size);

		int store_randomVariable;
		myList.add(3);
		for (int i = 1; i < size; i++) {

			// will store a randomly generated number here
			store_randomVariable = 1 + randomVariable.nextInt();
			// checks if the generated number already exists in the list
			while (myList.contains(store_randomVariable)) {
				store_randomVariable = 1 + randomVariable.nextInt();
			}
			myList.add(store_randomVariable);
		}
		Collections.sort(myList);
		return myList;
	}

	// question3
	public static Integer[] userInputArray(Integer[] myArray, int size, int index, int key) {

		int search = 0;
		while (search != -1) {
			search = linearSearch(myArray, size, key);
			if (search == -1) {
				break;
			}
			int rand = 1 + randomVariable.nextInt();
			myArray[search] = rand;
		}

		myArray[index] = key;

		return myArray;
	}

	public static Integer[] userInputArray(int size, int index, int key) {

		ArrayList<Integer> genList = orderedList(size);

		Integer[] myArray = genList.toArray(new Integer[genList.size()]);
		if (genList.contains(key)) {
			int search = 0;
			while (search != -1) {
				search = linearSearch(myArray, size, key);
				if (search == -1) {
					break;
				}
				int rand = 1 + randomVariable.nextInt();
				myArray[search] = rand;
			}

		}

		myArray[index] = key;

		return myArray;
	}

	public static int linearSearch(Integer[] myArray, int n, int key) {

		int index = 0;

		while (index <= (n - 1) && myArray[index] != key) {
			index = index + 1;

		}

		if (index > n - 1) {
			index = -1;
		}

		return index;
	}

	public static long elapsedTimeLinearSearch(Integer[] myArray, int n, int key) {

		long start = System.nanoTime();
		linearSearch(myArray, n, key);
		long end = System.nanoTime();
		return end - start;
	}

	public static void main(String[] args) {

		System.out.println("input,time");
		for (int i = 10000; i < 210000; i = i + 10000) {

			ArrayList<Integer> arrlst = orderedList(i);
			Integer[] myArray = arrlst.toArray(new Integer[arrlst.size()]);

			int key = myArray[0];
			long time = elapsedTimeLinearSearch(myArray, i, key);

			System.out.println(i + "," + time);
		}

	}

}
