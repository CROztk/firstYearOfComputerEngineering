// Civan Rıdar Öztekin
import java.util.Scanner;

public class LexicographicPermutationsOfLetters {

	static void lexicographicPermutations(String text) {
		char[] chArray = text.toCharArray();
		selectionSort(chArray, 0);
		lexicographicPermutations(chArray, 0);
	}

	static void lexicographicPermutations(char[] chArray, int itemCounter) {
		if (itemCounter == Math.pow(chArray.length, chArray.length))
			return;
		printElement(chArray, itemCounter, 1);
		System.out.print(" ");
		if ((itemCounter + 1) % 10 == 0)
			System.out.println();
		lexicographicPermutations(chArray, itemCounter + 1);
	}

	static void printElement(char[] chArray, int itemCounter, int charCounter) {
		if (charCounter == chArray.length) {
			System.out.print(chArray[itemCounter % chArray.length]);
			return;
		}
		System.out.print(chArray[(int) (itemCounter / Math.pow(chArray.length, chArray.length - charCounter))]);
		itemCounter = (int) (itemCounter % Math.pow(chArray.length, chArray.length - charCounter));
		printElement(chArray, itemCounter, charCounter + 1);
	}

	static void selectionSort(char[] chArray, int startIndex) {
		if (startIndex >= chArray.length)
			return;
		int minIndex = indexOfMin(chArray, startIndex, startIndex);
		char temp = chArray[startIndex];
		chArray[startIndex] = chArray[minIndex];
		chArray[minIndex] = temp;
		selectionSort(chArray, startIndex + 1);
	}

	static int indexOfMin(char[] chArray, int startIndex, int possibleIndex) {
		if (startIndex >= chArray.length)
			return possibleIndex;
		if (chArray[startIndex] < chArray[possibleIndex])
			possibleIndex = startIndex;
		return indexOfMin(chArray, startIndex + 1, possibleIndex);
	}

	public static void main(String[] args) {
		Scanner scText = new Scanner(System.in);
		System.out.print("Enter a letters: ");
		String letters = scText.next();
		lexicographicPermutations(letters);
		scText.close();
	}

}
