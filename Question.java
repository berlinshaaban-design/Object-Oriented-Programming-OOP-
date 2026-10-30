public abstract class Question implements Comparable<Question> {
	private String content;
	private int level;
	private char answer;
	private String[] choices;

	// Constructor
	public Question(String content, int level, char answer, String[] choices) {
		// Validate that level is between 1 and 10
		if (level < 1 || level > 10) {
			throw new NotOnRangeException("Level must be between 1 and 10");
		}
		this.content = content;
		this.level = level;
		this.answer = answer;
		this.choices = choices;
	}

	// Getter for content
	public String getContent() {
		return content;
	}

	// Getter for level
	public int getLevel() {
		return level;
	}

	// Getter for answer
	public char getAnswer() {
		return answer;
	}

	// Getter for choices
	public String[] getChoices() {
		return choices;
	}

	// Method to compare questions based on their level
	@Override
	public int compareTo(Question other) {
		return Integer.compare(this.level, other.level);
	}

	// Abstract method to be implemented by subclasses
	public abstract String getAdditionalInfo();
}
