package AdventOfCodePcg;

import Shared.FileLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day11 {
    List<String> input;
    List<List<String>> seatMap = new ArrayList<>();

    public Day11() {
        this("puzzles/Day11Test.txt");
    }

    // empty seat = 0, ful seat = 1, floor = .
    public Day11(String path) {
        input = FileLoader.loadFile(path);
        for (String row : input) {
            List<String> rowInMap = new ArrayList<>();
            for (int i = 0; i < row.length(); i++) {
                if (row.charAt(i) == 'L') {
                    rowInMap.add("L");
                } else {
                    rowInMap.add(".");
                }
            }
            seatMap.add(rowInMap);
        }
    }

    public static void main(String[] args) {
        Day11 d11 = new Day11();
        System.out.println(d11.part1());
    }

    public int part1() {
        while (true) {
            List<List<String>> newSeatMap = new ArrayList<>();
            printSeatMap();
            int rowCount = 0;
            for (List<String> row : seatMap) {
                List<String> newRow = new ArrayList<>();
                int seatCount = 0;
                for (String seat : row) {
                    List<String> adjacents = getAdjacents(rowCount, seatCount);
                    switch (seat) {
                        case "L":
                            if (howManyOf("#", adjacents) == 0) {
                                newRow.add("#");
                            }
                            break;
                        case "#":
                            if (howManyOf("#", adjacents) >= 4) {
                                newRow.add("L");
                            }
                            break;
                        case ".":
                            newRow.add(".");
                            break;
                    }
                    seatCount++;
                }
                rowCount++;
                newSeatMap.add(newRow);
            }
            if (!(seatMap == newSeatMap)) {
                seatMap = newSeatMap;

            } else {
                return getOccupiedSeats();
            }
            System.out.println("after");
            printSeatMap();
        }
    }

    private int getOccupiedSeats() {
        int occupied = 0;
        for (List<String> row: seatMap) {
            for (String seat: row) {
                if (seat.equals("#")) {
                    occupied++;
                }
            }
        }
        return occupied;
    }

    private void printSeatMap() {
        for (List<String> row: seatMap) {
            System.out.println(row);
        }
    }

    private int howManyOf(String state, List<String> adjacents) {
        int counter = 0;
        for (String adjacent: adjacents) {
            if(adjacent.equals(state)){
                counter++;
            }
        }
        return counter;
    }

    private List<String> getAdjacents(int row, int seat) {

        List<String> adjacents = new ArrayList<>();
        int rowTop = row -1 >= 0 ? row - 1 : seatMap.size()-1;
        int rowBot = row +1 < seatMap.size() ? row + 1 : 0;
        int seatRight = seat + 1 < seatMap.get(row).size() ? seat + 1 : 0;
        int seatLeft = seat - 1 >= 0 ? seat - 1 : seatMap.get(row).size()-1;

        adjacents.add(seatMap.get(rowTop).get(seatLeft)); // oben links
        adjacents.add(seatMap.get(rowTop).get(seat));     // oben mitte
        adjacents.add(seatMap.get(rowTop).get(seatRight)); // oben rechts
        adjacents.add(seatMap.get(row).get(seatRight));     // mitte rechts
        adjacents.add(seatMap.get(rowBot).get(seatRight)); // unten rechts
        adjacents.add(seatMap.get(rowBot).get(seat));     // unten mitte
        adjacents.add(seatMap.get(rowBot).get(seatLeft)); // unten links
        adjacents.add(seatMap.get(row).get(seatLeft));     // mitte links

        return adjacents;
    }
}
