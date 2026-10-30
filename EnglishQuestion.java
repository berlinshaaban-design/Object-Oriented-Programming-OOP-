public class EnglishQuestion extends Question {
    private String hint;

    // Constructor
    public EnglishQuestion(String content, int level, char answer, String[] choices, String hint) {
        super(content, level, answer, choices);
        this.hint = hint;
    }

    // Getter for hint
    public String getHint() {
        return hint;
    }

    // Override the getAdditionalInfo method to return the hint
    @Override
    public String getAdditionalInfo() {
        return hint;
    }

    // Override toString method for easy printing
    @Override
    public String toString() {
        return String.format("%s\nHint: %s", super.toString(), hint);
    }
}
