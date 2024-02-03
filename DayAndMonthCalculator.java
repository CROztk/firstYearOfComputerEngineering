//Civan Rıdar Öztekin
import java.util.Scanner;

public class DayAndMonthCalculator {

	// Method takes day as input and returns an array that stores month and day of month for leap years
	public static int[] monthAndDayCalculatorLeap(int dayInput) {
		// An array that stores how many days passed for each passed month
		final int[] DAYS_PASSED_LEAP = { 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335, 366 };
		// Empty array to store month and day
		int[] monthAndDay = new int[2];
		// Determine how many month(s) passed according to passed days and calculate day of month
		for (int i = 0; i < DAYS_PASSED_LEAP.length; i++) {
			if (dayInput <= DAYS_PASSED_LEAP[i]) {
				monthAndDay[0] = i;
				monthAndDay[1] = dayInput - DAYS_PASSED_LEAP[(i > 0 ? i - 1 : 0)];
				break;
			}
		}
		return monthAndDay;
	}

	// Method takes day as input and returns an array that stores month and day of month for non-leap years
	public static int[] monthAndDayCalculatorNonleap(int dayInput) {
		// declaring an array that stores how many days passed for each passed month
		final int[] DAYS_PASSED_NONLEAP = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365 };
		// declare empty array to store month and day
		int[] monthAndDay = new int[2];
		// determine how many month(s) passed according to passed days and calculate day of month
		for (int i = 0; i < DAYS_PASSED_NONLEAP.length; i++) {
			if (dayInput <= DAYS_PASSED_NONLEAP[i]) {
				monthAndDay[0] = i;
				monthAndDay[1] = dayInput - DAYS_PASSED_NONLEAP[(i > 0 ? i - 1 : 0)];
				break;
			}
		}
		return monthAndDay;
	}

	public static void main(String[] args) {
		Scanner scNum = new Scanner(System.in);
		// take year and day input from user
		System.out.print("Enter the year (K): ");
		int yearInput = scNum.nextInt();
		System.out.print("Enter the day (D): ");
		int dayInput = scNum.nextInt();
		// declare empty array to store month and day
		int[] monthAndDay = new int[2];
		// determine if year is a leap year
		if (yearInput <= 1918) {
			if (yearInput % 4 == 0)
				monthAndDay = monthAndDayCalculatorLeap(dayInput);
			else
				monthAndDay = monthAndDayCalculatorNonleap(dayInput);
		} else if ((yearInput % 4 == 0 && yearInput % 100 != 0) || yearInput % 400 == 0)
			monthAndDay = monthAndDayCalculatorLeap(dayInput);
		else
			monthAndDay = monthAndDayCalculatorNonleap(dayInput);
		// output date as expected form
		System.out.print((monthAndDay[1] < 10 ? "0" + monthAndDay[1] : monthAndDay[1]) + ".");
		System.out.println((monthAndDay[0] < 10 ? "0" + monthAndDay[0] : monthAndDay[0]) + "." + yearInput);
		scNum.close();
	}

}
