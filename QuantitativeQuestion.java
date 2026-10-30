public class QuantitativeQuestion extends Question {
    private String formula;

    // Constructor
    public QuantitativeQuestion(String content, int level, char answer, String[] choices, String formula) {
        super(content, level, answer, choices);
        this.formula = formula;
    }

    // Getter for formula
    public String getFormula() {
        return formula;
    }

    // Override the getAdditionalInfo method to return the formula
    @Override
    public String getAdditionalInfo() {
        return formula;
    }

    // Override toString method for easy printing
    @Override
    public String toString() {
        return String.format("%s\nFormula: %s", super.toString(), formula);
    }
}
