package components.caseanalyze;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;
import components.set.Set;

/**
 * Tests for CaseAnalyze1L class.
 */
public class CaseAnalyze1LTest {

    /**
     * Creates a new CaseAnalyze instance.
     *
     * @return new CaseAnalyze1L instance.
     */
    private CaseAnalyze constructor() {
        return new CaseAnalyze1L();
    }

    /*
     * Constructor Tests
     */
    /**
     * Tests that a new CaseAnalyze object starts empty.
     */
    @Test
    public void testConstructorEdge() {
        CaseAnalyze c = this.constructor();
        assertEquals(0, c.suspectCount());
        assertEquals(0, c.clueCount());
    }

    /*
     * addSuspect Tests
     */
    /**
     * Tests adding a single suspect normally.
     */
    @Test
    public void testAddSuspectRoutine() {
        CaseAnalyze c = this.constructor();
        c.addSuspect("Alice");
        assertTrue(c.hasSuspect("Alice"));
        assertEquals(0, c.getSuspicion("Alice"));
    }

    /**
     * Tests adding a duplicate suspect does not increase count.
     */
    @Test
    public void testAddSuspectChallengeDuplicate() {
        CaseAnalyze c = this.constructor();
        c.addSuspect("Bob");
        c.addSuspect("Bob");
        assertEquals(1, c.suspectCount());
    }

    /*
     * removeSuspect Tests
     */
    /**
     * Tests removing a suspect normally.
     */
    @Test
    public void testRemoveSuspectRoutine() {
        CaseAnalyze c = this.constructor();
        c.addSuspect("X");
        c.removeSuspect("X");
        assertFalse(c.hasSuspect("X"));
    }

    /**
     * Tests removing one suspect from multiple.
     */
    @Test
    public void testRemoveSuspectChallengeMultiple() {
        CaseAnalyze c = this.constructor();
        c.addSuspect("A");
        c.addSuspect("B");
        c.addSuspect("C");
        c.removeSuspect("B");
        assertEquals(2, c.suspectCount());
        assertFalse(c.hasSuspect("B"));
    }

    /*
     * addClue Tests
     */
    /**
     * Tests adding one clue normally.
     */
    @Test
    public void testAddClueRoutine() {
        CaseAnalyze c = this.constructor();
        c.addClue("C1");
        assertEquals(1, c.clueCount());
    }

    /**
     * Tests adding multiple clues.
     */
    @Test
    public void testAddClueChallengeMultiple() {
        CaseAnalyze c = this.constructor();
        for (int i = 0; i < 10; i++) {
            c.addClue("C" + i);
        }
        assertEquals(10, c.clueCount());
    }

    /*
     * hasSuspect Tests
     */
    /**
     * Tests hasSuspect on an empty object.
     */
    @Test
    public void testHasSuspectEdgeEmpty() {
        CaseAnalyze c = this.constructor();
        assertFalse(c.hasSuspect("X")); // empty case -> should be false
    }

    /**
     * Tests hasSuspect with one existing and one non-existing suspect.
     */
    @Test
    public void testHasSuspectRoutine() {
        CaseAnalyze c = this.constructor();
        c.addSuspect("Alice");

        assertTrue(c.hasSuspect("Alice")); // present
        assertFalse(c.hasSuspect("Bob")); // not present
    }

    /**
     * Tests hasSuspect with multiple suspects.
     */
    @Test
    public void testHasSuspectChallengeMultiple() {
        CaseAnalyze c = this.constructor();
        c.addSuspect("A");
        c.addSuspect("B");
        c.addSuspect("C");

        assertTrue(c.hasSuspect("B"));
        assertFalse(c.hasSuspect("D")); // ensure unrelated name is false
    }

    /*
     * getSuspicion Tests
     */
    /**
     * Tests that new suspects start with suspicion 0.
     */
    @Test
    public void testGetSuspicionRoutine() {
        CaseAnalyze c = this.constructor();
        c.addSuspect("K");
        assertEquals(0, c.getSuspicion("K"));
    }

    /**
     * Tests setting suspicion to a specific value.
     */
    @Test
    public void testSetSuspicionChallenge() {
        CaseAnalyze c = this.constructor();
        c.addSuspect("H");
        c.setSuspicion("H", 10);
        assertEquals(10, c.getSuspicion("H"));
    }

    /*
     * suspectCount Tests
     */
    /**
     * Tests suspectCount on an empty structure.
     */
    @Test
    public void testSuspectCountEdgeEmpty() {
        CaseAnalyze c = this.constructor();
        assertEquals(0, c.suspectCount());
    }

    /**
     * Tests suspectCount with one suspect.
     */
    @Test
    public void testSuspectCountRoutine() {
        CaseAnalyze c = this.constructor();
        c.addSuspect("Alice");

        assertEquals(1, c.suspectCount());
    }

    /**
     * Tests suspectCount with duplicates.
     */
    @Test
    public void testSuspectCountChallengeMultiple() {
        CaseAnalyze c = this.constructor();
        c.addSuspect("A");
        c.addSuspect("B");
        c.addSuspect("C");
        c.addSuspect("B"); // duplicate, should not increase count

        assertEquals(3, c.suspectCount());
    }

    /*
     * clueCount Tests
     */
    /**
     * Tests clueCount on empty structure.
     */
    @Test
    public void testClueCountEdgeEmpty() {
        CaseAnalyze c = this.constructor();
        assertEquals(0, c.clueCount());
    }

    /**
     * Tests clueCount with one clue.
     */
    @Test
    public void testClueCountRoutine() {
        CaseAnalyze c = this.constructor();
        c.addClue("footprint");

        assertEquals(1, c.clueCount());
    }

    /**
     * Tests clueCount with multiple clues.
     */
    @Test
    public void testClueCountChallengeMultiple() {
        CaseAnalyze c = this.constructor();
        c.addClue("a");
        c.addClue("b");
        c.addClue("c");

        assertEquals(3, c.clueCount());
    }

    /*
     * clear Tests
     */
    /**
     * Tests clear on an empty structure.
     */
    @Test
    public void testClearEdgeEmpty() {
        CaseAnalyze c = this.constructor();
        c.clear();

        assertEquals(0, c.suspectCount());
        assertEquals(0, c.clueCount());
    }

    /**
     * Tests clearing with one suspect and one clue.
     */
    @Test
    public void testClear2() {
        CaseAnalyze c = this.constructor();
        c.addSuspect("A");
        c.addClue("C1");

        c.clear();

        assertEquals(0, c.suspectCount());
        assertEquals(0, c.clueCount());
    }

    /**
     * Tests clearing many suspects and clues.
     */
    @Test
    public void testClearChallengeMany() {
        CaseAnalyze c = this.constructor();

        for (int i = 0; i < 50; i++) {
            c.addSuspect("S" + i);
            c.addClue("C" + i);
        }

        c.clear();

        assertEquals(0, c.suspectCount());
        assertEquals(0, c.clueCount());
    }

    /*
     * removeAnySuspect Tests
     */
    /**
     * Tests removing any suspect from a single-suspect case.
     */
    @Test
    public void testRemoveAnySuspectRoutine() {
        CaseAnalyze c = this.constructor();
        c.addSuspect("A");
        Map.Pair<String, Integer> p = c.removeAnySuspect();
        assertNotNull(p);
        assertEquals(0, c.suspectCount());
    }

    /**
     * Tests removing any suspect from multiple suspects.
     */
    @Test
    public void testRemoveAnySuspectChallengeMultiple() {
        CaseAnalyze c = this.constructor();
        c.addSuspect("A");
        c.addSuspect("B");
        c.addSuspect("C");
        int before = c.suspectCount();
        c.removeAnySuspect();
        assertEquals(before - 1, c.suspectCount());
    }

    /*
     * setSuspicion Tests
     */
    @Test
    public void testSetSuspicionEdge() {
        CaseAnalyze c = this.constructor();
        c.addSuspect("A");

        c.setSuspicion("A", 5);

        assertEquals(5, c.getSuspicion("A"));
    }

    /**
     * Tests setting suspicion normally.
     */
    @Test
    public void testSetSuspicionRoutine() {
        CaseAnalyze c = this.constructor();
        c.addSuspect("Bob");
        c.setSuspicion("Bob", 3);

        // Revise suspicion level
        c.setSuspicion("Bob", 10);

        assertEquals(10, c.getSuspicion("Bob"));
    }

    /**
     * Tests setting suspicion for multiple suspects.
     */
    @Test
    public void testSetSuspicionChallengeMultiple() {
        CaseAnalyze c = this.constructor();

        c.addSuspect("A");
        c.addSuspect("B");
        c.addSuspect("C");

        c.setSuspicion("A", 2);
        c.setSuspicion("B", 5);
        c.setSuspicion("C", 9);

        assertEquals(2, c.getSuspicion("A"));
        assertEquals(5, c.getSuspicion("B"));
        assertEquals(9, c.getSuspicion("C"));
    }

    /*
     * suspectNames Tests
     */
    /**
     * Tests suspectNames returns correct names.
     */
    @Test
    public void testSuspectNamesRoutine() {
        CaseAnalyze c = this.constructor();
        c.addSuspect("A");
        c.addSuspect("B");

        Set<String> result = c.suspectNames();

        assertEquals(2, result.size());
        assertTrue(result.contains("A"));
        assertTrue(result.contains("B"));
    }

    /**
     * Tests suspectNames does NOT mutate the original object.
     */
    @Test
    public void testSuspectNamesNoMutation() {
        CaseAnalyze c = this.constructor();
        c.addSuspect("A");
        c.addSuspect("B");

        int before = c.suspectCount();
        c.suspectNames(); // call should NOT modify c

        assertEquals(before, c.suspectCount());
        assertTrue(c.hasSuspect("A"));
        assertTrue(c.hasSuspect("B"));
    }

    /*
     * transferFrom Tests
     */
    /**
     * Tests transferring content from one instance to another.
     */
    @Test
    public void testTransferFromRoutine() {
        CaseAnalyze c1 = this.constructor();
        CaseAnalyze c2 = this.constructor();

        c2.addSuspect("A");
        c2.addClue("C1");

        c1.transferFrom(c2);

        // c1 now has content
        assertEquals(1, c1.suspectCount());
        assertEquals(1, c1.clueCount());
        assertTrue(c1.hasSuspect("A"));

        // c2 becomes empty
        assertEquals(0, c2.suspectCount());
        assertEquals(0, c2.clueCount());
    }
}