package helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import controllers.InstructorMainMenuController;
import teaming.Student;
import teaming.Team;

/**
 * Helper process related with processing process
 */

public class Process {
	
	/*
	 * 	Part 1: Helpers for general purpose
	 */
	/**
	 * Generate temporary team name e.g. i=14, j=4, c = 0 => 0014
	 * @param i index
	 * @param j length of the name
	 * @param c char to fill the gap
	 * @return temporary team name
	 */
	public static String string(int i, int j, char c) {
		// Auto-generated method stub
    	String temp=Integer.toString(i+1);
    	
    	while(temp.length()<j) {
    		temp= c + temp;
    	}
    	return temp;
	}

	//check whether team exist
	/**
	 * Check if there exist any team is allocated
	 * @param src source of team list
	 * @return true if exist team allocation record
	 */
	public static boolean existTeam(List<Team> src){
//        if (InstructorMainMenuController.teams.size()>0)
        if (src.size()>0)

        	return true;
        else
            return false;
    }
	
	
	/*
	 * 	Part 2: Components of the ATUengine
	 */
	/**
	 * sort array list of student by K1 Energy in descending order
	 * @param stu list of student to to be sorted
	 */
    public static void sortK1EnergyinDescending(List<Student> stu) {
    	  //k1_energy comparator
	    Comparator<Student> k1EnergyComparator 
	     = (s1, s2) ->{
	    	 double diff = Double.parseDouble(s1.getK1energy())-Double.parseDouble(s2.getK1energy());
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
	 * sort array list of student by K2 Energy in descending order
	 * @param stu list of student to to be sorted
	 */
    public static void sortK2EnergyinDescending(List<Student> stu) {
   	 //k2_energy comparator
	    Comparator<Student> k2EnergyComparator 
	     = (s1, s2) ->{
	    	 double diff = Double.parseDouble(s1.getK2energy())-Double.parseDouble(s2.getK2energy());
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
	 * sort array list of student by K2 Energy in acscending order
	 * @param stu list of student to to be sorted
	 */
    public static void sortK2EnergyinAscending(List<Student> stu) {
    	 //k2_energy comparator
	    Comparator<Student> k2EnergyComparator 
	     = (s1, s2) ->{
	    	 double diff = Double.parseDouble(s1.getK2energy())-Double.parseDouble(s2.getK2energy());
	    	 if(diff>0)
	    	 return 1;
	    	 else if(diff<0)
	    		 return -1;
	    	 else
	    		 return 0;
	     };
	     
	     //sorting list by k2_energy in ascending order
	     stu.sort(k2EnergyComparator);
	}
    
	/**
	 * remove top i student from student list 
	 * @param studentsList student list that need to remove student
	 * @param teamSize number of top i student need to remove from the student list 
	 */
    public static void removeStudent(List<Student> studentsList, int teamSize) {
	     for (int i = 0; i < teamSize; i ++){
	    	 studentsList.remove(0);
	     }
	}
	
	/**
	 * select top team size student into target list
	 * @param studentsList source studentlist
	 * @param teamSize top i student
	 * @param member target student list
	 */
	public static void selectMember(List<Student> studentsList, int teamSize, List<Student> member) {
		 for (int i = 0; i < teamSize; i ++){
			 member.add(studentsList.get(i));
	     }
	}
	
	
	//create team
	/**
	 * Create and initialize team
	 * @param teams List of team to be initialise
	 * @param teamSize number of team
	 */
	public static void createTeam(List<Team> teams, int teamSize) {
	     for (int i = 0; i < teamSize; i ++){  
	    	    int team_id=i+1;
	            String team_name = "T-"+string(i, 4,'0');
	            Team tm = new Team(team_id,team_name);
	            teams.add(tm);
	     }
	}
	
	//check if student exit
	/**
	 * Check if an student object in the student list
	 * @param stu student list
	 * @param checkStudent student object
	 * @return true if student exist, else false
	 */
	public static boolean studentExist(List<Student> stu, Student checkStudent) {
		for(Student student: stu) {
			String id1=student.getStudentid();
			String id2=checkStudent.getStudentid();
			//same id, student exist
			if(id1 == id2)
				return true;
		}
		return false;
	}
	
	
	/*
	 * 	Part 3: The main engine to allocate students
	 */
	/**
	 * Allocate student into teams
	 * @param teams target teams
	 */
	public static void ATUengine(List<Team> teams){
		//clear teams list's data
	    teams.clear();
	   
	    //total number of teamSize
	    int teamSize = InstructorMainMenuController.students.size()/3;
	    
	    //can't form group:
	    //1. student number < 3
	    //2. student number =5 (3,2 / 4,1)
//	    int stundetNum=InstructorMainMenuController.students.size();
//	    if(stundetNum<3 || stundetNum==5)
//	    	return;
	    
	    //student List
		List<Student> studentsList = new ArrayList<Student>(InstructorMainMenuController.students);  
	    
		// System.out.println("teamSize:"+teamSize);
	    
	    //array list of k1member, k2member & k3member
		List<Student> k1member = new ArrayList<>();
		List<Student> k2member = new ArrayList<>();
		List<Student> k3member = new ArrayList<>();

	    //k1 energy average value
		double k1energyAvg=0;
	     for (Student student: studentsList)
		 {
	     	double student_k1 = Double.parseDouble(student.getK1energy());	
		 	k1energyAvg+=student_k1;
		 }
	     k1energyAvg /= studentsList.size();
	    
     	//sort student list by k1 Energy in descending order
	    sortK1EnergyinDescending(studentsList);
	    
	    //check top team size's student k1 energy > =k1 average
	     for(int i=0;i<teamSize;i++) {
	     	double student_k1 = Double.parseDouble(studentsList.get(i).getK1energy());
	     	//number of student (k1 energy >= k1 average) least than team size, cannot form the group
	     	if(student_k1 < k1energyAvg)
	     		return;
	     }
	    
	    //select top(k1 energy) team size student into k1menber list
	    selectMember(studentsList,teamSize,k1member);
	    
	    //remove the selected k1member in studentList
	    removeStudent(studentsList,teamSize);
	    
		//sort student list by k2 Energy in descending order 
	    sortK2EnergyinDescending(studentsList);
	    
	    //select top(k2 energy) team size student into k2menber list
	     selectMember(studentsList,teamSize,k2member);
	     
	 	//sort k2member by k2 Energy in ascending order
	     sortK2EnergyinAscending(k2member);
	     
	    //remove the selected k2member in studentList
	     removeStudent(studentsList,teamSize);
		
	    //remain student add to k3member list without sorting
		for(Student student: InstructorMainMenuController.students) {
			boolean exist=studentExist(studentsList, student);
			//student in studentsList occur in original input students list, add to k3 member
			if(exist) {
				k3member.add(student);
			}
		}
	    
	    // create team 
	    createTeam(teams, teamSize); 
	    
	    for (int i = 0; i < teamSize; i ++){
	    	String team_id = Integer.toString(i+1);
	        k1member.get(i).setTeam(team_id);
	        k2member.get(i).setTeam(team_id);
	        k3member.get(i).setTeam(team_id);
			teams.get(i).addMember(k1member.get(i));
            teams.get(i).addMember(k2member.get(i));
            teams.get(i).addMember(k3member.get(i));
        }
	    
	     //Remaining students add to last and second last team
	     int lastTeam =teams.size();
	     for(int i=teamSize; i<k3member.size();i++) {
	   
	    	 String team_id =Integer.toString(lastTeam);
	    	 k3member.get(i).setTeam(team_id);
	    	 teams.get(lastTeam-1).addMember(k3member.get(i));
 
	    	 lastTeam--;
	    }     
	}
}
