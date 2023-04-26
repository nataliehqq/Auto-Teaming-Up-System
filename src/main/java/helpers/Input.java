package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import teaming.Statistics;
import teaming.Student;

/**
 * Helper functions related with input process
 */

public class Input {

	/*
	 *	Part 1: File loading
	 */
	/**
	 * Set title and initial directory of the file chooser, and limit user to choose .cvs file only
	 * @param fileChooser FilerChooser object
	 */
	public static void configureFileChooser(final FileChooser fileChooser) 
	{      
        // Set the title
		fileChooser.setTitle("Open Student Data file (.csv) ...");

		// Set initial directory to be home
        fileChooser.setInitialDirectory(
            new File(System.getProperty("user.home"))
        );             
        
        // Add filter to limit file type to be .csv
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter(".csv file", "*.csv")
        );
    }
	/**
	 * Get the raw student data from the loading file
	 * @param file File
	 * @return An array list of string thats holds student raw data
	 * @throws FileNotFoundException Cannot load the file probable
	 */
	public static ArrayList<String> loadRawStudentData(File file) throws FileNotFoundException
	{
		Scanner sc = new Scanner(file);
    	
    	// Use \n as delimiter
    	sc.useDelimiter("\n");
    	
    	// Skip the first line if the file is not blank, otherwise return null
    	if (sc.hasNext())
    	{
    		sc.next();
    	}
    	else
    	{
    		sc.close();
    		return null;
    	}
    	
    	// Create an string ArrayList to hold the student raw data
    	ArrayList<String> studentRawData = new ArrayList<String>();
    	
    	// Read the data
    	while (sc.hasNext())
    	{
    		studentRawData.add(sc.next());
    	}

    	// Close the scanner
    	sc.close();
    	
    	return studentRawData;
	}

	
	/*
	 *	Part 2: Raw data processing
	 */
	/**
	 * Process the raw student data and return an array list of students
	 * @param raw Array list of student raw data
	 * @return	ObservableList list of student object
	 */

	public static ObservableList<Student> getAllStudents(ArrayList<String> raw)
	{
		// Create empty ObservableList of students
		ObservableList<Student> students = FXCollections.observableArrayList();
		
		// Ensure the raw data is not null
		if (raw == null)
		{
			return null;
		}
		else
		{
			int index = 0;
			// For each line in raw data
			for (String line : raw)
	    	{
				String[] data;
				
				// Split that line by comma
				data = line.split(",");

	    		// Store the data into variables
				String stuid;
	    		String first_name;
	    		String last_name;
	    		String name;
	    		String email;
	    		String k1_ene;
	    		String k2_ene;
	    		String tick_1;
	    		String tick_2;
				String pref;
				String conc;
				
				try {
					stuid = data[0];
		    		first_name = data[1].substring(1);
		    		last_name = data[2].substring(0, data[2].length()-1);
		    		name = first_name.concat(last_name);
		    		email = data[3];
		    		k1_ene = data[4];
		    		k2_ene = data[5];
		    		tick_1 = data[6];
		    		tick_2 = data[7];
					pref = data[8];
					conc = data[9];
				}
				catch(Exception e) {
					stuid = "";
					name = "";
					email = "";
					k1_ene = "";
					k2_ene = "";
					tick_1 = "";
					tick_2 = "";
					pref = "";
					conc = "";
				}
	    		
	    		// Check for any empty data
	    		if (stuid != "" && name != "" && email != "" && 
	    			k1_ene != "" && k2_ene != "" && tick_1 != "" &&
	    			tick_2 != "")
	    		{
	    			try 
	    			{
	    				// Check whether there are any data that do not match the expected data type
	    				Integer.parseInt(stuid);
	    				Integer.parseInt(k1_ene);
	    				Integer.parseInt(k2_ene);
	    				Integer.parseInt(tick_1);
	    				Integer.parseInt(tick_2);
	    			}
	    			catch (NumberFormatException e)
	    			{
	    				return null;
	    			}
	    			
	    			// Create new student object and append it to the students array list
	    			String stringIndex = Integer.toString(index);
	    			Student student = new Student(stringIndex,"",stuid,name,email,k1_ene,k2_ene,tick_1,tick_2,pref,conc);
	    			students.add(student);
	    			index++;
	    		}
	    		else
	    		{
	    			return null;
	    		}
	    	}
		}
		
		// Return the result if no problem in the raw data
    	return students;
	}
	
	
	/*
	 *	Part 3: Statistic generating
	 */
	
	/**
	 * Get the overall statistics of students (INPUT)
	 * @param students ObservableList of student data
	 * @param stats ObservableList of stats data
	 */
	public static void constructOverallStats(ObservableList<Student> students, ObservableList<Statistics> stats)
	{
		// Get number of students
		int amount_student = students.size();

		// Initialise the min and max of k1 energy and k2 energy
		double min_k1 = 100;
		double max_k1 = 0;
		double min_k2 = 100;
		double max_k2 = 0;
		
		// Initialise the amount of k3_tick1, k3_tick2 and my_pref
		int amount_k3_t1 = 0;
		int amount_k3_t2 = 0;
		int amount_pref = 0;
		
		// Initialise the total k1 and k2
		double total_k1 = 0;
		double total_k2 = 0;

		// For all student in students ObservableList
		for (Student student: students)
		{
			// Get student's k1 and k2 energy, and parse them into double type
			double student_k1 = Double.parseDouble(student.getK1energy());
			double student_k2 = Double.parseDouble(student.getK2energy());
			
			// Update the min and max of k1 energy and k2 energy
			if (student_k1 < min_k1)
			{
				min_k1 = student_k1;
			}
			else if (student_k1 > max_k1)
			{
				max_k1 = student_k1;
			}
			
			if (student_k2 < min_k2)
			{
				min_k2 = student_k2;
			}
			else if (student_k2 > max_k2)
			{
				max_k2 = student_k2;
			}
			
			// Add 1 to the amount of amount_k3_t1, amount_k3_t2 and amount_pref
			if (student.getK3tick1().equals("1"))
				amount_k3_t1++;
			if (student.getK3tick2().equals("1"))
				amount_k3_t2++;
			if (student.getMypreference().equals("1"))
				amount_pref++;
			
			// Add up the total k1 energy and total k2 energy
			total_k1 += student_k1;
			total_k2 += student_k2;
		}
		
		// Get the average k1 energy and average k2 energy
		double avg_k1 = total_k1/amount_student;
		double avg_k2 = total_k2/amount_student;
		
		// Construct the strings of k1 and k2 stats
		String k1_avg_min_max = new String("("+String.format("%.1f", avg_k1)+", "+String.format("%.1f", min_k1)+", "+String.format("%.1f", max_k1)+")");
		String k2_avg_min_max = new String("("+String.format("%.1f", avg_k2)+", "+String.format("%.1f", min_k2)+", "+String.format("%.1f", max_k2)+")");
		
		// Construct the statistic objects as each row in the statistics table
		Statistics total_number_of_students = new Statistics("0","Total Number of Students", Integer.toString(amount_student));
		Statistics k1_energy_stat = new Statistics("1","K1_Energy(Average, Min, Max)", k1_avg_min_max);
		Statistics k2_energy_stat = new Statistics("2","K2_Energy(Average, Min, Max)", k2_avg_min_max);
		Statistics total_k3_t1 = new Statistics("3","K3_Tick1 = 1", Integer.toString(amount_k3_t1));
		Statistics total_k3_t2 = new Statistics("4","K3_Tick2 = 1", Integer.toString(amount_k3_t2));
		Statistics total_my_pref = new Statistics("5","My_Preference = 1", Integer.toString(amount_pref));
		
		// Add these objects to the ObservableList
		stats.add(total_number_of_students);
		stats.add(k1_energy_stat);
		stats.add(k2_energy_stat);
		stats.add(total_k3_t1);
		stats.add(total_k3_t2);
		stats.add(total_my_pref);
	}
}
