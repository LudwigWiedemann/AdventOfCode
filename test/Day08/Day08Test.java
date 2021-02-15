package Day08;

import AdventOfCodePcg.Day08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

    public class Day08Test {

        @Test
        void part1() {
            assertEquals(1563, new Day08("puzzles/Day08.txt").part1());

        }

        @Test
        void part2() {
            assertEquals(8, new Day08().part2());
        }

}
