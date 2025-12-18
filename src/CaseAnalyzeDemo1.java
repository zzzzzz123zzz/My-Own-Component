import components.caseanalyze.CaseAnalyze;
import components.caseanalyze.CaseAnalyze1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Demo 1: Basic detective case workflow using the CaseAnalyze component.
 *
 * This example demonstrates a simple use of the component. It shows how a
 * developer could use the component to manage a single investigation: add
 * suspects and clues, Perform analysis to update suspicion levels, and generate
 * a report for the single case. This demo focuses on basic functionality of the
 * component and models the workflow a beginner or small tool might use to track
 * one case at a time.
 *
 * @author Jeng Zhuang
 * @version 2025.12.10
 */
public final class CaseAnalyzeDemo1 {

    /**
     * Private constructor to prevent instantiation.
     */
    private CaseAnalyzeDemo1() {
        // prevent instantiation
    }

    /**
     * Runs the basic demonstration.
     *
     * @param args
     *            command line arguments
     */
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