package aoc2020;

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
        assertEquals(2296, new Day11().part1());
    }

    @Test
    @DisplayName("Lists are equal works")
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
        List<List<String>> seatWithNeighbours = createTestSeatMap();
        @Test
        @DisplayName("Works in the middle of the seatmap")
        void inMiddleOfSeatMap() {
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

        @Test
        @DisplayName("Works in the upper right corner of the seatmap")
        void upperRightCornerOfSeatMap() {
            Collection<String> expected = new ArrayList<>();
            expected.add(".");
            expected.add(".");
            expected.add("L");
            assertEquals(expected.stream().sorted().collect(Collectors.toList()),
                    new Day11().getNeighbours(0, 2, seatWithNeighbours).stream().sorted().collect(Collectors.toList()));
        }

        @Test
        @DisplayName("Works in the upper left corner of the seatmap")
        void upperLeftCornerOfSeatMap() {
            Collection<String> expected = new ArrayList<>();
            expected.add(".");
            expected.add(".");
            expected.add(".");
            assertEquals(expected.stream().sorted().collect(Collectors.toList()),
                    new Day11().getNeighbours(0, 0, seatWithNeighbours).stream().sorted().collect(Collectors.toList()));
        }

        @Test
        @DisplayName("Works in the lower left corner of the seatmap")
        void lowerLeftCornerOfSeatMap() {
            Collection<String> expected = new ArrayList<>();
            expected.add(".");
            expected.add(".");
            expected.add("#");
            assertEquals(expected.stream().sorted().collect(Collectors.toList()),
                    new Day11().getNeighbours(2, 0, seatWithNeighbours).stream().sorted().collect(Collectors.toList()));
        }

        @Test
        @DisplayName("Works in the lowerr right corner of the seatmap")
        void lowerRightCornerOfSeatMap() {
            Collection<String> expected = new ArrayList<>();
            expected.add(".");
            expected.add("#");
            expected.add("L");
            assertEquals(expected.stream().sorted().collect(Collectors.toList()),
                    new Day11().getNeighbours(2, 2, seatWithNeighbours).stream().sorted().collect(Collectors.toList()));
        }
    }

    @Nested
    class GetFirstVisibleSeats {

        @Test
        @DisplayName("works for a seat in the middle of one single row")
        void firstVisibleSeatsInOneRow() {
            List<String> testRow = new ArrayList<>();
            testRow.add(".");
            testRow.add(".");
            testRow.add("#");
            testRow.add(".");
            testRow.add(".");
            testRow.add(".");
            testRow.add("L");
            testRow.add(".");
            testRow.add("L");
            List<String> expected = new ArrayList<>();
            expected.add("#");
            expected.add("L");
            assertEquals(expected, new Day11().getFirstVisibleSeatInRow(6, testRow));
        }

        @Test
        @DisplayName("works for a seat in one single row if the seat is on the start of the row")
        void firstVisibleSeatsInOneRowAtStart() {
            List<String> testRow = new ArrayList<>();
            testRow.add("#");
            testRow.add(".");
            testRow.add("#");
            testRow.add(".");
            testRow.add("L");
            testRow.add(".");
            testRow.add(".");
            testRow.add(".");
            testRow.add("#");
            List<String> expected = new ArrayList<>();
            expected.add("#");
            assertEquals(expected, new Day11().getFirstVisibleSeatInRow(0, testRow));
        }

        @Test
        @DisplayName("works for a seat in one single row if the seat is on the start of the row")
        void firstVisibleSeatsInOneRowAtEnd() {
            List<String> testRow = new ArrayList<>();
            testRow.add("#");
            testRow.add(".");
            testRow.add(".");
            testRow.add("#");
            testRow.add("L");
            testRow.add(".");
            testRow.add(".");
            testRow.add(".");
            testRow.add("#");
            List<String> expected = new ArrayList<>();
            expected.add("L");
            assertEquals(expected, new Day11().getFirstVisibleSeatInRow(8, testRow));
        }
    }

    @Nested
    class CalculateSeatState {

        @Test
        @DisplayName("Works for '.' ")
        void dot() {
            List<List<String>> testSeatMap = createTestSeatMap();
            assertEquals(".", new Day11().calculateSeatState(1, 1, testSeatMap));
        }

        @Test
        @DisplayName("Works for an empty seat with no occupied seats around")
        void emptySeatNoOccupied() {
            List<List<String>> testSeatMap = createTestSeatMap(
                    "L", ".", "L",
                    "L", "L", ".",
                    ".", "L", ".");
            assertEquals("#", new Day11().calculateSeatState(1, 1, testSeatMap));
        }

        @Test
        @DisplayName("Works for an empty seat with occupied seats around")
        void emptySeatWithOccupied() {
            List<List<String>> testSeatMap = createTestSeatMap(
                    "L", ".", "L",
                    "L", "L", ".",
                    ".", "#", ".");
            assertEquals("L", new Day11().calculateSeatState(1, 1, testSeatMap));
        }

        @Test
        @DisplayName("Works for an occupied seat with only 3 occupied seats around")
        void occupiedSeatWithThreeOccupied() {
            List<List<String>> testSeatMap = createTestSeatMap(
                    "L", ".", "#",
                    "#", "#", ".",
                    ".", "#", ".");
            assertEquals("#", new Day11().calculateSeatState(1, 1, testSeatMap));
        }

        @Test
        @DisplayName("Works for an occupied seat with 4 occupied seats around")
        void occupiedSeatWithFourOccupied() {
            List<List<String>> testSeatMap = createTestSeatMap(
                    "L", ".", "#",
                    "#", "#", "#",
                    ".", "#", ".");
            assertEquals("L", new Day11().calculateSeatState(1, 1, testSeatMap));
        }

        @Test
        @DisplayName("Works for an occupied seat with more than 4 occupied seats around")
        void occupiedSeatWithMoreThanFourOccupied() {
            List<List<String>> testSeatMap = createTestSeatMap(
                    "#", ".", "#",
                    "#", "#", "#",
                    ".", "#", "#");
            assertEquals("L", new Day11().calculateSeatState(1, 1, testSeatMap));
        }

    }




    @Test
    @DisplayName("How many of works")
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

    @Test
    @DisplayName("Get Occupied Seats works")
    void getOccupiedSeats() {
        List<List<String>> testSeatMap = createTestSeatMap();
        assertEquals(2, new Day11().getOccupiedSeats(testSeatMap));
    }

    private List<List<String>> createTestSeatMap(String upperLeft, String upperMid, String upperRight,
                                                 String midLeft, String midMid, String midRight,
                                                 String botLeft, String botMid, String botRight) {
        List<List<String>> seatWithNeighbours = new ArrayList<>();
        List<String> rowTop = new ArrayList<>();
        rowTop.add(upperLeft);
        rowTop.add(upperMid);
        rowTop.add(upperRight);
        // "." : 1; "#" : 1; "L" : 1;
        List<String> rowMid = new ArrayList<>();
        rowMid.add(midLeft);
        rowMid.add(midMid);
        rowMid.add(midRight);
        // "." 1; seat; "L" : 1;
        List<String> rowBot = new ArrayList<>();
        rowBot.add(botLeft);
        rowBot.add(botMid);
        rowBot.add(botRight);
        // "L" : 1; "#" : 1; "." : 1;
        seatWithNeighbours.add(rowTop);
        seatWithNeighbours.add(rowMid);
        seatWithNeighbours.add(rowBot);
        return seatWithNeighbours;
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

}