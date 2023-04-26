package teaming;
import java.util.*;
import javafx.beans.property.SimpleStringProperty;

/**
 * Class for storing team information
 */
public class Team {
    /**
     * team id
     */
    private SimpleStringProperty team_id;
    /**
     * team name
     */
    private SimpleStringProperty team_name;
    /**
     * team meamber in this team
     */
    private ArrayList<Student> team_member;
    /** 
     * team leader for this team
     */
    private SimpleStringProperty team_leader;

    /**
     * Default constructor, initialize everythings to be null
     */
    public Team(){
        this.team_id = new SimpleStringProperty();
        //for non-H student,
        //seems we don't need team_leader
        team_name=null;
        team_member = new ArrayList<>();
        team_leader = null;
        
    }
    /**
     * Initialize team by team id and team name with empty team member
     * @param team_id team id
     * @param team_name team name
     */
    public Team(int team_id,String team_name){
        this.team_id = new SimpleStringProperty(Integer.toString(team_id));
        //for non-H student,
        //seems we don't need team_leader
        this.team_name= new SimpleStringProperty(team_name);
        team_leader = null;
        team_member = new ArrayList<>();
    }
    
    /**
     * get team member
     * @return list of team member
     */
    public ArrayList<Student> getMember(){
        return this.team_member;
    }

    /**
     * add student to the team member list
     * @param stu student to be added
     */
    public void addMember(Student stu){
        team_member.add(stu);
    }

    /**
     * get team name
     * @return team name
     */
    public String getTeamName(){
        return this.team_name.get();
    }

    /**
     * get team id
     * @return team id
     */
    public String getTeamID(){
    	return this.team_id.get();
    }
   
    /**
     * get team size
     * @return team size
     */
    public int getEachTeamSize(){
        return this.team_member.size();
    }
    
  
}
