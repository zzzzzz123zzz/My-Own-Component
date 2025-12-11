import components.caseanalyze.CaseAnalyze;
import components.caseanalyze.CaseAnalyze1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Demo 1: Basic detective case workflow.
 *
 * Shows how to use the CaseAnalyze component in a simple investigation: add
 * suspects add clues analyze clues generate a report
 */
public final class CaseAnalyzeDemo1 {

    private CaseAnalyzeDemo1() {
        // Private constructor to prevent instantiation
    }

    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        out.println("=== Demo 1: Basic Case Analysis ===");

        CaseAnalyze caseFile = new CaseAnalyze1L();

        // Add suspects
        caseFile.addSuspect("Alice");
        caseFile.addSuspect("Bob");

        // Add clues
        caseFile.addClue("Footprint");
        caseFile.addClue("Broken glass");
        caseFile.addClue("Witness testimony");

        // Analyze suspicions
        caseFile.analyzeClues();

        // Print report
        caseFile.generateReport(out);

        out.println("Demo 1 complete!");
        out.close();
    }
}