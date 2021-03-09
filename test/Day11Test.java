import aoc2020.Day11;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class Day11Test {


    @Test
    void part1() {
        assertEquals(37, new Day11().part1());
    }

    @Test
    void listsAreEqual() {
        List<List<String>> list1 = new ArrayList<>();
        List<List<String>> list2 = new ArrayList<>();
        for (int row = 0; row < 10; row++) {
            List<String> rowList = new ArrayList<>();
            List<String> rowList2 = new ArrayList<>();
            for (int seat = 0; seat < 10; seat++) {
                rowList.add("Hello");
                rowList2.add("Hello");
            }
            list1.add(rowList);
            list2.add(rowList2);
        }
        assertTrue(new Day11().listsAreEqual(list1, list2));
        List<String> makesFalse = new ArrayList<>();
        makesFalse.add("seven");
        list1.add(makesFalse);
        assertFalse(new Day11().listsAreEqual(list1, list2));
    }

    @Nested
    class GetNeighbours {
        @Test
        @DisplayName("works in the middle of the seatmap")
        void inMiddleOfSeatMap() {
            List<List<String>> seatWithNeighbours = createTestSeatMap();
            Collection<String> expected = new ArrayList<>();
            expected.add("#");
            expected.add(".");
            expected.add("L");
            expected.add("L");
            expected.add(".");
            expected.add("#");
            expected.add("L");
            expected.add(".");
            assertEquals(expected.stream().sorted().collect(Collectors.toList()),
                    new Day11().getNeighbours(1, 1, seatWithNeighbours).stream().sorted().collect(Collectors.toList()));

        }
    }


    private List<List<String>> createTestSeatMap() {
        List<List<String>> seatWithNeighbours = new ArrayList<>();
        List<String> rowTop = new ArrayList<>();
        rowTop.add("#");
        rowTop.add(".");
        rowTop.add("L");
        // "." : 1; "#" : 1; "L" : 1;
        List<String> rowMid = new ArrayList<>();
        rowMid.add(".");
        rowMid.add(".");
        rowMid.add("L");
        // "." 1; seat; "L" : 1;
        List<String> rowBot = new ArrayList<>();
        rowBot.add("L");
        rowBot.add("#");
        rowBot.add(".");
        // "L" : 1; "#" : 1; "." : 1;
        seatWithNeighbours.add(rowTop);
        seatWithNeighbours.add(rowMid);
        seatWithNeighbours.add(rowBot);
        return seatWithNeighbours;
    }

    @Test
    void howManyOf() {
        List<String> input = new ArrayList<>();
        input.add("#");
        input.add("L");
        input.add("#");
        input.add(".");
        input.add("L");
        input.add("#");
        input.add("#");
        assertEquals(4, new Day11().howManyOf("#", input));

    }

//    @Test
//    void part2() {
//        assertEquals(8, new Day11().part2());
//    }

}
