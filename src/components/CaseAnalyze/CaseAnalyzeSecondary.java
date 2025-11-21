package components.caseanalyze;

import components.map.Map;
import components.set.Set;
import components.simplewriter.SimpleWriter;

/**
 * Abstract secondary implementation for the CaseAnalyze component.
 *
 * Implements enhanced (secondary) methods and Object methods
 * using only kernel and Standard methods.
 *
 * @author Jeng(Zizheng) Zhuang
 * @version 2025.11.06
 */
public abstract class CaseAnalyzeSecondary implements CaseAnalyze {

    /*
     * ------------------------------------------------------------
     * Secondary methods from the enhanced interface
     * ------------------------------------------------------------
     */

    @Override
    public final void analyzeClues() {
        // Pre-checks
        if (this.suspectCount() == 0) {
            return; // nothing to update
        }

        int clueCount = this.clueCount();

        /*
         * Strategy:
         * Use suspectNames() to iterate through suspects
         * and set new suspicion levels accordingly.
         * This avoids modifying this while iterating.
         */
        Set<String> names = this.suspectNames();
        Set<String> temp = names.newInstance();
        temp.transferFrom(names);

        while (temp.size() > 0) {
            String name = temp.removeAny();
            if (this.hasSuspect(name)) {
                int old = this.getSuspicion(name);
                this.setSuspicion(name, old + clueCount);
            }
        }
    }

    @Override
    public final void generateReport(SimpleWriter out) {
        // Check precondition
        if (out == null) {
            throw new IllegalArgumentException("out must not be null");
        }

        out.println("=== Case Report ===");
        out.println("Total clues: " + this.clueCount());
        out.println("Suspect List:");

        Set<String> names = this.suspectNames();
        Set<String> temp = names.newInstance();
        temp.transferFrom(names);

        while (temp.size() > 0) {
            String name = temp.removeAny();
            int suspicion = this.getSuspicion(name);
            out.println(" - " + name + ": " + suspicion);
        }

        out.println("====================");
    }

    /*
     * ------------------------------------------------------------
     * Common Object methods (using only kernel methods)
     * ------------------------------------------------------------
     */

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{clues=").append(this.clueCount()).append(", suspects=[");

        Set<String> names = this.suspectNames();
        Set<String> temp = names.newInstance();
        temp.transferFrom(names);

        boolean first = true;
        while (temp.size() > 0) {
            String name = temp.removeAny();
            int suspicion = this.getSuspicion(name);
            if (!first) {
                sb.append(", ");
            }
            first = false;
            sb.append(name).append("=").append(suspicion);
        }

        sb.append("]}");
        return sb.toString();
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CaseAnalyze)) {
            return false;
        }

        CaseAnalyze other = (CaseAnalyze) obj;

        // Compare clue count
        if (this.clueCount() != other.clueCount()) {
            return false;
        }

        // Compare suspect counts
        if (this.suspectCount() != other.suspectCount()) {
            return false;
        }

        // Compare every suspect’s suspicion
        Set<String> names = this.suspectNames();
        Set<String> temp = names.newInstance();
        temp.transferFrom(names);

        while (temp.size() > 0) {
            String name = temp.removeAny();
            if (!other.hasSuspect(name)) {
                return false;
            }
            if (this.getSuspicion(name) != other.getSuspicion(name)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int h = 1;
        h = 31 * h + this.clueCount();

        Set<String> names = this.suspectNames();
        Set<String> temp = names.newInstance();
        temp.transferFrom(names);

        while (temp.size() > 0) {
            String name = temp.removeAny();
            int suspicion = this.getSuspicion(name);
            h = 31 * h + name.hashCode();
            h = 31 * h + Integer.hashCode(suspicion);
        }

        return h;
    }
}