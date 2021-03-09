

import aoc2020.Day09;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Day09Test {

    @Test
    void part1() {
        assertEquals(new Day09("puzzles/Day09.txt").part1(25), 31161678);
        assertEquals(new Day09().part1(5), 127);

    }

    @Test
    void part2() {
        assertEquals(5453868, new Day09("puzzles/Day09.txt").part2());
    }

}
