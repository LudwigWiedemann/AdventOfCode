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
                rowInMap.add(Character.toString(row.charAt(i)));
            }
            seatMap.add(rowInMap);
        }
    }

    public static void main(String[] args) {
        Day11 d11 = new Day11();
        System.out.println(d11.part1());
    }

    public int part1() {
        int round = 0;
        while (true) {
            System.out.println("Seat Map: ");
            printList(seatMap);
            List<List<String>> secondSeatMap = new ArrayList<>();
            for (int rowCount = 0; rowCount < seatMap.size(); rowCount++) {
                List<String> row = seatMap.get(rowCount);
                List<String> rowNew = new ArrayList<>();
                for (int seatCount = 0; seatCount < row.size(); seatCount++) {
                    String seat = row.get(seatCount);
                    List<String> adjacents = getAdjacents(rowCount, seatCount);
                    switch (seat) {
                        case "L":
//                        System.out.println((howManyOf("#", adjacents)));
                            if (howManyOf("#", adjacents) == 0) {
                                rowNew.add("#");
                            } else {
                                rowNew.add(seat);
                            }
                            break;

                        case "#":
//                        System.out.println((howManyOf("#", adjacents)));
                            if (howManyOf("#", adjacents) >= 4) {
                                rowNew.add("L");
                            } else {
                                rowNew.add(seat);
                            }
                            break;

                        case ".":
                            rowNew.add(seat);
                            break;
                    }
                }
                secondSeatMap.add(rowNew);
            }
//            System.out.println("SECOND SEAT MAP: ");
//            printList(secondSeatMap);
            System.out.println(listsAreEqual(seatMap, secondSeatMap));
            if (listsAreEqual(seatMap, secondSeatMap)) {
                return getOccupiedSeats(seatMap);
            } else {
                seatMap = secondSeatMap;
            }
            System.out.println("DIESER DURCHGANG IST BEENDET: " + ++round);
        }
    }

    private int getOccupiedSeats(List<List<String>> list) {
        int occupied = 0;
        for (List<String> row : list) {
            for (String seat : row) {
                if (seat.equals("#")) {
                    occupied++;
                }
            }
        }
        return occupied;
    }

    private void printList(List<List<String>> list) {
        for (List<String> row : list) {
            System.out.println(row);
        }
    }

    private int howManyOf(String state, List<String> adjacents) {
        int counter = 0;
        for (String adjacent : adjacents) {
            if (adjacent.equals(state)) {
                counter++;
            }
        }
        return counter;
    }

    private List<String> getAdjacents(int row, int seat) {
        List<String> adjacents = new ArrayList<>();
        int rowTop = row - 1;
        int rowBot = row + 1;
        int seatRight = seat + 1;
        int seatLeft = seat - 1;

        String upperLeft = rowTop >= 0 && seatLeft >= 0 ? seatMap.get(rowTop).get(seatLeft) : ".";
        String upperMiddle = rowTop >= 0 ? seatMap.get(rowTop).get(seat) : ".";
        String upperRight = rowTop >= 0 && seatRight < seatMap.size() ? seatMap.get(rowTop).get(seatRight) : ".";
        String middleRight = seatRight < seatMap.size() ? seatMap.get(row).get(seatRight) : ".";
        String lowerRight = rowBot < seatMap.size() && seatRight < seatMap.size() ? seatMap.get(rowBot).get(seatRight) : ".";
        String lowerMiddle = rowBot < seatMap.size() ? seatMap.get(rowBot).get(seat) : ".";
        String lowerLeft = rowBot < seatMap.size() && seatLeft >= 0 ? seatMap.get(rowBot).get(seatLeft) : ".";
        String middleLeft = seatLeft >= 0 ? seatMap.get(row).get(seatLeft) : ".";

        adjacents.add(upperMiddle);
        adjacents.add(upperLeft);
        adjacents.add(upperRight);
        adjacents.add(middleRight);
        adjacents.add(middleLeft);
        adjacents.add(lowerLeft);
        adjacents.add(lowerMiddle);
        adjacents.add(lowerRight);
        return adjacents;
    }

    private boolean listsAreEqual(List<List<String>> list1, List<List<String>> list2) {
        List<Boolean> isEqual = new ArrayList<>();
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int row = 0; row < list1.size(); row++) {
            for (int seat = 0; seat < list2.size(); seat++) {
                String seat1 = list1.get(row).get(seat);
                String seat2 = list2.get(row).get(seat);
                isEqual.add(seat1.equals(seat2));
            }
        }
        return !(isEqual.contains(false));
    }
}
