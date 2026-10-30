import java.util.Collections;
import java.util.Vector;
import java.util.Random;

public class Practice {
	private Student student;
	private Vector<Question> questions;

	// Constructor
	public Practice(Student student, Vector<Question> questions) {
		this.student = student;
		this.questions = new Vector<>(questions);
		Collections.sort(this.questions); // Ensure that the questions are ordered
	}

	// Method to get question by number (1-based index)
	public Question getQuestionByNumber(int number) {
		if (number < 1 || number > questions.size()) {
			throw new NotOnRangeException("Invalid question number.");
		}
		return questions.get(number - 1);
	}

	// Method to get number of questions in the practice
	public int numOfQuestions() {
		return questions.size();
	}

	// Method to simulate running the practice session
	public void runPractice() {
		System.out.println("Welcome to the Practice.");

		int correctQuantitative = 0;
		int quantitativeCount = 0;
		int correctEnglish = 0;
		int englishCount = 0;

		Random rand = new Random();

		for (int i = 0; i < questions.size(); i++) {
			Question q = questions.get(i);
			System.out.printf("Question number %d: %s\n", i + 1, q.getContent());

			// Output additional info based on question type
			if (q instanceof QuantitativeQuestion) {
				QuantitativeQuestion qq = (QuantitativeQuestion) q;
				System.out.printf("Formula: %s\n", qq.getFormula());
				quantitativeCount++;
			} else if (q instanceof EnglishQuestion) {
				EnglishQuestion eq = (EnglishQuestion) q;
				System.out.printf("Hint: %s\n", eq.getHint());
				englishCount++;
			}

			// Simulate student's answer based on their level
			char studentAnswer;
			if (rand.nextDouble() < student.getStudentLevel() / 10.0) {
				// If within student's level, choose the correct answer
				studentAnswer = q.getAnswer();
			} else {
				// Otherwise, choose a random wrong answer
				String[] choices = q.getChoices();
				do {
					studentAnswer = (char) ('a' + rand.nextInt(4));
				} while (studentAnswer == q.getAnswer());
			}

			// Check if the answer is correct
			if (studentAnswer == q.getAnswer()) {
				System.out.println("Correct!");
				if (q instanceof QuantitativeQuestion) {
					correctQuantitative++;
				} else if (q instanceof EnglishQuestion) {
					correctEnglish++;
				}
			} else {
				System.out.println("Incorrect.");
			}
		}

		// Calculate and print the scores
		int mathGrade = quantitativeCount == 0 ? 0 : (correctQuantitative * 100) / quantitativeCount;
		int englishGrade = englishCount == 0 ? 0 : (correctEnglish * 100) / englishCount;

		student.updateGrades(mathGrade, englishGrade);

		System.out.printf("Practice finished. Your math score is: %d.\n", mathGrade);
		System.out.printf("Practice finished. Your English score is: %d.\n", englishGrade);

		// Charge the student for the practice session
		student.practice();
	}
}
