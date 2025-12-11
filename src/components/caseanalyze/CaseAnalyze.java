package components.caseanalyze;

import components.simplewriter.SimpleWriter;

/**
 * Enhanced interface for the CaseAnalyze component.
 *
 * Extends CaseAnalyzeKernel by adding higher-level operations such as analysis
 * and report generation.
 *
 * @author Jeng(Zizheng) Zhuang
 * @version 2025.10.22
 */
public interface CaseAnalyze extends CaseAnalyzeKernel {

    /**
     * Analyzes clues and updates all suspects’ suspicion levels.
     *
     * Each clue increases every suspect's suspicion by 1.
     *
     * @updates this
     * @ensures for all suspects s: this.getSuspicion(s) = #this.getSuspicion(s)
     *          + #this.clueCount()
     */
    void analyzeClues();

    /**
     * Outputs a summary report of the current case.
     *
     * @param out
     *            the output writer
     * @requires out != null
     * @ensures no change to this
     */
    void generateReport(SimpleWriter out);
}