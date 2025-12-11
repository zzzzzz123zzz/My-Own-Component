import components.caseanalyze.CaseAnalyze;
import components.caseanalyze.CaseAnalyze1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Demo 2: Managing multiple case files.
 *
 * Shows advanced usage: - two separate investigations - merging using
 * transferFrom - comparing cases with equals - using toString for inspection
 */
public class CaseAnalyzeDemo2 {

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