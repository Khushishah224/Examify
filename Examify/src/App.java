import java.util.*;
import java.sql.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Examify {
	static Connection con = null;
	static Scanner sc = new Scanner(System.in);
	static int f_id, s_id;
	static int size;
	static int score;
	static String Date, Time;
	static ResultSet r;
	static int choice;
	static int mode;
	static int sub;
	static int number;

	public static void main(String[] args) throws Exception {

		String dburl = "jdbc:mysql://localhost:3306/Khushi";
		String dbuser = "root";
		String dbpass = "";
		String driver = "com.mysql.cj.jdbc.Driver";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(dburl, dbuser, dbpass);
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC driver not found: " + e.getMessage());
			return; // Terminate the code execution
		} catch (SQLException e) {
			System.err.println("Database connection error: " + e.getMessage());
			return; // Terminate the code execution
		}
		try {
			if (con != null) {
				// System.out.println("connection Successfull");
			}
		} catch (Exception e) {
			System.err.println("Error closing database connection: " + e.getMessage());

		}
		if (con != null) {
			System.out.println("connection succesfull");
		} else {
			System.out.println("failed!!");
		}
		if (con != null) {
			String Date_Time = Date_Time();
			String a[] = Date_Time.split(" ");
			Date = a[0];
			Time = a[1];
			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------");
			System.out.println(
					"                                                    W E L C O M E                                               ");
			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------");
			System.out.println();
			do {
				try {
					System.out.println(
							"PRESS (1). IF YOU ARE FACULTY  \nPRESS (2). IF YOU ARE STUDENT \nPRESS (3). FOR EXIT");
					choice = sc.nextInt();
					switch (choice) {
						case 1: // for faculty
						{
							try {
								f_login();

							} catch (Exception e) {
								System.out
										.println(
												"-----------------------------------------------------------------------");
							}
						}
							break;

						case 2: // FOR Students
						{
							try {
								s_login();

							} catch (Exception e) {
								System.out
										.println(
												"-----------------------------------------------------------------------");
							}
						}
							break;

						case 3: {
							System.out.println("Thank you!!");
						}
							break;

						default: {
							System.out.println("Invalid Input");
						}
							break;
					}

				} catch (Exception e) {
					System.out.println("Invalid Input !! ");
					sc.nextLine();
					System.out.println("-----------------------------------------------------------------------");
				}
			} while (choice != 3);
		}
	}

	private static void faculty_options(ResultSet r) throws Exception {   // options for faculty
		do {
			try {
				System.out.println(
						"choose option \n(1).for auto-generating question paper \n(2).for manualy generate Question paper\r\n"
								+ "(3).for generating Students Marksheet\n(4).log out");
				choice = sc.nextInt();

				switch (choice) {
					case 1: {
						auto_generatePaper(r);
					}
						break;

					case 2: {
						manualy_generatePaper(r);
					}
						break;

					case 3: {
						Marksheet marksheet = new Marksheet();
						marksheet.generateMarksheet();
					}
						break;

					case 4: {
						System.out.println("Log out");
					}
						break;

					default: {
						System.out.println("Invalid Output.. ");
					}
						break;
				}
			} catch (Exception e) {
				System.out.println("Invalid Input!!");
				sc.nextLine();
				System.out.println("-----------------------------------------------------------------------");
			}
		} while (choice != 4);

	}

	private static String Date_Time() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");

		// Get the current date and time as a LocalDateTime object
		LocalDateTime currentDateTime = LocalDateTime.now();

		// Format the LocalDateTime object as a string
		String dateString = currentDateTime.format(formatter);

		return dateString;
	}

	private static boolean isFaculty_idExists() throws SQLException { // checks faculty id is exists or not

		System.out.println("enter login_id : ");
		f_id = sc.nextInt();
		String selectQuery = "SELECT * FROM faculty WHERE f_id= ?";
		PreparedStatement statement = con.prepareStatement(selectQuery);
		statement.setInt(1, f_id);
		ResultSet r = statement.executeQuery();
		return r.next();
	}

	private static void f_login() throws Exception { // for faculty login
		boolean correct_pass = false;
		String f_pass = "";
		int attemptsLeft = 4;

		if (isFaculty_idExists()) {
			while (correct_pass != true && attemptsLeft > 0) {
				System.out.print("Enter password: ");
				f_pass = sc.next();
				CallableStatement cst = con.prepareCall("{call F_Login(?,?)}");
				cst.setInt(1, f_id);
				cst.setString(2, f_pass);
				r = cst.executeQuery();

				if (r.next()) {
					correct_pass = true;

					String Date_Time = Date_Time();
					String a[] = Date_Time.split(" ");
					Date = a[0];
					Time = a[1];

					System.out.println(
							"-------------------------------------------------------------------------------------------------");
					System.out.println("                                     Login successful.");
					System.out.println(
							"-------------------------------------------------------------------------------------------------");
					System.out.println(
							"                                                                      Date : " + Date);
					System.out.println(
							"                                                                      Time : " + Time);
					System.out.println("Welcome " + r.getString(3) + " .....!!");
					faculty_options(r);
				} else {
					attemptsLeft--;
					System.out.println("Login failed. Invalid credentials.");
					System.out.println("Enter Again.");
				}
				if (attemptsLeft == 0) {

					System.out.println("You Have Reached Maximum Attempts.\n Please try again Later.\n");
					System.exit(0);
				}
			}
		} else {
			System.out.println("This user id is not valid :");
		}
	}

	private static boolean isStudent_idExists() throws SQLException { // checks student id is exists or not
		System.out.println("Enter login_id : ");
		s_id = sc.nextInt();
		String selectQuery = "SELECT * FROM student WHERE s_id= ?";
		PreparedStatement statement = con.prepareStatement(selectQuery);
		statement.setInt(1, s_id);
		ResultSet resultSet = statement.executeQuery();
		return resultSet.next();
	}

	private static void s_login() throws Exception { // for student login
		boolean correct_pass = false;
		String s_pass = "";
		int attemptsLeft = 4;

		if (isStudent_idExists()) {
			while (correct_pass != true && attemptsLeft > 0) {
				System.out.print("Enter password: ");
				s_pass = sc.next();
				CallableStatement cst = con.prepareCall("{call S_Login(?,?)}");
				cst.setInt(1, s_id);
				cst.setString(2, s_pass);
				r = cst.executeQuery();

				if (r.next()) {
					correct_pass = true;

					String Date_Time = Date_Time();
					String a[] = Date_Time.split(" ");
					Date = a[0];
					Time = a[1];

					System.out.println(
							"-------------------------------------------------------------------------------------------------");
					System.out.println("                                     Login successful.");
					System.out.println(
							"-------------------------------------------------------------------------------------------------");
					System.out.println(
							"                                                                      Date : " + Date);
					System.out.println(
							"                                                                      Time : " + Time);
					System.out.println("Welcome " + r.getString(3) + " .....!!");
					options(r);
				} else {
					attemptsLeft--;
					System.out.println("Login failed. Invalid credentials.");
					System.out.println("Enter Again.");
				}
				if (attemptsLeft == 0) {

					System.out.println("You Have Reached Maximum Attempts.\n Please try again Later.\n");
					System.exit(0);
				}
			}
		} else {
			System.out.println("This user id is not valid :");
		}
	}

	private static void auto_generatePaper(ResultSet r) throws Exception // for auto generating question paper
	{
		System.out.println("enter File name for storing Question Paper: (EX. test1-> Auto_test1)");
		String filename = sc.next();

		File f = new File("Auto_" + filename + ".txt");

		while (f.exists()) { // checks if file already exists or not
			System.out.println("This name already exists , enter another name");
			System.out.println("enter File name for storing Question Paper");
			filename = sc.next();
			f = new File("Auto_" + filename + ".txt");
		}

		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		File f1 = new File("Auto_Solution_" + filename + ".txt");
		BufferedWriter answer_writer = new BufferedWriter(new FileWriter(f1));

		System.out.println("enter subject name :");
		sc.nextLine();
		String sub = sc.nextLine();

		writer.write("Name : " + r.getString(3) + "       SUBJECT : " + sub + "     Set : "
				+ r.getString(5));
		writer.newLine();

		answer_writer.write("Name : " + r.getString(3) + "       SUBJECT : " + sub + "     Set : "
				+ r.getString(5));
		answer_writer.newLine();

		writer.write("\nINSTRUCTIONS\n*All question are compulsory.\n*Figure to the right indicates marks\n");
		try {
			System.out.println("How many Questions Do you Want to Insert?");
			number = sc.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid Input !!");
			writer.close();
			answer_writer.close();
			String sql = "delete from questionpaper where paperfile='Auto_" + filename + ".txt'";
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			sql = "delete from questionpaper where solution_of_paper='Auto_Solution_" + filename + ".txt'";
			Statement st1 = con.createStatement();
			st1.executeUpdate(sql);
			File file = new File("Auto_" + filename + ".txt");
			File filesolution = new File("Auto_Solution" + filename + ".txt");
			if (file.delete() && filesolution.delete()) {
				System.out.println("File deleted !!");
			}
			return;
		}

		boolean flag_no = false;
		String sql = "";
		if (sub.equalsIgnoreCase("java")) {
			sql = "select count(q_no) from java";
		} else if (sub.equalsIgnoreCase("DS")) {
			sql = "select count(q_no) from DS ";

		} else {
			System.out.println("Invalid Input!!");

		}
		Statement st1 = con.createStatement();
		ResultSet r2 = st1.executeQuery(sql);
		while (!flag_no) {
			if (r2.next()) {
				int no_of_que = r2.getInt(1);
				if (no_of_que > number) {         // checks question no. is less than questions from the table
					flag_no = true;
				} else {
					System.out.println("Number of questions are less in Database \nRe-enter number : ");
					number = sc.nextInt();
					flag_no = false;
				}
			} else {
				System.out.println("No questions in database !!");
				flag_no = true;
				break;
			}
		}

		System.out.println("No of questions : " + number);
		int a[] = new int[number];
		for (int i = 0; i < number; i++) {
			a[i] = random();
			System.out.println(a[i]);
		}

		for (int j = 0; j < number; j++) {
			String sql1 = "";
			if (sub.equalsIgnoreCase("java")) {
				sql1 = "select * from java where q_no=" + a[j] + "";
			} else if (sub.equalsIgnoreCase("DS")) {
				sql1 = "select * from DS where q_no=" + a[j] + "";
			}
			Statement st = con.createStatement();
			ResultSet r1 = st.executeQuery(sql1);
			System.out.println(
					"------------------------------------------------------------------------------------------");
			while (r1.next()) {

				System.out.println("Que: " + (j + 1) + " " + r1.getString(2));
				System.out.println("(A). " + r1.getString(3));
				System.out.println("(B). " + r1.getString(4));
				System.out.println("(C). " + r1.getString(5));
				System.out.println("(D). " + r1.getString(6));
				System.out.println("(Answer). " + r1.getString(7));
				writer.newLine();
				writer.write("Que : " + (j + 1) + " " + r1.getString(2));
				writer.newLine();
				writer.write("(A). " + r1.getString(3));
				writer.newLine();
				writer.write("(B). " + r1.getString(4));
				writer.newLine();
				writer.write("(C). " + r1.getString(5));
				writer.newLine();
				writer.write("(D). " + r1.getString(6));
				writer.newLine();

				answer_writer.newLine();
				answer_writer.write("Answer " + (j + 1) + " : " + r1.getString(7));
				answer_writer.newLine();
				System.out.println(
						"------------------------------------------------------------------------------------------");
			}
		}

		writer.write(" \n");
		writer.write("         A L L  T H E  B E S T          ");
		writer.close();
		answer_writer.close();
		String name = "Auto_" + filename + ".txt";
		String solution = "Auto_Solution_" + filename + ".txt";
		String sql1 = "insert into questionpaper (f_id,subject,paperfile,solution_of_paper)  values (?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql1);
		pst.setInt(1, r.getInt(1));
		pst.setString(2, sub);
		pst.setString(3, name);
		pst.setString(4, solution);
		int i = pst.executeUpdate();
		if (i > 0) {
			System.out.println("File successfully inserted in questionpaper table ");
		} else {
			System.out.println("failed to insert in question paper table ");
		}
		System.out.println();
		System.out.println("=> Question paper is saved in Auto_" + filename
				+ ".txt and \n=> It's Solution is saved in Auto_Solution_" + filename + ".txt file ");
		System.out.println();
		System.out.println(
				"-----------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println();
		h1.clear();
	}

	private static void manualy_generatePaper(ResultSet r) throws Exception {

		System.out.println("enter File name for storing Question Paper : ");
		String filename = sc.next();
		File f = new File(filename + ".txt");
		while (f.exists()) { // checks if file already exists or not
			System.out.println("This name already exists !!");
			System.out.println("enter another File name for storing Question Paper : ");
			filename = sc.next();
			f = new File(filename + ".txt");
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		File f1 = new File("Solution_" + filename + ".txt");
		BufferedWriter answer_writer = new BufferedWriter(new FileWriter(f1));
		System.out.println("enter subject name :");
		sc.nextLine();
		String sub = sc.nextLine();

		writer.write("Name : " + r.getString(3) + "       SUBJECT : " + sub + "     Set : "
				+ r.getString(5));
		writer.newLine();

		answer_writer.write("Name : " + r.getString(3) + "       SUBJECT : " + sub + "     Set : "
				+ r.getString(5));
		answer_writer.newLine();

		writer.write(
				"INSTRUCTIONS\n*All question are compulsory.\n*Figure to the right indicates marks\n");
		try {
			System.out.println("How many Questions Do you Want to Insert?");
			number = sc.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid Input !!");
			String sql = "delete from questionpaper where paperfile='Auto_" + filename + ".txt'";
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			sql = "delete from questionpaper where solution_of_paper='Auto_Solution_" + filename + ".txt'";
			Statement st1 = con.createStatement();
			st1.executeUpdate(sql);
			File file = new File("Auto_" + filename + ".txt");
			File filesolution = new File("Auto_Solution" + filename + ".txt");
			if (file.delete() && filesolution.delete()) {
				System.out.println("File deleted !!");
			}
			return;
		}
		sc.nextLine();
		for (int i = 1; i <= number; i++) {
			System.out.println("enter question " + i);
			String que = sc.nextLine();
			System.out.println("enter option A");
			String A = sc.nextLine();
			System.out.println("enter option B");
			String B = sc.nextLine();
			System.out.println("enter option C");
			String C = sc.nextLine();
			System.out.println("enter option D");
			String D = sc.nextLine();
			System.out.println("enter Correct Answer");
			String answer = sc.nextLine();
			writer.newLine();
			writer.write(" (" + i + "). " + que);
			writer.newLine();
			writer.write(" (A)." + A);
			writer.newLine();
			writer.write(" (B)." + B);
			writer.newLine();
			writer.write(" (C)." + C);
			writer.newLine();
			writer.write(" (D)." + D);
			writer.newLine();
			answer_writer.newLine();
			answer_writer.write("Answer " + i + ":  " + answer);
			answer_writer.newLine();
		}
		writer.write(" \n");
		writer.write("         A L L  T H E  B E S T          ");
		writer.close();
		answer_writer.close();
		String name = filename + ".txt";
		String solution = "Solution_" + filename + ".txt";
		String sql1 = "insert into questionpaper (f_id,subject,paperfile,solution_of_paper)  values(?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql1);
		pst.setInt(1, r.getInt(1));
		pst.setString(2, sub);
		pst.setString(3, name);
		pst.setString(4, solution);
		int i = pst.executeUpdate();
		if (i > 0) {
			System.out.println("File successfully inserted in questionpaper table ");
		} else {
			System.out.println("failed to insert in question paper table ");
		}
		System.out.println("=> Question paper is saved in " + filename
				+ ".txt and \n=> it's Solution is saved in Solution_" + filename
				+ ".txt file ");
		System.out.println();
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println();
	}

	private static void options(ResultSet r) throws Exception {
		do {
			try {
				System.out.println("which Subject's exam do you want to give :");
				System.out.println("Press \n(1). JAVA\n(2). DS\n(3). for LOG OUT");
				sub = sc.nextInt();

				switch (sub) {
					case 1: {
						options_for_students(r, "java");
					}
						break;

					case 2: {
						options_for_students(r, "DS");
					}
						break;

					case 3: {
						System.out.println("Log Out");
					}
						break;

					default: {
						System.out.println("Invalid output");
					}
						break;
				}
			} catch (Exception e) {
				System.out.println("Invalid Input!!");
				sc.nextLine();
				System.out.println(
						"--------------------------------------------------------------------------------------------------------------");
			}
		} while (sub != 3);

		System.out.println();
		System.out.println();
	}

	private static void options_for_students(ResultSet r, String sub) throws Exception {
		int Total = 0;
		System.out.println("Are you ready to give exam ?\nenter (1).FOR YES  (2).FOR NO ");
		int responce = sc.nextInt();
		if (responce == 1) {
			System.out.println("enter File name for storing your Question Paper: ");
			String filename = sc.next();
			File f = new File(filename + ".txt");

			while (f.exists()) { // checks if file already exists or not
				System.out.println("This name already exists , enter another name");
				System.out.println("enter File name for storing your Question Paper");
				filename = sc.next();
				f = new File(filename + ".txt");
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter(f));

			System.out.println(
					"----------------------------------------------------------------------------");
			System.out.println("Instructions :");
			System.out.println("*All question are compulsory.\n" +
					"*Figure to the right indicates marks");
			System.out.println(
					"-----------------------------------------------------------------------------");
			System.out.println();

			writer.write(
					"Instructions :\n*All question are compulsory.\n*Figure to the right indicates marks");

			
			int a[] = new int[10];
			for (int i = 0; i < 10; i++) {
				a[i] = random();
				System.out.println(a[i]);
			}

			for (int j = 0; j < 10; j++) {
				boolean ans = false;
				String sql1 = "";
				if (sub.equalsIgnoreCase("java")) {
					sql1 = "select * from java where q_no=" + a[j] + "";
				} else if (sub.equalsIgnoreCase("DS")) {
					sql1 = "select * from DS where q_no=" + a[j] + "";
				} else {
					System.out.println("Invalid Input!!");
				}
				PreparedStatement pst = con.prepareStatement(sql1);
				ResultSet r1 = pst.executeQuery();

				System.out.println(
						"------------------------------------------------------------------------------------------");
				while (r1.next()) {

					System.out.println("Que: " + (j + 1) + " " + r1.getString(2));
					System.out.println("(A). " + r1.getString(3));
					System.out.println("(B). " + r1.getString(4));
					System.out.println("(C). " + r1.getString(5));
					System.out.println("(D). " + r1.getString(6));
					writer.newLine();
					writer.write("Que : " + (j + 1) + " " + r1.getString(2));
					writer.newLine();
					writer.write("(A). " + r1.getString(3));
					writer.newLine();
					writer.write("(B). " + r1.getString(4));
					writer.newLine();
					writer.write("(C). " + r1.getString(5));
					writer.newLine();
					writer.write("(D). " + r1.getString(6));
					writer.newLine();
					String answer = sc.next();
					while (!ans) {

						if (answer.equalsIgnoreCase("A") || answer.equalsIgnoreCase("B")
								|| answer.equalsIgnoreCase("C")
								|| answer.equalsIgnoreCase("D")) {

							if (answer.equalsIgnoreCase(r1.getString(7))) {
								ans = true;
								System.out.println("Congratulation ! Your ans is correct");
								writer.write("Your Answer: " + answer + "\n");
								writer.write("Your Answer is Correct");
								writer.newLine();
								writer.newLine();
								Total++;
							} else {
								ans = true;
								System.out.println("Oops ! your ans is wrong");
								System.out.println("Correct answer is " + r1.getString(7));
								writer.write("Your Answer: " + answer + "\n");
								writer.write("Correct Answer " + ":  " + r1.getString(7));
								writer.newLine();
								writer.write("Your Answer is Wrong ");
								writer.newLine();
							}
						} else {
							System.out.println("Plese enter valid choice ");
							answer = sc.nextLine();
						}
					}
					System.out.println(
							"------------------------------------------------------------------------------------------");
				}
			}
			int marks = 0;
			String data = "";
			String sql1 = "";
			String query = "";
			int total_marks = 0;
			int set = 0;
			if (sub.equalsIgnoreCase("java")) {
				data = "select * from exam where s_id=" + r.getInt(1) + " AND date='" + Date + "'";
				sql1 = "insert into exam (s_id,java,total,date) values (?,?,?,?)";

			} else if (sub.equalsIgnoreCase("DS")) {
				data = "select * from exam where s_id=" + r.getInt(1) + " AND date='" + Date + "'";
				sql1 = "insert into exam (s_id,DS,total,date) values (?,?,?,?)";

			} else {
				System.out.println("Invalid Input!!");
			}

			PreparedStatement pst1 = con.prepareStatement(data);
			ResultSet resultSet = pst1.executeQuery();
			if (resultSet.next()) {
				total_marks = resultSet.getInt(5);
				System.out.println("Previous subject marks : " + marks);
				System.out.println("Previous Total subject marks : " + total_marks);
				set = total_marks + Total;
				System.out.println("set : " + set);
				if (sub.equalsIgnoreCase("java")) {
					marks = resultSet.getInt(3);
					query = "update exam set java=" + (marks + Total) + ",total=" + set + " where s_id="
							+ r.getInt(1) + " AND date='" + Date + "'";
				} else if (sub.equalsIgnoreCase("DS")) {
					marks = resultSet.getInt(4);
					query = "update exam set DS=" + (marks + Total) + ",total=" + set + " where s_id="
							+ r.getInt(1)+ " AND date='" + Date + "'";
				}
				else
				{
					System.out.println("Invalid ...!!");
				}

				PreparedStatement pst2 = con.prepareStatement(query);
				int j = pst2.executeUpdate();
				if (j > 0) {
					System.out.println("Marks successfully updated in student table ");
				} else {
					System.out.println("failed to updated marks in student table ");
				}

			} else {

				PreparedStatement pst = con.prepareStatement(sql1);
				pst.setInt(1, r.getInt(1));
				pst.setInt(2, Total);
				pst.setInt(3, Total);
				pst.setString(4, Date);
				int i = pst.executeUpdate();
				if (i > 0) {
					System.out.println("Marks successfully inserted in student table ");
				} else {
					System.out.println("failed to insert marks in student table ");
				}
			}
			System.out.println("\n" + r.getString(3) + " ,Your total score is " + Total);
			writer.write("\n" + r.getString(3) + " ,Your total score is " + Total);
			// sc.nextLine();
			if (Total < 3) {
				System.out.println("you are failed in this test,better luck next time !..");
				writer.write("you are failed in this test,better luck next time !..");
			}
			writer.close();

		} else {
			System.out
					.println("No problem. Feel free to come back whenever you're ready to win a million!");
		}
		System.out.println();
		h1.clear();
	}

	static HashSet<Integer> h1 = new HashSet<>();

	static int random() { // Generates Question number
		int a = (int) (Math.random() * 21);

		while (a == 0) {
			a = (int) (Math.random() * 21);
		}

		while (h1.contains(a)) {

			a = (int) (Math.random() * 21);
			while (a == 0) {
				a = (int) (Math.random() * 21);
			}

		}
		h1.add(a);
		return a;
	}
}

class QuestionPaper {
	static String Question, A, B, C, D, ans;

	public QuestionPaper(String question, String a, String b, String c, String d, String ans1) {
		Question = question;
		A = a;
		B = b;
		C = c;
		D = d;
		ans = ans1;
	}

	@Override
	public String toString() {
		return "QuestionPaper [Question=" + Question + ", A=" + A + ", B=" + B + ", C=" + C + ", D=" + D + ", ans="
				+ ans + "]";
	}

	public static String getQuestion() {
		return Question;
	}

	public static void setQuestion(String question) {
		Question = question;
	}

	public static String getA() {
		return A;
	}

	public static void setA(String a) {
		A = a;
	}

	public static String getB() {
		return B;
	}

	public static void setB(String b) {
		B = b;
	}

	public static String getC() {
		return C;
	}

	public static void setC(String c) {
		C = c;
	}

	public static String getD() {
		return D;
	}

	public static void setD(String d) {
		D = d;
	}

	public static String getAns() {
		return ans;
	}

	public static void setAns(String ans) {
		QuestionPaper.ans = ans;
	}

}

class Marksheet {  
	static String filename;                                       //Marksheet generation
	Scanner sc = new Scanner(System.in);

	void generateMarksheet() throws Exception{
		try {
			System.out.println("Enter file name for saving marksheet : (EX. khushi -> Marksheet_khushi.txt)");
			filename=sc.next();
			BufferedWriter writer=new BufferedWriter(new FileWriter("Marksheet_"+filename+".txt"));
			int n, sem;
			double total = 0, per, credit = 0, SP = 0, cr = 0;
			String name, SPI = "0", rollno;
			Scanner sc = new Scanner(System.in);
			System.out.println();
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------");
			System.out.println(
					"                              M a r k s h e e t  G e n e r a t i o n   S y s t e m                                  ");
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("....................WELCOME TO MARKSHEET GENERATION SYSTEM....................");
			System.out.println();
			System.out.println();
                                                         // taking input
			System.out.println("enter semester:"); 
			sem = sc.nextInt();
			System.out.println("enter the Student roll no:");
			rollno = sc.next();
			System.out.println("enter the Student name:");
			sc.nextLine();
			name = sc.nextLine();
			name = name.toUpperCase();
			int x = name.length();
			System.out.println("enter no of subject:");
			n = sc.nextInt();
			String marks[] = new String[n];
			String sub[] = new String[n];
			double a[] = new double[n];
			System.out.println("enter marks out of 100:" + "\nFor absent Students write :" + "AB");
			for (int i = 0; i < n; i++) {
				System.out.println("enter sub" + (i + 1) + "=");
				sc.nextLine();
				sub[i] = sc.nextLine();
				sub[i] = sub[i].toUpperCase();
				if (sub[i].equalsIgnoreCase("Maths")) {
					credit = 6;
				} else if (sub[i].equalsIgnoreCase("Physics")) {
					credit = 4;
				} else if (sub[i].equalsIgnoreCase("Java")) {
					credit = 6;
				} else if (sub[i].equalsIgnoreCase("S.E") || sub[i].equalsIgnoreCase("Software Engineering")) {
					credit = 4;
				} else if (sub[i].equalsIgnoreCase("E.S") || sub[i].equalsIgnoreCase("Environment Science")) {
					credit = 0;
				} else if (sub[i].equalsIgnoreCase("I.O.T") || sub[i].equalsIgnoreCase("CWS")) {
					credit = 2;
				} else {
					credit = 0;
				}
				System.out.println("enter marks" + (i + 1) + "=");
				marks[i] = sc.next();
				if (marks[i].equalsIgnoreCase("AB") || Integer.parseInt(marks[i]) <= 100) {

					for (int l = 0; l < n; l++) {
						if (marks[i].equalsIgnoreCase("AB")) {
							marks[i] = "AB";
							a[l] = 0;
						} else {
							a[l] = Integer.parseInt(marks[i]);
						}
					}
					total = total + a[i];
				} else {
					System.out.println("enter valid marks");
					marks[i] = sc.next();
				}
				SP = SP + (a[i] * credit);
				cr = cr + credit;
			}
			double s = Double.parseDouble(SPI);
			s = SP / (cr * 10);
			SPI = Double.toString(s);

			// for display marksheet
			System.out.println();
			System.out.println("**************************************************");
			System.out.println("                  L.J UNIVERSITY                  ");
			System.out.println("**************************************************");
			System.out.println();
			System.out.println("__________________________________________________");
			System.out.print("|STUDENT NAME |" + name);

			for (int b = (34 - x); b > 0; b--) {
				System.out.print(" ");
			}
			System.out.println("|");
			System.out.println("__________________________________________________");
			if (rollno.length() == 1) {
				System.out.println("|ROLL NO      |" + rollno + "                                  |");
			} else if (rollno.length() == 2) {
				System.out.println("|ROLL NO      |" + rollno + "                                |");
			} else if (rollno.length() == 3) {
				System.out.println("|ROLL NO      |" + rollno + "                              |");
			}
			System.out.println("__________________________________________________");
			System.out.println("|SEM          |" + sem + "                                 |");
			System.out.println("__________________________________________________");
			System.out.println("|SUBJECT                       |" + "MARKS            |");
			System.out.println("__________________________________________________");
			System.out.println();
			System.out.println();
			for (int i = 0; i < n; i++) {
				System.out.print("|" + sub[i]);
				int y = sub[i].length();
				for (int b = (30 - y); b > 0; b--) {
					System.out.print(" ");
				}
				System.out.print("|");
				System.out.print(marks[i]);
				int m = marks[i].length();
				for (int b = (17 - m); b > 0; b--) {
					System.out.print(" ");
				}
				System.out.println("|");
				System.out.println("--------------------------------------------------");
			}
			per = (total * 100) / (n * 100);
			System.out.println("__________________________________________________");
			System.out.println("|TOTAL MARKS       |" + total + "                        |");
			System.out.println("__________________________________________________");
			String b = Double.toString(per);
			if (b.length() > 4) {
				System.out.println("|PERCENTAGE        |" + b.charAt(0) + b.charAt(1) + b.charAt(2) + b.charAt(3)
						+ b.charAt(4) + "                        |");
			} else {
				System.out.println("|PERCENTAGE        |" + per + "                         |");
			}
			System.out.println("__________________________________________________");
			if (per > 80) {
				System.out.println("|GRADE             | A                           |");
				System.out.println("__________________________________________________");
			} else if (per >= 60) {
				System.out.println("|GRADE             | B                           |");
				System.out.println("__________________________________________________");
			} else if (per >= 45) {
				System.out.println("|GRADE             | C                           |");
				System.out.println("__________________________________________________");
			} else if (per >= 35) {
				System.out.println("|GRADE             | D                           |");
				System.out.println("__________________________________________________");
			} else {
				System.out.println("|RESULT            |FAIL                         |");
				System.out.println("__________________________________________________");
			}
			if (SPI.length() >= 4) {
				System.out
						.println("|SPI               |" + SPI.charAt(0) + SPI.charAt(1) + SPI.charAt(2) + SPI.charAt(3)
								+ SPI.charAt(4) + "                        |");
			} else if (SPI.length() >= 2 && SPI.length() < 4) {
				System.out.println("|SPI               |" + SPI.charAt(0) + SPI.charAt(1) + SPI.charAt(2)
						+ "                          |");
			} else {
				System.out.println("|SPI               |" + SPI + "                         |");
			}
			System.out.println("__________________________________________________");

			writer.write("**************************************************");
			writer.newLine();
			writer.write("                  L.J UNIVERSITY                  ");
			writer.newLine();
			writer.write("**************************************************");
			writer.newLine();
			writer.newLine();
			writer.write("__________________________________________________");
			writer.newLine();
			writer.write("|STUDENT NAME |" + name);
			writer.newLine();
			for (int b1 = (34 - x); b1 > 0; b1--) {
				writer.write(" ");
			}
			writer.write("|");
			writer.newLine();
			writer.write("__________________________________________________");
			writer.newLine();
			if (rollno.length() == 1) {
				writer.write("|ROLL NO      |" + rollno + "                                   |");
			} else if (rollno.length() == 2) {
				writer.write("|ROLL NO      |" + rollno + "                                |");
			} else if (rollno.length() == 3) {
				writer.write("|ROLL NO      |" + rollno + "                               |");
			}
			writer.newLine();
			writer.write("__________________________________________________");
			writer.newLine();
			writer.write("|SEM          |" + sem + "                                 |");
			writer.newLine();
			writer.write("__________________________________________________");
			writer.newLine();
			writer.write("|SUBJECT                       |" + "MARKS            |");
			writer.newLine();
			writer.write("__________________________________________________");
			writer.newLine();

			for (int i = 0; i < n; i++) {
				writer.write("|" + sub[i]);
				int y = sub[i].length();
				for (int b1 = (30 - y); b1 > 0; b1--) {
					writer.write(" ");
				}
				writer.write("|");
				writer.write(marks[i]);
				int m = marks[i].length();
				for (int b1 = (17 - m); b1 > 0; b1--) {
					writer.write(" ");
				}
				writer.write("|");
				writer.newLine();
				writer.write("--------------------------------------------------");
				writer.newLine();
			}
			per = (total * 100) / (n * 100);
			writer.newLine();
			writer.write("__________________________________________________");
			writer.newLine();
			writer.write("|TOTAL MARKS       |" + total + "                        |");
			writer.newLine();
			writer.write("__________________________________________________");
			writer.newLine();
			String b1 = Double.toString(per);
			if (b1.length() > 4) {
				writer.write("|PERCENTAGE        |" + b1.charAt(0) + b1.charAt(1) + b1.charAt(2) + b1.charAt(3)
						+ b1.charAt(4) + "                        |");
			} else {
				writer.write("|PERCENTAGE        |" + per + "                         |");
			}
			writer.newLine();
			writer.write("__________________________________________________");
			writer.newLine();
			if (per > 80) {
				writer.write("|GRADE             | A                           |");
				writer.newLine();
				writer.write("__________________________________________________");
			} else if (per >= 60) {
				writer.write("|GRADE             | B                           |");
				writer.newLine();
				writer.write("__________________________________________________");
			} else if (per >= 45) {
				writer.write("|GRADE             | C                           |");
				writer.newLine();
				writer.write("__________________________________________________");
			} else if (per >= 35) {
				writer.write("|GRADE             | D                           |");
				writer.newLine();
				writer.write("__________________________________________________");
			} else {
				writer.write("|RESULT            |FAIL                         |");
				writer.newLine();
				writer.write("__________________________________________________");
			}
			writer.newLine();
			if (SPI.length() >= 4) {
			writer.write("|SPI               |" + SPI.charAt(0) + SPI.charAt(1) + SPI.charAt(2) + SPI.charAt(3)
								+ SPI.charAt(4) + "                        |");
			} else if (SPI.length() >= 2 && SPI.length() < 4) {
				writer.write("|SPI               |" + SPI.charAt(0) + SPI.charAt(1) + SPI.charAt(2)
						+ "                          |");
			} else {
				writer.write("|SPI               |" + SPI + "                         |");
			}
			writer.newLine();
			writer.write("__________________________________________________");
			writer.newLine();
			writer.close();

		} catch (Exception e) {
			System.out.println(" Marksheet Generated Successfully !!..");
		}
	}
}