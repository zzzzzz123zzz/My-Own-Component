package components.caseanalyze;

import components.map.Map;
import components.set.Set;
import components.standard.Standard;

/**
 * Kernel interface for the CaseAnalyze component.
 *
 * Defines the minimal operations needed to model a case analysis system with
 * suspects and clues.
 *
 * @author Jeng(Zizheng) Zhuang
 * @version 2025.10.22
 */
public interface CaseAnalyzeKernel extends Standard<CaseAnalyze> {

    /**
     * Adds a suspect with suspicion level 0 if not already present.
     *
     * @param name
     *            the suspect's name
     * @requires name != null
     * @updates this
     * @ensures if \old(!this.hasSuspect(name)) then this.hasSuspect(name) and
     *          this.getSuspicion(name) = 0
     */
    void addSuspect(String name);

    /**
     * Removes a suspect from this case if present.
     *
     * @param name
     *            the suspect's name
     * @requires name != null
     * @updates this
     * @ensures !this.hasSuspect(name)
     */
    void removeSuspect(String name);

    /**
     * Adds a clue to this case.
     *
     * @param clue
     *            the clue text
     * @requires clue != null
     * @updates this
     * @ensures this.clueCount() = #this.clueCount() + 1
     */
    void addClue(String clue);

    /**
     * Reports whether a suspect exists in this case.
     *
     * @param name
     *            the suspect's name
     * @return true if suspect exists
     * @requires name != null
     */
    boolean hasSuspect(String name);

    /**
     * Returns the suspicion level for a given suspect.
     *
     * @param name
     *            the suspect's name
     * @return the suspicion level
     * @requires this.hasSuspect(name)
     */
    int getSuspicion(String name);

    /**
     * Returns the number of suspects.
     *
     * @return number of suspects
     */
    int suspectCount();

    /**
     * Returns the number of clues.
     *
     * @return number of clues
     */
    int clueCount();

    /**
     * Clears this case (removes all suspects and clues).
     *
     * @updates this
     * @ensures this.suspectCount() = 0 and this.clueCount() = 0
     */
    @Override
    void clear();

    /**
     * Removes and returns an arbitrary suspect entry as a Map.Pair(name,
     * suspicion).
     *
     * @requires this.suspectCount() > 0
     * @updates this
     * @ensures this.suspectCount() = #this.suspectCount() - 1
     */
    Map.Pair<String, Integer> removeAnySuspect();

    /**
     * Sets the suspicion level for an existing suspect.
     *
     * @requires this.hasSuspect(name)
     * @updates this
     * @ensures this.getSuspicion(name) = suspicion
     */
    void setSuspicion(String name, int suspicion);

    /**
     * Returns a (component) Set containing the names of all suspects (a copy).
     *
     * @return a Set<String> of suspect names
     * @ensures no change to this
     */
    Set<String> suspectNames();
}