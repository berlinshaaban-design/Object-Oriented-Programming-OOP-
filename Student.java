public class Student implements Comparable<Student> {
    private String email;
    private String firstName;
    private String lastName;
    private int age;
    private double totalRevenue;
    private int totalGrade;
    private int mathGrade;
    private int englishGrade;
    private int practiceCount;

    // Constructor
    public Student(String email, String firstName, String lastName, int age) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.totalRevenue = 10.0; // Initial fee
        this.setPracticeCount(0);
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public int getTotalGrade() {
        return totalGrade;
    }

    public int getMathGrade() {
        return mathGrade;
    }

    public int getEnglishGrade() {
        return englishGrade;
    }

    public int getStudentLevel() {
        // Calculate student level based on totalGrade
        return Math.max((int)(totalGrade / 100), 1);
    }

    // Method to simulate taking a practice
    public void takePractice(int correctMathQuestions, int totalMathQuestions, int correctEnglishQuestions, int totalEnglishQuestions) {
        setPracticeCount(getPracticeCount() + 1);
        totalRevenue += 2.0; // Fee for each practice

        if (totalMathQuestions > 0) {
            // Calculate math grade as a percentage
            mathGrade = (correctMathQuestions * 100) / totalMathQuestions;
        } else {
            mathGrade = 0;
        }

        if (totalEnglishQuestions > 0) {
            // Calculate English grade as a percentage
            englishGrade = (correctEnglishQuestions * 100) / totalEnglishQuestions;
        } else {
            englishGrade = 0;
        }

        // Calculate total grade with weight for math and English
        totalGrade = mathGrade * 7 + englishGrade * 3;
    }

    // Method to update grades after a practice session
    public void updateGrades(int mathGrade, int englishGrade) {
        this.mathGrade = mathGrade;
        this.englishGrade = englishGrade;
        this.totalGrade = mathGrade * 7 + englishGrade * 3;
    }

    // Method to charge for a practice session
    public void practice() {
        setPracticeCount(getPracticeCount() + 1);
        totalRevenue += 2.0;
    }

    // Method to calculate profit
    public double calculateProfit() {
        // Calculate profit as total revenue minus initial fee (10.0)
        return totalRevenue - 10.0;
    }

    // Method to compare students based on total revenue
    @Override
    public int compareTo(Student other) {
        return Double.compare(this.totalRevenue, other.totalRevenue);
    }

    // Method to print student details
    @Override
    public String toString() {
        return String.format("Student[name=%s %s, email=%s, age=%d, totalRevenue=%.2f, totalGrade=%d, studentLevel=%d]",
                firstName, lastName, email, age, totalRevenue, totalGrade, getStudentLevel());
    }

    public int getPracticeCount() {
        return practiceCount;
    }

    public void setPracticeCount(int practiceCount) {
        this.practiceCount = practiceCount;
    }
}
