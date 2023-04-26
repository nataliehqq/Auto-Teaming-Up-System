package helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;

import controllers.InstructorMainMenuController;
import controllers.StudentRunningController;
import java.util.Comparator;
import java.util.Collections;
import teaming.Student;
import teaming.Team;

/**
 * Helper function related with output process
 */
public class Output {
    
	/*
	 * 	Part 1: Graph generating
	 */
	/**
     * Get team energy by group index
     * @param groupIndex group index
     * @param source_tm the source of team
     * @return double list of {Avg k1 energy, Avg k2 energy, (Avg k1 +Avg k2)/2 }
     */
	public static double[] getTeamEnergyDouble(int groupIndex, List<Team> source_tm){		
        Team inquiryTeam = new Team();
        List<Student> stu = new ArrayList<>(); 
        //Get all the team member info
        // inquiryTeam = InstructorMainMenuController.teams.get(groupIndex);
        try {
	        inquiryTeam = source_tm.get(groupIndex);
	        
	        stu = inquiryTeam.getMember();
	
	        double numOfStu = stu.size();
	        double k1 = 0;
	        double k2 = 0;
	        for (int i = 0; i < numOfStu; i ++){
	            k1 += Double. parseDouble(stu.get(i).getK1energy());
	            k2 += Double. parseDouble(stu.get(i).getK2energy());
	        }
	        Double k1Return = k1/numOfStu;
	        Double k2Return = k2/numOfStu;
	        double[] k1k2 = {k1Return, k2Return, (k1Return+k2Return)/2};
	        return k1k2;
        }catch (Exception e) {
        	double[] falseReturn = {-1, -1, -1};
        	return falseReturn;
        }
    }
	

    /*
     *	Part 2: Student enquire handling
     */
    
    // Part 2.1: Input format validation
    /**
     * Validate the input format of student itsc and student id
     * @param s_itsc itsc string
     * @param s_id  student id string
     * @return True: Both correct format, False: Wrong format
     */
    public static boolean formatValidation(String s_itsc, String s_id){
        System.out.println(s_itsc);
        System.out.println(s_id);
        //if empty then return
        if (s_itsc.isEmpty() || s_id.isEmpty()){
            System.out.println("Cell is Empty");
            return false;
        }

        if (!onlyString(s_itsc)){
            System.out.println("Invalid Student itsc");
            return false;
        }
        if (!onlyDigit(s_id)){
            System.out.println("Invalid Student ID");
            return false;
        }
        System.out.println("The input format is correct");
        return true;
    }
    
    /**
     * Check if the only digit
     * @param ipt string to be validate
     * @return True: only digit, False: not only digit
     */
    public static boolean onlyDigit(String ipt){
        
        //check if exist any non digit character
        Pattern pattern = Pattern.compile("[\\D]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ipt);

        if(!matcher.find()) {
            // System.out.println("valid");
            return true;
          } else {
            // System.out.println("invalid");
            return false;
          }
    }

    /**
     * Check if the only character
     * @param ipt string to be validate
     * @return True: only contains characters, False: not only contains characters
     */
    public static boolean onlyString(String ipt){
        //check if exist any character that is not a-z A-Z
        // as itsc only contains pure english character
        Pattern pattern = Pattern.compile("[^a-zA-Z]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ipt);

        if(!matcher.find()) {
            // System.out.println("valid");
            return true;
          } else {
            // System.out.println("invalid");
            return false;
          }
    }
    
    // Part 2.2: Student existence validation
    /**
     * Get student name and team index by the student itsc and student id 
     * @param s_itsc student itsc
     * @param s_id student id
     * @param studentList the source of student list
     * @return String[2] returnValue    returnValue[0] = student name   returnValue[0] = team index ("-1" means does not exist)
     */
    public static String[] studentExist(String s_itsc, String s_id, ObservableList<Student> studentList){
//        int numOfStu = InstructorMainMenuController.students.size();
        int numOfStu = studentList.size();

    	String[] returnValue = {"-1", "-1"};
        for (int i = 0; i < numOfStu; i++){
            
//            String itsc = extractMailName(InstructorMainMenuController.students.get(i).getEmail());
            String itsc = extractMailName(studentList.get(i).getEmail());

            // System.out.printf("%s \t %s \t %s \t %s\n", itsc, s_itsc, InstructorMainMenuController.students.get(i).getStudentid(), s_id);
//            if (itsc.equalsIgnoreCase(s_itsc) &&
//                InstructorMainMenuController.students.get(i).getStudentid().equalsIgnoreCase(s_id.trim())){
//                    System.out.println("Student exist");
//                    System.out.println(InstructorMainMenuController.students.get(i).getTeam());
//                    returnValue[0] = InstructorMainMenuController.students.get(i).getStudentname();
//                    returnValue[1] = InstructorMainMenuController.students.get(i).getTeam();
//                    return returnValue;                 
//                }
            if (itsc.equalsIgnoreCase(s_itsc) &&
                    studentList.get(i).getStudentid().equalsIgnoreCase(s_id.trim())){
                        System.out.println("Student exist");
                        System.out.println(studentList.get(i).getTeam());
                        returnValue[0] = studentList.get(i).getStudentname();
                        returnValue[1] = studentList.get(i).getTeam();
                        return returnValue;                 
                    }
        }
        return returnValue;
    }
    /**
     * remove @connect.ust.hk from an email
     * @param mail email to be remove @connect.ust.hk
     * @return  string before "@"
     */
    public static String extractMailName(String mail){
        // only takes string before "@"
        String[] parts = mail.split("@");
        String part1 = parts[0];
        return part1;
    }
    
    // Part 2.3: Initialise enquired information

    /**
     * Used to initialize the value in the student inquire scene
     * @param itsc student itsc
     * @param sid student id
     * @param stuName student name
     * @param team_name team name
     * @param srcList the source of team
     */
    public static void initInfo(String itsc,
        String sid, 
        String stuName,
        String team_name,
        List<Team> srcList){
    	
		StudentRunningController.teamMember[0] = "-";
		StudentRunningController.teamMember[1] = "-";
		StudentRunningController.teamMember[2] = "-";
		StudentRunningController.ipt_itsc = itsc;
		StudentRunningController.ipt_stuName = stuName;
		StudentRunningController.ipt_stuID = sid;
		StudentRunningController.teamNo = team_name;
		
		// int team_index = getTeamIndex(team_name);
		int team_index = Integer.parseInt(team_name)-1;
		
		List<String> name_list = new ArrayList<>(getTeamMember(team_index, stuName, srcList));
		// System.out.println(name_list.size());
		for (int i = 0; i < name_list.size(); i ++){
		// System.out.println(name_list.get(i));
			StudentRunningController.teamMember[i] = name_list.get(i);
		}
		StudentRunningController.k1k2 = getTeamEnergy(team_index,srcList);
    }
    
    /**
     * Get team member of the student by team index and student name
     * @param index team index
     * @param ipt_stuName student name
     * @param teamList the source of team list
     * @return List of team member name
     */
    public static List<String> getTeamMember(int index, String ipt_stuName, List<Team> teamList){
        Team inquiryTeam = new Team();
        List<Student> stu = new ArrayList<>(); 
        List<String> name_list = new ArrayList<>();
//        inquiryTeam = InstructorMainMenuController.teams.get(index);
        try {
        	boolean found = false;
        	inquiryTeam = teamList.get(index);

            //Get all the team member info
            stu = inquiryTeam.getMember();
            
            for (int i = 0; i < stu.size(); i ++){
                if (stu.get(i).getStudentname().equals(ipt_stuName)){
                	found = true;
                    continue;
                }
                name_list.add(stu.get(i).getStudentname());
            }
            if (found) {
            	return name_list;
            }else
            	return new ArrayList<>();
            
        	
        }catch (Exception e) {
        	return name_list;
        }
        
    }

    /**
     * Sort the an teams according to the K1 energy in descending order
     * @param stu 2D list in the format of [[k1,k2, k1+k2],[k1,k2, k1+k2],...]
     */
    public static void sortK1EnergyinDescending(List<List<Double>> stu) {
        //k1_energy comparator
      Comparator<List<Double>> k1EnergyComparator 
       = (s1, s2) ->{
           double diff = s1.get(0)-s2.get(0);
           if(diff>0)
           return 1;
           else if(diff<0)
               return -1;
           else
               return 0;
       };
       
     //sorting list by k1_energy in descending order
       stu.sort(Collections.reverseOrder(k1EnergyComparator));
  }
  /**
     * Sort the an teams according to the K2 energy in descending order
     * @param stu 2D list in the format of [[k1,k2, k1+k2],[k1,k2, k1+k2],...]
     */
  public static void sortK2EnergyinDescending(List<List<Double>> stu) {
      //k2_energy comparator
      Comparator<List<Double>> k2EnergyComparator 
       = (s1, s2) ->{
        double diff = s1.get(1)-s2.get(1);
           if(diff>0)
           return 1;
           else if(diff<0)
               return -1;
           else
               return 0;
       };
       
       //sorting list by k2_energy in ascending order
       stu.sort(Collections.reverseOrder(k2EnergyComparator));
  }
    /**
     * Get team avg k1 and k2 energy by group index
     * @param groupIndex group index
     * @param srcTeam the source of team list
     * @return {Avg k1 energy, Avg k2 energy}
     */
    public static String[] getTeamEnergy(int groupIndex, List<Team> srcTeam){
        Team inquiryTeam = new Team();
        List<Student> stu = new ArrayList<>(); 

        //Get all the team member info
        //inquiryTeam = InstructorMainMenuController.teams.get(groupIndex);
        inquiryTeam = srcTeam.get(groupIndex);

        stu = inquiryTeam.getMember();

        double numOfStu = stu.size();
        double k1 = 0;
        double k2 = 0;
        for (int i = 0; i < numOfStu; i ++){
            k1 += Double. parseDouble(stu.get(i).getK1energy());
            k2 += Double. parseDouble(stu.get(i).getK2energy());
        }
        Double k1Return = k1/numOfStu;
        Double k2Return = k2/numOfStu;
        String[] k1k2 = {String.format("%.2f", k1Return),String.format("%.2f", k2Return)};
        return k1k2;
    }
}
