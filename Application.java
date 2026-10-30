import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Application {
	private List<Student> students;
	private Vector<Question> questions;
	private List<Practice> practices;

	// Constructor
	public Application(String fileName) {
		this.students = new ArrayList<>();
		this.questions = new Vector<>();
		this.practices = new ArrayList<>();
		importQuestions(fileName);
		Collections.sort(questions); // Sort questions after importing
	}

	// Method to import questions from a file
	public void importQuestions(String fileName) {
		File fp = new File(fileName);
		Vector<String> FileLines = new Vector<String>();
		try {
			Scanner sc = new Scanner(fp);
			if (sc.hasNextLine())
				sc.nextLine(); // Skip the header line
			while (sc.hasNextLine()) {
				FileLines.add(sc.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String line : FileLines) {
			String[] splits = line.split("\t");
			String[] choices = {splits[4], splits[5], splits[6], splits[7]};
			if (splits[8].equals("")) {
				// Create an EnglishQuestion
				try {
					Question q = new EnglishQuestion(splits[1], Integer.valueOf(splits[2]), splits[3].charAt(0), choices, splits[9]);
					questions.add(q);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				// Create a QuantitativeQuestion
				try {
					Question q = new QuantitativeQuestion(splits[1], Integer.valueOf(splits[2]), splits[3].charAt(0), choices, splits[8]);
					questions.add(q);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}


	// Method to add a student to the application
	public void addStudent(Student student) {
		students.add(student);
	}

	// Method to add a question to the application
	public void addQuestion(Question question) {
		questions.add(question);
	}

	// Method to create a practice session for a student based on their email
	public void createPractice(String email) {
		Student student = findStudentByEmail(email);
		if (student == null) {
			System.out.println("Student with email " + email + " does not exist.");
			return;
		}

		// Generate practice questions based on student's level
		Vector<Question> practiceQuestions = generatePracticeQuestions(student);

		// Create practice session
		Practice practice = new Practice(student, practiceQuestions);
		practices.add(practice); // Add practice to list of practices

		// Run the practice session
		practice.runPractice();
	}

	// Method to find a student by email
	private Student findStudentByEmail(String email) {
		for (Student student : students) {
			if (student.getEmail().equals(email)) {
				return student;
			}
		}
		return null; // Student not found
	}

	// Method to generate practice questions based on student's level
	private Vector<Question> generatePracticeQuestions(Student student) {
		Vector<Question> selectedQuestions = new Vector<>();
		int student_level = student.getStudentLevel();
		int current_level = 1;
		int lower = 0;
		int upper = 0;
		int last_rank_question_number = 0;
		ArrayList<Integer> question_numbers = new ArrayList<Integer>();

		// Determine the range of questions based on levels
		for (int i = 0; i < questions.size(); i++) {
			if (questions.elementAt(i).getLevel() > current_level) {
				question_numbers.add((int)(Math.random() * (i - last_rank_question_number) + last_rank_question_number));
				last_rank_question_number = i;
				if (current_level == student_level && questions.elementAt(i).getLevel() > student_level) {
					upper = i;
				}
				current_level = questions.elementAt(i).getLevel();
				if (current_level == student_level) {
					lower = i;
				}
			}
		}
		question_numbers.add((int)(Math.random() * (questions.size() - last_rank_question_number) + last_rank_question_number));

		// Select random questions based on the student's level
		for (int i = 0; i < 4; i++) {
			if (student_level == 1)
				question_numbers.add((int)(Math.random() * upper));
			else
				question_numbers.add((int)(Math.random() * lower));
		}
		for (int i = 0; i < 4; i++) {
			if (student_level == 10)
				question_numbers.add((int)(Math.random() * (upper - lower) + lower));
			else
				question_numbers.add((int)(Math.random() * (questions.size() - upper) + upper));
		}
		for (int i = 0; i < 4; i++) {
			question_numbers.add((int)(Math.random() * (upper - lower) + lower));
		}
		for (Integer number : question_numbers)
			selectedQuestions.add(questions.elementAt(number));

		return selectedQuestions;
	}

	// Method to print the best students based on criteria

	public void bestStudents() {
		int maxGrade = 0;
		Student bestGradeStudent = null;
		double maxRevenue = 0.0;
		Student bestRevenueStudent = null;

		// Logic to find the best students based on total grade and total revenue
		for (Student student : students) {
			if (student.getTotalGrade() >= maxGrade) {
				bestGradeStudent = student;
				maxGrade = student.getTotalGrade();
			}
			if (student.getTotalRevenue() >= maxRevenue) {
				bestRevenueStudent = student;
				maxRevenue = student.getTotalRevenue();
			}
		}

		if (bestGradeStudent != null) {
			System.out.println("Best student by grade is: " + bestGradeStudent.getFirstName() + " " + bestGradeStudent.getLastName() + " with grade: " + bestGradeStudent.getTotalGrade());
		}

		if (bestRevenueStudent != null) {
			System.out.println("Best student by revenue is: " + bestRevenueStudent.getFirstName() + " " + bestRevenueStudent.getLastName() + " with revenue: " + bestRevenueStudent.getTotalRevenue());
		}
	}


	// Method to get the maximum object from a list of comparable objects
	public static <T extends Comparable<T>> T getMax(List<T> list) {
		if (list.isEmpty()) {
			return null;
		}

		T max = list.get(0);
		for (T item : list) {
			if (item.compareTo(max) > 0) {
				max = item;
			}
		}
		return max;
	}
}
