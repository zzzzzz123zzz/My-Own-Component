package components.caseanalyze;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * JUnit test for the secondary methods of the {@code CaseAnalyze} component.
 * These tests cover all functionality implemented in
 * {@code CaseAnalyzeSecondary}, including: {@code analyzeClues()},
 * {@code generateReport(SimpleWriter)},{@code toString()} ,
 * {@code equals(Object)}, and {@code hashCode()}.
 * 
 * @author Jeng Zhuang
 * @version 2025.12.10
 */
public class CaseAnalyzeSecondaryTest {

    /**
     * Returns a new instance of {@code CaseAnalyze}.
     *
     * @return fresh CaseAnalyze1L instance
     */
    private CaseAnalyze constructor() {
        return new CaseAnalyze1L();
    }

    /*
     * analyzeClues Tests
     */
    /**
     * Tests analyzeClues when no suspects exist (edge case).
     */
    @Test
    public void testAnalyzeCluesEdgeNoSuspects() {
        CaseAnalyze c = this.constructor();
        c.addClue("A");
        c.analyzeClues(); // Should do nothing
        assertEquals(0, c.suspectCount());
    }

    /**
     * Tests analyzeClues with one suspect and multiple clues.
     */
    @Test
    public void testAnalyzeCluesRoutine() {
        CaseAnalyze c = this.constructor();
        c.addSuspect("A");
        c.addClue("A1");
        c.addClue("A2"); // clueCount = 2

        c.analyzeClues();

        assertEquals(2, c.getSuspicion("A"));
    }

    /**
     * Tests analyzeClues with multiple suspects and multiple clues.
     */
    @Test
    public void testAnalyzeCluesChallengeMultipleSuspects() {
        CaseAnalyze c = this.constructor();

        c.addSuspect("A");
        c.addSuspect("B");
        c.addSuspect("C");

        c.addClue("C1");
        c.addClue("C2");
        c.addClue("C3"); // clueCount = 3

        c.analyzeClues();

        assertEquals(3, c.getSuspicion("A"));
        assertEquals(3, c.getSuspicion("B"));
        assertEquals(3, c.getSuspicion("C"));
    }

    /*
     * generateReport Tests
     */
    /**
     * Ensures generateReport throws IllegalArgumentException when given null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGenerateReportNull() {
        CaseAnalyze c = this.constructor();
        c.generateReport(null);
    }

    /**
     * Tests generateReport produces output and does not mutate the component.
     */
    @Test
    public void testGenerateReportRoutine() {
        CaseAnalyze c = this.constructor();
        c.addSuspect("A");
        c.addClue("C1");

        SimpleWriter out = new SimpleWriter1L();
        c.generateReport(out);

        // No mutation should happen
        assertEquals(1, c.suspectCount());
        assertEquals(1, c.clueCount());
    }

    /*
     * toString Tests
     */
    /**
     * Tests that toString includes expected formatted information.
     */
    @Test
    public void testToStringRoutine() {
        CaseAnalyze c = this.constructor();
        c.addSuspect("A");
        c.setSuspicion("A", 5);

        String s = c.toString();

        // At minimum, the string should contain:
        assertTrue(s.contains("clues="));
        assertTrue(s.contains("suspects"));
        assertTrue(s.contains("A=5"));
    }

    /*
     * equals Tests.
     */
    /**
     * Tests equals returns true when contents match.
     */
    @Test
    public void testEqualsTrue() {
        CaseAnalyze c1 = this.constructor();
        CaseAnalyze c2 = this.constructor();

        c1.addSuspect("A");
        c2.addSuspect("A");

        c1.addClue("C1");
        c2.addClue("C1");

        assertTrue(c1.equals(c2));
    }

    /**
     * Tests equals returns false when suspicion levels differ.
     */
    @Test
    public void testEqualsFalseDifferentSuspicion() {
        CaseAnalyze c1 = this.constructor();
        CaseAnalyze c2 = this.constructor();

        c1.addSuspect("A");
        c1.setSuspicion("A", 2);

        c2.addSuspect("A");
        c2.setSuspicion("A", 5);

        assertFalse(c1.equals(c2));
    }

    /**
     * Tests equals returns false when comparing with a different type.
     */
    @Test
    public void testEqualsFalseDifferentType() {
        CaseAnalyze c1 = this.constructor();
        String s = "Not a CaseAnalyze";
        assertFalse(c1.equals(s));
    }

    /*
     * hashCode Tests
     */
    /**
     * Tests hashCode matches when equals is true.
     */
    @Test
    public void testHashCodeConsistentWithEquals() {
        CaseAnalyze c1 = this.constructor();
        CaseAnalyze c2 = this.constructor();

        c1.addSuspect("A");
        c2.addSuspect("A");

        c1.addClue("C1");
        c2.addClue("C1");

        assertTrue(c1.equals(c2));
        assertEquals(c1.hashCode(), c2.hashCode());
    }
}