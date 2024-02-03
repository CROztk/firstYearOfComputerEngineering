
import java.util.Scanner;

public class SimpleStudentManagementSytem {

	// 2D array to store student informations.
	// student[i][j]: j=0 for student ID, j=1 for student's name, j=2 for student's phone number and j=3 for student's email.
	// Default capacity is 16 and then doubles if it is full
	static String[][] students = new String[16][4];
	static int currentLength = 0;
	static Scanner scText = new Scanner(System.in);
	
	public static void main(String[] args) {
		mainMenu();
	}
	
	public static void mainMenu()
	{
		Scanner scNum = new Scanner(System.in);
		System.out.println("----Main Menu----");
		System.out.println("Enter 1 for student list");
		System.out.println("Enter 2 to add new student");
		System.out.println("Enter 3 to search a student");
		System.out.println("Enter 4 to delete a student");
		System.out.println("Enter 5 to update a student's info");
		System.out.println("Enter 0 to quit");
		System.out.print("Selection: ");
		int selection = scNum.nextInt();
		switch (selection) {
		// Quit
		case 0:
			scNum.close();
			break;
		// Display student list
		case 1:
			displayStudents();
			break;
		// Add new student
		case 2:
			addStudent();
			break;
		
		// Search a student
		case 3:
			searchStudent();
			break;
			
		// Delete a student
		case 4:
			deleteStudent();
			break;
		// Update student's info
		case 5:
			updateStudentsInfo();
			break;
		default:
			break;
		}
	}
	
	static void printStudentIndexN(int n)
	{
		System.out.print(students[n][0] + " | ");
		System.out.print(students[n][1] + " | ");
		System.out.print(students[n][2] + " | ");
		System.out.println(students[n][3]);
	}
	
	public static void displayStudents()
	{
		System.out.println("\n----Students----");
		for (int i = 0; i < currentLength; i++) {
			printStudentIndexN(i);
		}
		System.out.println("There is/are " + currentLength + " student(s).\n");
		System.out.print("Press enter to return main menu...");
		scText.nextLine();
		System.out.println();
		mainMenu();
	}
	
	public static void addStudent()
	{
		// Double the array's capacity if it is full
		if(currentLength == students.length)
		{
			doubleCapacity();
		}
		// Take student ID from user
		String id = takeUniqueIdFromUser();
		
		// Take student's name from user
		scText.nextLine();
		System.out.print("Enter student's name: ");
		String name = scText.nextLine();
		
		// Take student's phone number from user
		String phoneNumber = takeUniquePhoneNumberFromUser();
		
		// Take student's email from user
		String email = takeUniqueEmailFromUser();
		
		// Ask user to confirm new student and add it to the array if they approve
		System.out.println(id + " | " + name + " | " + phoneNumber + " | " + email);
		System.out.print("Enter y to confirm: ");
		if(scText.next().matches("[yY]"))
		{
			int studentsIndex = Math.abs(binarySearchID(id)+1);
			shiftRightAfterIndexN(studentsIndex);
			students[studentsIndex][0] = id;
			students[studentsIndex][1] = name;
			students[studentsIndex][2] = phoneNumber;
			students[studentsIndex][3] = email;
			currentLength++;
			System.out.println("Student added successfully.");
		}else 
		{
			System.out.println("Student not added.");
		}
		System.out.print("Press enter to return main menu...");
		scText.nextLine();
		System.out.println();
		mainMenu();
	}
	
	static void doubleCapacity()
	{
		String[][] doubledArray = new String[students.length*2][4];
		for (int i = 0; i < students.length; i++) {
			doubledArray[i] = students[i];
		}
		students = doubledArray;
	}
	
	static void shiftRightAfterIndexN(int n)
	{
		for (int i = currentLength; n < i; i--) {
			students[i][0] = students[i-1][0];
			students[i][1] = students[i-1][1];
			students[i][2] = students[i-1][2];
			students[i][3] = students[i-1][3];
		}
	}
	
	static void shiftLeftAfterIndexN(int n)
	{
		for (int i = n; i < currentLength; i++) {
			students[i][0] = students[i+1][0];
			students[i][1] = students[i+1][1];
			students[i][2] = students[i+1][2];
			students[i][3] = students[i+1][3];
		}
	}
	
	static int binarySearchID(String id)
	{
		int low = 0;
		int high = currentLength - 1;
		while(low <= high)
		{
			int mid = (low + high) / 2;
			int compare = compare(id, students[mid][0]);
			if(compare < 0)
				high = mid - 1;
			else if(compare > 0)
				low = mid + 1;
			else
				return mid;
		}
		return -1 - low;
	}
	
	static int compare(String str1, String str2)
	{
		for (int i = 0; i < (str1.length() < str2.length() ? str1.length() : str2.length()); i++) {
			if( str1.charAt(i) < str2.charAt(i) )
				return -1;
			else if( str1.charAt(i) > str2.charAt(i))
				return 1;
		}
		return 0;
	}
	
	static String takeValidIdFromUser()
	{
		System.out.print("Please enter 11 digits student ID number: ");
		String id = scText.next();
		while(!id.matches("\\d{11}"))
		{
			System.out.println("Student ID must only contain 11 digits!");
			System.out.print("Please enter student ID again: ");
			id = scText.next();
		}
		return id;
	}
	
	static String takeUniqueIdFromUser()
	{
		String id = takeValidIdFromUser();
		while(binarySearchID(id) >= 0)
		{
			System.out.println("Student ID already exists!");
			id = takeValidIdFromUser();
		}
		return id;
	}
	
	// index 2 for searching phone number and 3 for  searching email
	static int linearSearch(String key, int index)
	{
		for (int i = 0; i < currentLength; i++) {
			if(students[i][index].equals(key))
				return i;
		}
		return -1;
	}
	
	static int linearSearchPhoneNumber(String phoneNumber)
	{
		return linearSearch(phoneNumber, 2);
	}
	
	static String takeValidPhoneNumberFromUser()
	{
		System.out.print("Please enter student's phone number: ");
		String phoneNumber = scText.next();
		while(!phoneNumber.matches("\\+?\\d*"))
		{
			System.out.println("Phone number must contain only digits with or without starting with '+' sign ");
			System.out.print("Please enter student's phone number again: ");
			phoneNumber = scText.next();
		}
		return phoneNumber;
	}
	
	static String takeUniquePhoneNumberFromUser()
	{
		String phoneNumber = takeValidPhoneNumberFromUser();
		while(linearSearchPhoneNumber(phoneNumber) != -1)
		{
			System.out.println("Phone number already exists!");
			phoneNumber = takeValidPhoneNumberFromUser();
		}
		return phoneNumber;
	}
	
	static String takeValidEmailFromUser()
	{
		System.out.print("Please enter student's email: ");
		String email = scText.next();
		while (!email.matches(".+@.+\\..+")) 
		{
			System.out.println("Email is not valid! (example@example.com)");
			System.out.print("Please enter student's email again: ");
			email = scText.next();
		}
		return email;
	}
	
	static int linearSearchEmail(String email)
	{
		return linearSearch(email, 3);
	}
	
	static String takeUniqueEmailFromUser()
	{
		String email = takeValidEmailFromUser();
		while(linearSearchEmail(email) >= 0)
		{
			System.out.println("Email already exists!");
			email = takeValidEmailFromUser();
		}
		return email;
	}
	
	static void searchStudent()
	{
		System.out.print("Enter student ID, student's phone number or email: ");
		String key = scText.next();
		if(key.matches(".+@.+\\..+"))
		{
			// Search with email
			int index = linearSearchEmail(key);
			if(index >= 0)
			{
				// Student found
				System.out.println("Student found: ");
				printStudentIndexN(index);
			}else 
			{
				// Student not found
				System.out.println("Student not found!");
			}
			
		}else if(key.matches("\\+\\d*"))
		{
			// Search with phone number
			int index = linearSearchPhoneNumber(key);
			if(index >= 0)
			{
				// Student found
				System.out.println("Student found: ");
				printStudentIndexN(index);
			}else 
			{
				// Student not found
				System.out.println("Student not found!");
			}
			
		}else if(key.matches("\\d+"))
		{
			// Key might be a phone number or a student id
			// Search with phone number
			int index = linearSearchPhoneNumber(key);
			if(index >= 0)
			{
				// Student found
				System.out.println("Student found: ");
				printStudentIndexN(index);
			}else 
			{
				// Search with id number
				searchStudentWithId(key);
				
			}
			
		}else 
		{
			// invalid input type
			System.out.println("Please enter a valid phone number, email or student ID!");
		}
		mainMenu();
	}
	
	static void searchStudentWithId(String id)
	{
		if(id.length() == 11)
		{
			// try to find student
			int index = binarySearchID(id);
			if(index >= 0)
			{
				// Student found
				System.out.println("Student found: ");
				printStudentIndexN(index);
				
			}else
			{
				// Possible student found
				System.out.println("Student not found!");
				System.out.println("Possible student is: ");
				printStudentIndexN(Math.abs(index+1));
			}
		}else if(id.length() < 11)
		{
			// try to find students whose id's starts with given key
			int index = binarySearchID(id);
			if(index >= 0){
				for (int i = index; i >= 0; i--) {
					if (compare(id, students[i][0]) != 0) {
						// Start index for students whose id's starts with given key
						index = i + 1;
						break;
					}
				}
				
				System.out.println("Students whose ID is start with "+id+" :");
				for (int i = index; i < currentLength; i++) {
					if (compare(id, students[i][0]) != 0)
					{
						break;
					}
					printStudentIndexN(i);
				}
			}else 
			{
				// Student not found
				System.out.println("There is no any student whose ID starts with "+id+" !");
			}
		}else
		{
			// Given key cannot be longer than 11 digits
			System.out.println("Student ID cannot be longer than 11 digits!");
		}
	}
	
	static void deleteStudent()
	{
		System.out.print("Enter student's ID to delete: ");
		String id = scText.next();
		if(id.length() == 11) {
			int index = binarySearchID(id);
			if(index >= 0)
			{
				// Student found
				System.out.println("Student found:");
				printStudentIndexN(index);
				System.out.print("Enter y to delete student: ");
				if(scText.next().matches("[yY]"))
				{
					shiftLeftAfterIndexN(index);
					currentLength--;
					System.out.println("Student deleted successfully.");
				}else
				{
					System.out.println("Student not deleted.");
				}
			}else
			{
				// Student not found
				System.out.println("Student not found!");
			}
		}else
		{
			System.out.println("Student ID must be 11 digits!");
		}
		mainMenu();
	}
	
	static void updateStudentsInfo()
	{
		System.out.print("Enter student's ID to update info: ");
		String id = scText.next();
		int index = binarySearchID(id);
		if(index < 0)
		{
			System.out.println("Student not found!");
			return;
		}
		System.out.println("Student found: ");
		printStudentIndexN(index);
		
		System.out.print("Enter y to update student's name: ");
		if(scText.next().matches("[yY]"))
		{
			// Change student's name
			scText.nextLine();
			System.out.print("Enter new name: ");
			String newName = scText.nextLine();
			
			System.out.print("Enter y to change " + students[index][1] + " to " + newName + ": ");
			if(scText.next().matches("[yY]"))
			{
				students[index][1] = newName;
			}
		}
		
		System.out.println("Enter y to update student's phone number: ");
		if(scText.next().matches("[yY]"))
		{
			// Change student's phone number
			System.out.print("Enter new phone number: ");
			String newPhoneNumber = scText.next();
			
			System.out.print("Enter y to change " + students[index][2] + " to " + newPhoneNumber + ": ");
			if(scText.next().matches("[yY]"))
			{
				students[index][2] = newPhoneNumber;
			}
		}
		
		System.out.println("Enter y to update student's email: ");
		if(scText.next().matches("[yY]"))
		{
			// Change student's email
			System.out.print("Enter new email: ");
			String newEmail = scText.next();
			
			System.out.print("Enter y to change " + students[index][3] + " to " + newEmail + ": ");
			if(scText.next().matches("[yY]"))
			{
				students[index][3] = newEmail;
			}
		}
	}

}

















