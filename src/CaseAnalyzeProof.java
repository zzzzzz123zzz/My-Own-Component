import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Proof-of-Concept for the CaseAnalyze component.
 *
 * Demonstrates basic suspect and clue management and analysis using OSU
 * components.
 *
 * @author Jeng(Zizheng) Zhuang
 * @date 2025-10-09
 */
public class CaseAnalyzeProof {

    /**
     * Stores suspects and their suspicion levels.
     */
    private Map<String, Integer> suspects;

    /**
     * Stores clues.
     */
    private Set<String> clues;

    /**
     * Constructor: initializes empty case.
     */
    public CaseAnalyzeProof() {
        this.suspects = new Map1L<>();
        this.clues = new Set1L<>();
    }

    // ------------------------------------------------------------
    // Kernel Methods
    // ------------------------------------------------------------

    /**
     * Adds a suspect with suspicion level 0 if not already present.
     */
    public void addSuspect(String name) {
        if (!this.suspects.hasKey(name)) {
            this.suspects.add(name, 0);
        }
    }

    /**
     * Removes a suspect if they exist.
     */
    public void removeSuspect(String name) {
        if (this.suspects.hasKey(name)) {
            this.suspects.remove(name);
        }
    }

    /**
     * Adds a clue.
     */
    public void addClue(String clue) {
        this.clues.add(clue);
    }

    // ------------------------------------------------------------
    // Secondary Methods
    // ------------------------------------------------------------

    /**
     * Analyzes clues: each clue increases all suspects' suspicion by 1.
     */
    public void analyzeClues() {
        int clueCount = this.clues.size();
        Map<String, Integer> temp = new Map1L<>();

        while (this.suspects.size() > 0) {
            Map.Pair<String, Integer> p = this.suspects.removeAny();
            temp.add(p.key(), p.value() + clueCount);
        }

        this.suspects.transferFrom(temp);
    }

    /**
     * Generates a simple report showing suspects and their suspicion levels.
     */
    public void generateReport(SimpleWriter out) {
        out.println("=== Case Report ===");
        out.println("Total clues: " + this.clues.size());
        out.println("Suspect List:");

        Map<String, Integer> temp = this.suspects.newInstance();
        temp.transferFrom(this.suspects);

        while (temp.size() > 0) {
            Map.Pair<String, Integer> pair = temp.removeAny();
            out.println(" - " + pair.key() + ": " + pair.value());
        }

        out.println("====================");
    }

    // ------------------------------------------------------------
    // Main: Proof-of-Concept demonstration
    // ------------------------------------------------------------

    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        CaseAnalyzeProof case1 = new CaseAnalyzeProof();

        // Step 1: Add suspects
        case1.addSuspect("Kudo Shinichi");
        case1.addSuspect("Mouri Kogoro");
        case1.addSuspect("Kaitou Kid");

        // Step 2: Add clues
        case1.addClue("Footprint near window");
        case1.addClue("Suspicious alibi");
        case1.addClue("Unknown fingerprint");

        // Step 3: Generate initial report
        out.println("Initial case:");
        case1.generateReport(out);

        // Step 4: Analyze clues
        case1.analyzeClues();

        // Step 5: Generate report after analysis
        out.println("\nAfter analysis:");
        case1.generateReport(out);

        out.close();
    }
}