package helpers;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextInputDialog;
import teaming.Student;

/**
 * helper function related with input(H) process
 */

public class InputH {
	
	/*
	 * 	Part 1: Handling input number
	 */
	/**
	 * Get the number of student that the user entered (INPUT H)
	 * @param input number of student (String)
	 * @return number of student (int)
	 */
	// 
	public static int getNumStudent(String input)
	{
		int numStudent = 0;
		if (input == null)
		{
			return 0;
		}
		try {
			numStudent = Integer.parseInt(input);
			if (numStudent >= 200 && numStudent <= 500)
			{
				return numStudent;
			}
			else
			{
				return -1;
			}
		}
		catch (Exception e){
			return -1;
		}
	}
	
	
	/*
	 * 	Part 2: Generate random data set
	 */
	/**
	 * Generate a random student data set with given number of students (INPUT H)
	 * @param n number of student to be generated
	 * @param students the generated data to be stored
	 */
	// 
	public static void generateStudents(int n, ObservableList<Student> students)
	{
		// Make sure the students array list has been initialized
		if (students == null)
		{
			students = FXCollections.observableArrayList();
		}
		
		// The fewer students, the spreading of student ID will be larger, and vice versa
		int maxIncrementForStudentID = (int) (999998/n);
		int studentID = 20000000;
		
		// Fixed ending for email address
		final String EMAIL_ENDING = "@connect.ust.hk";
		
		// ArrayList of email addresses for checking duplication
		ArrayList<String> emails = new ArrayList<String>();
		
		// Generate all n students 
		for (int i = 0; i < n; i++)
		{
			// Get the string of the index i
			String index = Integer.toString(i);
			if (i < 10)
			{
				index = "0" + index;
			}
			
			// Get the unique student ID
			int increment = (int) (Math.random()*maxIncrementForStudentID) + 1;
			studentID = studentID + increment;
			String studentID_string = Integer.toString(studentID);
			
			// Get a random name by different combinations of first name and last name
			int randomIndexForFirstName = (int) (Math.random()*260);
			int randomIndexForLastName = (int) (Math.random()*260);
			String firstName = Student.FIRSTNAMELIST[randomIndexForFirstName];
			String lastName = Student.LASTNAMELIST[randomIndexForLastName];
			String name = firstName.toUpperCase() + ", " + lastName;
			
			// Get the email by combining the last name and first 3 characters of the first name
			String email = lastName + firstName.substring(0,3) + EMAIL_ENDING;
			if (emails.contains(email))
			{
				email = lastName + firstName.substring(0,3) + studentID_string.substring(6) + index + EMAIL_ENDING;
			}
			emails.add(email);
			
			// Get the random K1 and K2 energy under normal distribution of mean = 50, sd = 16 approximately
			int K1_energy = (int) (Math.random()*11);
			float K2_multiplier = 0f;
			double lucky_num = Math.random();
			
			if (lucky_num < 0.004)
			{
				K2_multiplier = 1.2f;
			}
			else if (lucky_num >= 0.004 && lucky_num < 0.027)
			{
				K1_energy += 10;
				K2_multiplier = 1.15f;
			}
			else if (lucky_num >= 0.027 && lucky_num < 0.095)
			{
				K1_energy += 20;
				K2_multiplier = 1.1f;
			}
			else if (lucky_num >= 0.095 && lucky_num < 0.25)
			{
				K1_energy += 30;
				K2_multiplier = 1.05f;
			}
			else if (lucky_num >= 0.25 && lucky_num < 0.5)
			{
				K1_energy += 40;
				K2_multiplier = 1.0f;
			}
			else if (lucky_num >= 0.5 && lucky_num < 0.734)
			{
				K1_energy += 50;
				K2_multiplier = 1.0f;
			}
			else if (lucky_num >= 0.734 && lucky_num < 0.8944)
			{
				K1_energy += 60;
				K2_multiplier = 0.95f;
			}
			else if (lucky_num >= 0.8944 && lucky_num < 0.9696)
			{
				K1_energy += 70;
				K2_multiplier = 0.9f;
			}
			else if (lucky_num >= 0.9696 && lucky_num < 0.9938)
			{
				K1_energy += 80;
				K2_multiplier = 0.87f;
			}
			else
			{
				K1_energy += 90;
				K2_multiplier = 0.85f;
			}
			
			int K2_energy = (int) (Math.random()*11);
			lucky_num = Math.random()*0.5;
			
			if (lucky_num < 0.0062)
			{
				K2_energy += Math.random() >= 0.5 ? 90 : 0;
			}
			else if (lucky_num >= 0.0062 && lucky_num < 0.0304)
			{
				K2_energy += Math.random() < 0.5 ? 10 : 80;
			}
			else if (lucky_num >= 0.0304 && lucky_num < 0.1056)
			{
				K2_energy += Math.random() < 0.5 ? 20 : 70;
			}
			else if (lucky_num >= 0.1056 && lucky_num < 0.266)
			{
				K2_energy += Math.random() < 0.5 ? 30 : 60;
			}
			else
			{
				K2_energy += Math.random() < 0.5 ? 40 : 50;
			}
			
			float K2_mul = K2_energy * K2_multiplier;
			if (K2_mul > 100) K2_mul = 100;
			else if (K2_mul < 0) K2_mul = 0;
			
			K2_energy = (int) K2_mul;
			
			String K1_energy_string = Integer.toString(K1_energy);
			String K2_energy_string = Integer.toString(K2_energy);
			
			// Student may like to tick 1 or 2 for K3 if their sum of K1 and K2 energy is low
			// The lower their total energy, the higher the chance they will tick for K3
			// Student may want to be team leader if their sum of K1 and K2 energy is high
			// The higher their total energy, the higher the chance they want to be team leader
			int K3_tick1 = 0;
			int K3_tick2 = 0;
			int my_pref = 0;
			int totalEnergy = K1_energy+K2_energy;
			if (totalEnergy <= 115)
			{
				double probability = 1.4/(0.85+totalEnergy/100);
				if (Math.random() < probability)
				{
					double selection = Math.random();
					if (selection < 0.45)
					{
						K3_tick1 = 1;
					}
					else if (selection > 0.55)
					{
						K3_tick2 = 1;
					}
					else
					{
						K3_tick1 = 1;
						K3_tick2 = 1;
					}
				}
			}
			else
			{
				double probability = totalEnergy/200.0;
				if (Math.random() < probability)
				{
					my_pref = 1;
				}
			}
			String K3_tick1_string = Integer.toString(K3_tick1);
			String K3_tick2_string = Integer.toString(K3_tick2);
			String my_pref_string = Integer.toString(my_pref);
			
			// Assume all students have no concerns
			String my_concern = "";
			
			// Create the student object
			Student student = new Student(index, "", studentID_string, name, 
										  email, K1_energy_string, K2_energy_string,
										  K3_tick1_string, K3_tick2_string,
										  my_pref_string, my_concern);
			
			// Add the student object to the ObservableList
			students.add(student);
		}
	}
}
