package testing;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import helpers.Input;
import helpers.InputH;
import helpers.Process;
import helpers.Output;
import teaming.Team;
import teaming.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import teaming.Statistics;



public class Tester {
	String OS = System.getProperty("os.name");
	String CURR_DIR = System.getProperty("user.dir");
	String FILEPATH = "";
	ObservableList<Student> students = FXCollections.observableArrayList();
	ObservableList<Statistics> stats = FXCollections.observableArrayList();
	
	@Test
	public void loadStandard()
	{	
		System.out.println("Input test case 1: Load standard file");
		if (OS.equals("Mac OS X")){
			FILEPATH = CURR_DIR + "/src/main/resources/input_files/Sample+Student+Data+File.CSV";
		}else{
			FILEPATH = CURR_DIR + "\\src\\main\\resources\\input_files\\Sample+Student+Data+File.CSV";
		}
		File f = new File(FILEPATH);
		try {
			students = Input.getAllStudents(Input.loadRawStudentData(f));
			assertEquals(students.size(),100);
			System.out.println("Standard file loaded sucessfully!");
			
			Input.constructOverallStats(students, stats);
			assertEquals(stats.get(0).getValue(),"100");
			assertEquals(stats.get(1).getValue(),"(55.1, 10.0, 100.0)");
			assertEquals(stats.get(2).getValue(),"(65.6, 30.0, 90.0)");
			assertEquals(stats.get(3).getValue(),"12");
			assertEquals(stats.get(4).getValue(),"5");
			assertEquals(stats.get(5).getValue(),"0");
			
			System.out.println("Statistics are correct.");
			System.out.println();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void loadSmall()
	{
		System.out.println("Input test case 2: Load small file");
		if (OS.equals("Mac OS X")){
			FILEPATH = CURR_DIR + "/src/main/resources/input_files/Sample+Student+Data+File+Small.csv";
		}else{
			FILEPATH = CURR_DIR + "\\src\\main\\resources\\input_files\\Sample+Student+Data+File+Small.csv";
		}
		File f = new File(FILEPATH);
		try {
			students = Input.getAllStudents(Input.loadRawStudentData(f));
			assertEquals(students.size(),10);
			System.out.println("Small file loaded sucessfully!");
			
			Input.constructOverallStats(students, stats);
			assertEquals(stats.get(0).getValue(),"10");
			assertEquals(stats.get(1).getValue(),"(48.3, 26.0, 71.0)");
			assertEquals(stats.get(2).getValue(),"(65.5, 40.0, 85.0)");
			assertEquals(stats.get(3).getValue(),"2");
			assertEquals(stats.get(4).getValue(),"1");
			assertEquals(stats.get(5).getValue(),"0");
			
			System.out.println("Statistics are correct.");
			System.out.println();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void loadWrongData()
	{
		System.out.println("Input test case 3: Load error file");
		if (OS.equals("Mac OS X")){
			FILEPATH = CURR_DIR + "/src/main/resources/input_files/Sample+Student+Data+File+Wrong+Data.csv";
		}else{
			FILEPATH = CURR_DIR + "\\src\\main\\resources\\input_files\\Sample+Student+Data+File+Wrong+Data.csv";
		}
		File f = new File(FILEPATH);
		try {
			students = Input.getAllStudents(Input.loadRawStudentData(f));
			assertEquals(students,null);
			System.out.println("Wrong data in the file!");
			System.out.println();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void loadEmptyData()
	{
		System.out.println("Input test case 4: Load empty file");
		if (OS.equals("Mac OS X")){
			FILEPATH = CURR_DIR + "/src/main/resources/input_files/Sample+Student+Data+File+Empty+Data.csv";
		}else{
			FILEPATH = CURR_DIR + "\\src\\main\\resources\\input_files\\Sample+Student+Data+File+Empty+Data.csv";
		}
		File f = new File(FILEPATH);
		try {
			students = Input.getAllStudents(Input.loadRawStudentData(f));
			assertEquals(students,null);
			System.out.println("Empty data in the file!");
			System.out.println();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void loadBlank()
	{
		System.out.println("Input test case 5: Load blank file");
		if (OS.equals("Mac OS X")){
			FILEPATH = CURR_DIR + "/src/main/resources/input_files/blank_file.csv";
		}else{
			FILEPATH = CURR_DIR + "\\src\\main\\resources\\input_files\\blank_file.csv";
		}
		File f = new File(FILEPATH);
		try {
			students = Input.getAllStudents(Input.loadRawStudentData(f));
			assertEquals(students,null);
			System.out.println("Blank file!");
			System.out.println();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void loadNoStudent()
	{
		System.out.println("Input test case 6: Load no student file");
		if (OS.equals("Mac OS X")){
			FILEPATH = CURR_DIR + "/src/main/resources/input_files/no_student.csv";
		}else{
			FILEPATH = CURR_DIR + "\\src\\main\\resources\\input_files\\no_student.csv";
		}
		File f = new File(FILEPATH);
		try {
			students = Input.getAllStudents(Input.loadRawStudentData(f));
			assertEquals(students.size(),0);
			System.out.println("No student in the file!");
			System.out.println();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void inputNumForRandomGenerating()
	{
		System.out.println("Input H test case 1: Test different input of number of students");
		
		assertEquals(InputH.getNumStudent("199"), -1);
		assertEquals(InputH.getNumStudent("501"), -1);
		assertEquals(InputH.getNumStudent("-10000"), -1);
		assertEquals(InputH.getNumStudent("10000"), -1);
		
		assertEquals(InputH.getNumStudent("200"), 200);
		assertEquals(InputH.getNumStudent("201"), 201);
		assertEquals(InputH.getNumStudent("300"), 300);
		assertEquals(InputH.getNumStudent("499"), 499);
		assertEquals(InputH.getNumStudent("500"), 500);
		
		assertEquals(InputH.getNumStudent(null), 0);
		assertEquals(InputH.getNumStudent("abc"), -1);
		assertEquals(InputH.getNumStudent("250f"), -1);
		assertEquals(InputH.getNumStudent("300.52"), -1);
		assertEquals(InputH.getNumStudent(""), -1);
		
		System.out.println("Test Completed!");
		System.out.println();
	}
	
	@Test
	public void genRandomData()
	{
		System.out.println("Input H test case 2: Test whether generateStudents() function properly");
		
		int n = 1000;
		InputH.generateStudents(n, students);
		
		assertEquals(students.size(),n);
		
		System.out.println("Test Completed!");
		System.out.println();
	}
	// JUNIT for PROCESS-----------------------------------------------------------------------------------------------
	@Test
	public void testGenerateName() {
		assertEquals(Process.string(9,4,'0'), "0010");
		assertEquals(Process.string(1999,1,'0'), "2000");
		assertEquals(Process.string(0,0,'@'),"1");
	}
	

	@Test
	public void testSortK1EnergyinDescending() {
		Student firstStudent = new Student("1000","t1","123","Ab, Bc","qwe@connect.ust.hk", "40", "10", "0", "0", "", "");
		Student secondStudent = new Student("1001","0","456","Ef, Gh","asd@connect.ust.hk", "20", "8", "0", "0", "", "");
		Student thirdStudent = new Student("1002","0","457","Ef, Gh","asd@connect.ust.hk", "80", "10", "0", "0", "", "");
		Student forthStudent = new Student("1003","0","458","Ef, Gh","asd@connect.ust.hk", "80", "0", "0", "0", "", "");
		Student fifthStudent = new Student("1004","0","459","Ef, Gh","asd@connect.ust.hk", "15", "0", "0", "0", "", "");
		
		List<Student> src = new ArrayList<Student>();
		src.add(firstStudent);
		src.add(secondStudent);
		src.add(thirdStudent);
		src.add(forthStudent);
		src.add(fifthStudent);

		Process.sortK1EnergyinDescending(src);
		
		assertEquals(src.get(0),thirdStudent);
		assertEquals(src.get(1),forthStudent);
		assertEquals(src.get(2),firstStudent);
		assertEquals(src.get(3),secondStudent);
		assertEquals(src.get(4),fifthStudent);
		
		
	}
	
	@Test
	public void testSortK2EnergyinDescending() {
		Student firstStudent = new Student("0","t1","123","Ab, Bc","qwe@connect.ust.hk", "20", "45", "0", "0", "", "");
		Student secondStudent = new Student("0","0","456","Ef, Gh","asd@connect.ust.hk", "40", "25", "0", "0", "", "");
		Student thirdStudent = new Student("0","0","457","Ef, Gh","asd@connect.ust.hk", "60", "75", "0", "0", "", "");
		Student forthStudent = new Student("0","0","458","Ef, Gh","asd@connect.ust.hk", "80", "75", "0", "0", "", "");
		Student fifthStudent = new Student("1004","0","459","Ef, Gh","asd@connect.ust.hk", "15", "10", "0", "0", "", "");
		
		List<Student> src = new ArrayList<Student>();
		src.add(firstStudent);
		src.add(secondStudent);
		src.add(thirdStudent);
		src.add(forthStudent);
		src.add(fifthStudent);

		Process.sortK2EnergyinDescending(src);

		assertEquals(src.get(0),thirdStudent);
		assertEquals(src.get(1),forthStudent);
		assertEquals(src.get(2),firstStudent);
		assertEquals(src.get(3),secondStudent);
		assertEquals(src.get(4),fifthStudent);
		
		
	}
	
	@Test
	public void testSortK2EnergyinAscending() {
		Student firstStudent = new Student("0","t1","123","Ab, Bc","qwe@connect.ust.hk", "20", "75", "0", "0", "", "");
		Student secondStudent = new Student("0","0","456","Ef, Gh","asd@connect.ust.hk", "40", "55", "0", "0", "", "");
		Student thirdStudent = new Student("0","0","457","Ef, Gh","asd@connect.ust.hk", "60", "35", "0", "0", "", "");
		Student forthStudent = new Student("0","0","458","Ef, Gh","asd@connect.ust.hk", "80", "55", "0", "0", "", "");
		
		List<Student> src = new ArrayList<Student>();
		src.add(firstStudent);
		src.add(secondStudent);
		src.add(thirdStudent);
		src.add(forthStudent);

		Process.sortK2EnergyinAscending(src);

		assertEquals(src.get(0),thirdStudent);
		assertEquals(src.get(1),secondStudent);
		assertEquals(src.get(2),forthStudent);
		assertEquals(src.get(3),firstStudent);
		
	}
	
	@Test
	public void testRemoveStudent() {
		Student firstStudent = new Student("0","t1","123","Ab, Bc","qwe@connect.ust.hk", "20", "75", "0", "0", "", "");
		Student secondStudent = new Student("0","0","456","Ef, Gh","asd@connect.ust.hk", "40", "55", "0", "0", "", "");
		Student thirdStudent = new Student("0","0","457","Ef, Gh","asd@connect.ust.hk", "60", "45", "0", "0", "", "");
		
		List<Student> src = new ArrayList<Student>();
		src.add(firstStudent);
		src.add(secondStudent);
		src.add(thirdStudent);
		
		Process.removeStudent(src, 3);
		assertEquals(src.size(), 0);
	}
	
	@Test
	public void testSelectStudent(){
		Student firstStudent = new Student("0","t1","123","Ab, Bc","qwe@connect.ust.hk", "20", "75", "0", "0", "", "");
		Student secondStudent = new Student("0","0","456","Ef, Gh","asd@connect.ust.hk", "40", "55", "0", "0", "", "");
		Student thirdStudent = new Student("0","0","457","Ef, Gh","asd@connect.ust.hk", "60", "45", "0", "0", "", "");
		
		List<Student> src = new ArrayList<Student>();
		src.add(firstStudent);
		src.add(secondStudent);
		src.add(thirdStudent);
		
		List<Student> anotherList = new ArrayList<Student>();
		Process.selectMember(src,1, anotherList);
		assertEquals(anotherList.get(0), firstStudent);
	}
	
	
	@Test
	public void testStudentExist(){
		Student firstStudent = new Student("0","t1","1234","Ab, Bc","qwe@connect.ust.hk", "0", "10", "0", "0", "", "");
		Student secondStudent = new Student("0","0","1235","Ef, Gh","asd@connect.ust.hk", "2", "8", "0", "0", "", "");
		Student thirdStudent = new Student("0","0","1236","Ef, Gh","asd@connect.ust.hk", "2", "10", "0", "0", "", "");
		
		List<Student> src = new ArrayList<Student>();
		src.add(firstStudent);
		src.add(secondStudent);
		src.add(thirdStudent);
		
		assertTrue(Process.studentExist(src, firstStudent));
	
	}
	
	@Test
	public void testStudentNotExist(){
		Student firstStudent = new Student("0","t1","1234","Ab, Bc","qwe@connect.ust.hk", "0", "10", "0", "0", "", "");
		Student secondStudent = new Student("0","0","1235","Ef, Gh","asd@connect.ust.hk", "2", "8", "0", "0", "", "");
		Student thirdStudent = new Student("0","0","1236","Ef, Gh","asd@connect.ust.hk", "2", "10", "0", "0", "", "");
		Student forthStudent = new Student("0","0","1237","Ef, Gh","asd@connect.ust.hk", "80", "35", "0", "0", "", "");
		
		List<Student> src = new ArrayList<Student>();
		src.add(firstStudent);
		src.add(secondStudent);
		src.add(thirdStudent);
		
		assertFalse(Process.studentExist(src, forthStudent));
	
	}
	
	@Test
	public void testCreateTeam() {
		List<Team> src = new ArrayList<Team>();

		Process.createTeam(src, 0);
		assertEquals(src.size(), 0);
		
		Process.createTeam(src, -8);
		assertEquals(src.size(), 0);
		
		Process.createTeam(src, 5);
		assertEquals(src.size(), 5);
		
		
	}
	
	// JUNIT For OUTPUT----------
	
	@Test
	public void checkDigit() {
		System.out.println("Outout test case 1");
		assertFalse(Output.onlyDigit("1 2"));
		assertFalse(Output.onlyDigit("ab"));
		assertFalse(Output.onlyDigit("-9"));
		
		assertTrue(Output.onlyDigit("0"));
		assertTrue(Output.onlyDigit("9999"));
		assertTrue(Output.onlyDigit("20791423"));
	}
	
	@Test
	public void checkString() {
		System.out.println("Output test case 2");
		assertFalse(Output.onlyString("1 2"));
		assertFalse(Output.onlyString("-9"));
		assertFalse(Output.onlyString("20791423"));
		
		assertTrue(Output.onlyString("ab"));
		assertTrue(Output.onlyString("LiCCE"));
	}
	
	@Test
	public void checkCreateGet() {
		System.out.println("Process test case 3");
		Team sampleTeam = new Team();
		Team anotherTeam = new Team(0,"TABC");
		assertEquals(sampleTeam.getMember().size(), 0);
		
		List<Team> sampleList= new ArrayList<Team>();
		assertEquals(Output.getTeamEnergyDouble(0, sampleList)[0], -1,0);
		
		System.out.println(Output.getTeamEnergyDouble(0, sampleList));
		Student firstStudent = new Student("0","0","123","Ab, Bc","qwe@connect.ust.hk", "0", "100", "0", "0", "", "");
		Student secondStudent = new Student("0","0","123","Ab, Bc","qwe@connect.ust.hk", "100", "0", "0", "0", "", "");
		anotherTeam.addMember(firstStudent);
		sampleList.add(anotherTeam);
	
		
		assertEquals(anotherTeam.getTeamName(), "TABC");
		assertEquals(Output.getTeamEnergyDouble(0, sampleList)[0], 0, 0);
		assertEquals(Output.getTeamEnergyDouble(0, sampleList)[1], 100, 0);
		
		anotherTeam.addMember(secondStudent);
		assertEquals(Output.getTeamEnergyDouble(0, sampleList)[0], 50, 0);
		assertEquals(Output.getTeamEnergyDouble(0, sampleList)[1], 50, 0);
		
	}
	
	@Test
	public void checkValidNameID() {
		System.out.println("Process test case 4");
		String itscValid = "lcce";
		String itscInvalid = "itc6";
		String idValid = "292929";
		String idInvalid = "123 45";
		
		assertTrue(Output.formatValidation(itscValid, idValid));
		assertFalse(Output.formatValidation(itscValid, idInvalid));
		assertFalse(Output.formatValidation(itscInvalid, idValid));
		assertFalse(Output.formatValidation(itscInvalid, idInvalid));
		
	}
	
	@Test
	public void checkStudentExist() {
		System.out.println("Process test case 5");
		Student firstStudent = new Student("0","t1","123","Ab, Bc","qwe@connect.ust.hk", "50", "55", "0", "0", "", "");
		Student secondStudent = new Student("0","t2","456","Ef, Gh","asd@connect.ust.hk", "50", "55", "0", "0", "", "");
		ObservableList<Student> sampleList = FXCollections.observableArrayList();
		sampleList.add(firstStudent);
		sampleList.add(secondStudent);
		String[] sampleResult = {"Ab, Bc", "t1"};
		String[] anotherResult = {"Ef, Gh", "t2"};
		String[] errorResult = {"-1", "-1"};

		assertArrayEquals(Output.studentExist("qwe", "123", sampleList), sampleResult);
		assertArrayEquals(Output.studentExist("asd", "456", sampleList), anotherResult);
		assertArrayEquals(Output.studentExist("qw", "123", sampleList), errorResult);
		assertArrayEquals(Output.studentExist("qwe", "124", sampleList), errorResult);
		assertArrayEquals(Output.studentExist("qw3", "124", sampleList), errorResult);

	}
	
	@Test
	public void checkgetTeamMemberEnergy() {
		System.out.println("Process test case 6");
		Student firstStudent = new Student("0","t1","123","Ab, Bc","qwe@connect.ust.hk", "50", "55", "0", "0", "", "");
		Student secondStudent = new Student("0","0","456","Ef, Gh","asd@connect.ust.hk", "100", "55", "0", "0", "", "");
		Team sampleTeam = new Team(0, "SampleTeam");
		
		List<String> sampleOutputOne = new ArrayList<String>();
		List<String> sampleOutputTwo = new ArrayList<String>();
		
		sampleOutputOne.add("Ef, Gh");
		sampleOutputTwo.add("Ab, Bc");
		
		sampleTeam.addMember(firstStudent);
		sampleTeam.addMember(secondStudent);
		
		List<Team> sampleList = new ArrayList<Team>();
		assertEquals(Output.getTeamMember(0, "Ab, Bc", sampleList).size(), 0);
		assertFalse(Process.existTeam(sampleList));
		
		sampleList.add(sampleTeam);
		
		assertEquals(Output.getTeamMember(0, "Ab, Bc", sampleList), sampleOutputOne);
		assertEquals(Output.getTeamMember(0, "Ef, Gh", sampleList), sampleOutputTwo);
		assertEquals(Output.getTeamMember(0, "ASDFG, Bc", sampleList).size(), 0);
		
		String[] out = {"75.00", "55.00"};
		
		assertArrayEquals(Output.getTeamEnergy(0,sampleList), out);
		assertTrue(Process.existTeam(sampleList));
	}
	
	@Test
	public void checkOutEnergySort() {
		System.out.println("Process test case 7");
		List<List<Double>> src = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			src.add(new ArrayList<Double>());
		}
		src.get(0).add((double) 0);
		src.get(0).add((double) 10);
		
		src.get(1).add((double) 2);
		src.get(1).add((double) 8);
		
		src.get(2).add((double) 2);
		src.get(2).add((double) 20);
		
		src.get(3).add((double) 5);
		src.get(3).add((double) 10);
		
		src.get(4).add((double) 6);
		src.get(4).add((double) 0);
		
		List<List<Double>> out = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			out.add(new ArrayList<Double>());
		}
		out.get(0).add((double) 6);
		out.get(0).add((double) 0);
		
		out.get(1).add((double) 5);
		out.get(1).add((double) 10);
		
		out.get(2).add((double) 2);
		out.get(2).add((double) 20);
		
		out.get(3).add((double) 2);
		out.get(3).add((double) 8);
		
		out.get(4).add((double) 0);
		out.get(4).add((double) 10);
		
		
		Output.sortK2EnergyinDescending(src);
		Output.sortK1EnergyinDescending(src);
		
		assertEquals(src, out);
		
		
	}
}
