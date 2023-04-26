package teaming;

import javafx.beans.property.SimpleStringProperty;

/**
 * Class for storing student information
 */
public class Student {

	/*
	 * Student class: Storing the data of a student
	 * Do not touch anything in this file
	 */
	/**
	 * Row index
	 */
	private final SimpleStringProperty rowindex;
	/**
	 * team id
	 */
	private final SimpleStringProperty team;
	/**
	 * student id
	 */
	private final SimpleStringProperty studentid;
	/**
	 * student name
	 */
	private final SimpleStringProperty studentname;
	/**
	 * student itsc
	 */
	private final SimpleStringProperty email;
	/**
	 * k1 energy
	 */
	private final SimpleStringProperty k1energy;
	/**
	 * k2 energy
	 */
	private final SimpleStringProperty k2energy;
	/**
	 * k3 ticket1
	 */
	private final SimpleStringProperty k3tick1;
	/**
	 * k3 ticket2
	 */
	private final SimpleStringProperty k3tick2;
	/**
	 * student preference
	 */
	private final SimpleStringProperty mypreference;
	/**
	 * student concerns
	 */
	private final SimpleStringProperty concerns;
	
	// Name list for generating random students
	/**
	 * List of possible first name
	 */
	public final static String[] FIRSTNAMELIST = {"Alan","Albert","Alex","Alexander","Alice","Alpha","Amy","Anderson","Andy","Apple",
												  "Barack","Bean","Ben","Benedict","Benjamin","Bill","Blaze","Bob","Brandon","Bruno",
												  "Camila","Candy","Cary","Cecia","Chloe","Christopher","Cindy","Clara","Copper","Chuck",
												  "Daisy","Danny","David","Derek","Desmond","Diamond","Diana","Diego","Dimitris","Donald",
												  "Elena","Elisa","Elizabeth","Elman","Elon","Elton","Emma","Emily","Euclides","Eva",
												  "Fabiola","Fabre","Faith","Felix","Finn","Fiona","Floor","Forrest","Frances","Franklin",
												  "Gabriel","Gary","Gavin","Gia","Gibson","Gilmore","Gipsy","Gladstone","Grace","Granny",
												  "Harmony","Harper","Harry","Haven","Hazel","Hector","Heidi","Henry","Hope","Hunter",
												  "Ian","Imani","Iris","Isabella","Isaiah","Isla","Ismael","Issac","Ivan","Ivory",
												  "Jack","Jamie","Java","Jens","Jim","Joe","Joseph","Juan","Judy","Junior",
												  "Kai","Katherine","Kelvin","Kennedy","Kenneth","Kevin","Kimberly","Knox","Kobe","Kylie",
												  "Lance","Lavender","Leonardo","Linux","Logan","Lucas","Lucy","Luis","Luke","Luna",
												  "Mandy","Maria","Markus","Mary","Mason","Maxwell","Melody","Methew","Milo","Myles",
												  "Nancy","Naomi","Natalie","Nathan","Nico","Nina","Noah","Nolan","Nova","Nunu",
												  "Ocean","Odin","Olivia","Onyx","Opal","Orville","Osas","Oscar","Otis","Owen",
												  "Pablo","Pam","Patrick","Paul","Phoebe","Phoenix","Peter","Porter","Prince","Python",
												  "Qasim","Quarry","Quella","Querida","Quest","Queta","Quincy","Quintin","Quinton","Qwerty",
												  "Ram","Rebecca","Remi","Riley","River","Robert","Rom","Rose","Rowan","Ryan",
												  "Sage","Sam","Samuel","Silas","Sofia","Solaris","Stella","Steve","Summer","Sylvia",
												  "Tate","Ted","Tiffany","Timmy","Theo","Thomas","Thread","Tom","Tucker","Twinkle",
												  "Uber","Ulmer","Umar","Unique","Unix","Urban","Uriah","Uriel","Usman","Uziel",
												  "Valentina","Veda","Vera","Vicente","Victor","Victoria","Vikram","Violet","Virginia","Vivian",
												  "Wade","Warden","Wendy","Will","William","Willow","Wilson","Windows","Winnie","Winter",
												  "Xanthus","Xavi","Xavion","Xavier","Xena","Xenia","Ximena","Xiomara","Xochitl","Xyla",
												  "Yale","Yaml","Yammy","Yasmin","Yati","Yeti","Yuki","Yummy","Yuna","Yusuf",
												  "Zane","Zara","Zayne","Zeke","Zelda","Zion","Zoe","Zoey","Zoo","Zuri"};
	/**
	 * list of possible last name
	 */
	public final static String[] LASTNAMELIST = { "Aaron","Adams","Ai","Allison","An","Andrews","Armstrong","Atkinson","Austin","Auyueng",
												  "Bai","Bailey","Baker","Bell","Bennett","Bergensten","Brian","Brown","Burns","Butler",
												  "Cai","Carlson","Chan","Chau","Cheung","Cohen","Collin","Cook","Cooper","Cui",
												  "Daniels","David","Davis","Day","Dean","Ding","Dixon","Douglas","Doyle","Du",
												  "Edward","Edwin","Einstein","Ekka","Elamin","Ellis","Engineer","Esteves","Ethrouthu","Evans",
												  "Fan","Fang","Fibonacci","Fisher","Fong","Ford","Foster","Fox","Frank","Freeman",
												  "Gabale","Gaber","Gaitonde","Gama","Gate","George","Gilbert","Gorden","Gray","Green",
												  "Hall","Haber","Harbir","He","Heinz","Hidam","Ho","Hu","Huang","Huffman",
												  "Iglesia","Ikeda","Imai","Ingle","Inman","Iordanou","Ireland","Irvin","Iverson","Ivy",
												  "Jackson","Jagar","Jean","Jia","Jiang","Jin","Jo","Johnson","Jones","Jordan",
												  "Kamiya","Kane","Kang","Kao","Karl","Kim","Kimura","King","Kong","Kwong",
												  "Lam","Lane","Leung","Lewis","Li","Liang","Liu","Long","Lorenz","Lucas",
												  "Ma","MacDonald","Mai","Mao","Mak","Martin","Matt","Meng","Moles","Musk",
												  "Nagi","Nakajima","Newton","Nguyen","Nicolas","Niles","Nole","Norton","Nurmi","Nykvist",
												  "Ou","Obama","Oberst","Ola","Olmos","Oliver","Ono","Orlov","Ouyang","Overton",
												  "Pace","Pagini","Palomo","Pan","Pang","Papadias","Papadopoulos","Park","Peng","Persson",
												  "Qadir","Qi","Qian","Qin","Qiu","Queen","Quick","Quigg","Quinn","Quirk",
												  "Rains","Rana","Ren","Rey","Robin","Rojo","Roma","Rossiter","Ruan","Rush",
												  "Saitou","Salvi","Scott","Shek","Shi","Simpson","Siu","Smith","Sun","Sung",
												  "Takahashi","Tang","Tawfeek","Taylor","Tong","Tran","Trump","Tsio","Tsiu","Turing",
												  "Udayar","Ueda","Ukmabam","Unkle","Underwood","Unterbrink","Upton","Usham","Utkin","Uttara",
												  "Vann","Ventura","Verity","Villa","Vinci","Vinter","Viola","Vos","Vroom","Vu",
												  "Wada","Wagner","Walker","Walter","Wang","Wei","Wen","Wood","Wu","Wynne",
												  "Xander","Xanthopoulos","Xanthos","Xavier","Xi","Xiao","Xie","Xiong","Xu","Xylander",
												  "Yamazaki","Yang","Yao","Yau","Yueng","Yoon","York","Young","Yu","Yun",
												  "Zaman","Zappa","Zhang","Zhao","Zheng","Zhou","Zhu","Zima","Zimmermann","Zophe"};
	
	/**
	 * Intitialize student entry
	 * @param row_index row index
	 * @param team team id
	 * @param student_id student id
	 * @param student_name student name
	 * @param email stident itsc
	 * @param k1_energy student k1 energy
	 * @param k2_energy student k2 energy
	 * @param k3_tick1 student k3 ticket1
	 * @param k3_tick2 student k3 ticket2
	 * @param my_preference student perference
	 * @param concerns student concerns
	 */
	public Student(String row_index, String team, String student_id, String student_name, String email, String k1_energy, String k2_energy, String k3_tick1,
			String k3_tick2, String my_preference, String concerns) {
		this.rowindex = new SimpleStringProperty(row_index);
		this.team = new SimpleStringProperty(team);
		this.studentid = new SimpleStringProperty(student_id);
		this.studentname = new SimpleStringProperty(student_name);
		this.email = new SimpleStringProperty(email);
		this.k1energy = new SimpleStringProperty(k1_energy);
		this.k2energy = new SimpleStringProperty(k2_energy);
		this.k3tick1 = new SimpleStringProperty(k3_tick1);
		this.k3tick2 = new SimpleStringProperty(k3_tick2);
		this.mypreference = new SimpleStringProperty(my_preference);
		this.concerns = new SimpleStringProperty(concerns);
	}
	/**
	 * get row index
	 * @return row index
	 */
	public String getRowindex() {
		return rowindex.get();
	}
	/**
	 * set row index
	 * @param val row index
	 */
	public void setRowindex(String val) {
		rowindex.set(val);
	}
	/**
	 * get team id
	 * @return team id
	 */
	public String getTeam() {
		return team.get();
	}

	/**
	 * set team id
	 * @param val team id
	 */
	public void setTeam(String val) {
		team.set(val);
	}
	/**
	 * get student id
	 * @return student id
	 */
	public String getStudentid() {
		return studentid.get();
	}
	/**
	 * set student id
	 * @param val student id
	 */
	public void setStudentid(String val) {
		studentid.set(val);
	}
	/**
	 * get student name
	 * @return student name
	 */
	public String getStudentname() {
		return studentname.get();
	}
	/**
	 * set student name
	 * @param val student name
	 */
	public void setStudentname(String val) {
		studentname.set(val);
	}
	/**
	 * get student email
	 * @return email
	 */
	public String getEmail() {
		return email.get();
	}
	/**
	 * set student email
	 * @param val email
	 */
	public void setEmail(String val) {
		email.set(val);
	}
	/**
	 * get k1 energy
	 * @return k1 energy
	 */
	public String getK1energy() {
		return k1energy.get();
	}
	/**
	 * set k1 energy
	 * @param val k1 energy
	 */
	public void setK1energy(String val) {
		k1energy.set(val);
	}
	/**
	 * get k2 energy
	 * @return k2 energy
	 */
	public String getK2energy() {
		return k2energy.get();
	}
	/**
	 * set k2 energy
	 * @param val k2 energy
	 */
	public void setK2energy(String val) {
		k2energy.set(val);
	}
	/**
	 * get k3 tickek 1
	 * @return k3tick1
	 */
	public String getK3tick1() {
		return k3tick1.get();
	}
	/**
	 * set k3 ticket 1
	 * @param val k3 ticket1
	 */
	public void setK3tick1(String val) {
		k3tick1.set(val);
	}
	/**
	 * get k3 ticket2
	 * @return k3 ticket2
	 */
	public String getK3tick2() {
		return k3tick2.get();
	}
	/**
	 * set k3 ticket2
	 * @param val k3ticket2
	 */
	public void setK3tick2(String val) {
		k3tick2.set(val);
	}
	/**
	 * get student prefrence
	 * @return student preference
	 */
	public String getMypreference() {
		return mypreference.get();
	}
	/**
	 * set student preference
	 * @param val student preference
	 */
	public void setMypreference(String val) {
		mypreference.set(val);
	}
	/**
	 * get student concerns
	 * @return student concerns
	 */
	public String getConcerns() {
		return concerns.get();
	}
	/**
	 * set student concerns
	 * @param val concerns
	 */
	public void setConcerns(String val) {
		concerns.set(val);
	}
}