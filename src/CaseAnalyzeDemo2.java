import components.caseanalyze.CaseAnalyze;
import components.caseanalyze.CaseAnalyze1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Demo 2: Advanced Multi-Case Management
 *
 * This example demonstrates a more advanced and system-level use of the
 * CaseAnalyze component. Unlike Demo 1, which handles one simple investigation,
 * this demo focuses on working with multiple case files and performing
 * higher-level operations: Maintain multiple independent CaseAnalyze objects
 * Analyze each case separately Merge cases using transferFrom Compare cases
 * using equals Inspect internal state using toString
 *
 * This demo highlights how the component behaves in scenarios such as case
 * consolidation, data migration, and integrity checking, which are much closer
 * to real forensic systems handling multiple case files.
 *
 */
public final class CaseAnalyzeDemo2 {

    /**
     * Private constructor to prevent instantiation.
     */
    private CaseAnalyzeDemo2() {
    }

    /**
     * Main method to demonstrate managing multiple case files.
     *
     * @param args
     *            command line arguments (not used)
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        out.println("=== Demo 2: Managing Multiple Investigations ===");

        // Create Case A
        CaseAnalyze caseA = new CaseAnalyze1L();
        caseA.addSuspect("David");
        caseA.addSuspect("Emma");
        caseA.addClue("Fingerprint");

        // Create Case B
        CaseAnalyze caseB = new CaseAnalyze1L();
        caseB.addSuspect("Frank");
        caseB.addClue("Security footage");
        caseB.addClue("DNA sample");

        // Analyze each separately
        caseA.analyzeClues();
        caseB.analyzeClues();

        out.println("Case A before merge: " + caseA);
        out.println("Case B before merge: " + caseB);

        // Merge B → A
        out.println("\nMerging Case B into Case A...\n");
        caseA.transferFrom(caseB);

        out.println("Case A AFTER merge: " + caseA);
        out.println("Case B AFTER merge (should be empty): " + caseB);

        // Create a reconstructed copy to check equals()
        CaseAnalyze copy = new CaseAnalyze1L();
        copy.addSuspect("David");
        copy.addSuspect("Emma");
        copy.addSuspect("Frank");
        copy.addClue("Fingerprint");
        copy.addClue("Security footage");
        copy.addClue("DNA sample");

        copy.analyzeClues(); // must match caseA’s suspicion values

        out.println("\nDoes merged case equal the reconstructed copy?");
        out.println("equals() result: " + caseA.equals(copy));

        out.println("\nDemo 2 complete!");
        out.close();
    }
}