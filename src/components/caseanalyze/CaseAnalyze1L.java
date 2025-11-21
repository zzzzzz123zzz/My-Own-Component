package components.caseanalyze;

import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;

/*
 * Representation:
 *   private Map<String, Integer> suspects;
 *   private Set<String> clues;
 *
 * Convention:
 *   suspects != null
 *   clues != null
 *   All keys in suspects are non-null Strings
 *   All values in suspects (suspicion levels) are >= 0
 *
 * Correspondence:
 *   this.suspectCount() = suspects.size()
 *   this.clueCount() = clues.size()
 *   this.getSuspicion(name) = suspects.value(name)
 */

/**
 * Kernel implementation of {@code CaseAnalyze}.
 *
 * <p>This implementation uses an OSU {@code Map<String, Integer>} to store
 * suspect names with their suspicion levels, and an OSU {@code Set<String>}
 * to store clues. The representation follows the convention and correspondence
 * documented above.</p>
 *
 * <p>This class only implements kernel and standard methods. All secondary
 * methods are inherited from {@code CaseAnalyzeSecondary}.</p>
 *
 * @author Jeng(Zizheng) Zhuang
 * @version 2025.11.20
 */
public class CaseAnalyze1L extends CaseAnalyzeSecondary {

    /**
     * Mapping from suspect names to their suspicion levels.
     */
    private Map<String, Integer> suspects;

    /**
     * Set of clues added to this case.
     */
    private Set<String> clues;

    /**
    * Default constructor: initializes an empty case.
    */
    public CaseAnalyze1L() {
        this.suspects = new Map1L<>();
        this.clues = new Set1L<>();
    }

    /**
     * Creates and returns a new instance of this class.
     *
     * @return a fresh {@code CaseAnalyze1L} object
     */
    @Override
    public CaseAnalyze newInstance() {
        return new CaseAnalyze1L();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSuspect(String name) {
        if (!this.suspects.hasKey(name)) {
            this.suspects.add(name, 0);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeSuspect(String name) {
        if (this.suspects.hasKey(name)) {
            this.suspects.remove(name);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addClue(String clue) {
        this.clues.add(clue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasSuspect(String name) {
        return this.suspects.hasKey(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSuspicion(String name) {
        return this.suspects.value(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int suspectCount() {
        return this.suspects.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int clueCount() {
        return this.clues.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.suspects.clear();
        this.clues.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map.Pair<String, Integer> removeAnySuspect() {
        return this.suspects.removeAny();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSuspicion(String name, int suspicion) {
        this.suspects.replaceValue(name, suspicion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> suspectNames() {
        Set<String> result = new Set1L<>();

        Map<String, Integer> temp = this.suspects.newInstance();
        temp.transferFrom(this.suspects);

        while (temp.size() > 0) {
            Map.Pair<String, Integer> p = temp.removeAny();
            result.add(p.key());
        }

        return result;
    }
}