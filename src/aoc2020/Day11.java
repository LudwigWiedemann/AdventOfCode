package aoc2020;

import Shared.FileLoader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Day11 {
    List<String> input;
    List<List<String>> seatMap = new ArrayList<>();
    int rows;
    int colums;

    public Day11() {
        this("puzzles/Day11Test.txt");
    }

    public Day11(String path) {
        input = FileLoader.loadFile(path);
        for (String row : input) {
            List<String> rowInMap = new ArrayList<>();
            for (int i = 0; i < row.length(); i++) {
                rowInMap.add(Character.toString(row.charAt(i)));
            }
            seatMap.add(rowInMap);
        }
        rows = seatMap.size();
        colums = seatMap.get(0).size();
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
            for (int row = 0; row < rows; row++) {
                List<String> listNewRow = new ArrayList<>();
                for (int seat = 0; seat < colums; seat++) {
                    listNewRow.add(calculateSeatState(row, seat));
                }
                secondSeatMap.add(listNewRow);
            }
            System.out.println(listsAreEqual(seatMap, secondSeatMap));
            if (listsAreEqual(seatMap, secondSeatMap)) {
                return getOccupiedSeats(seatMap);
            } else {
                seatMap = secondSeatMap;
            }
            System.out.println("DIESER DURCHGANG IST BEENDET: " + ++round);
        }
    }

    private String calculateSeatState(int rowNumber, int seatNumber) {
        String seat = seatMap.get(rowNumber).get(seatNumber);
        Collection<String> neighbours = getNeighbours(rowNumber, seatNumber, seatMap);
        switch (seat) {
            case "L":
                if (howManyOf("#", neighbours) == 0) {
                    return "#";
                } else {
                    return seat;
                }

            case "#":
                if (howManyOf("#", neighbours) >= 4) {
                    return "L";
                } else {
                    return seat;
                }

            default:
                return seat;
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

    private void printList(Collection<List<String>> list) {
        for (Collection<String> row : list) {
            System.out.println(row);
        }
    }

    public int howManyOf(String target, Collection<String> elements) {
        return (int) elements.stream().filter(el -> el.equals(target)).count();
    }


    public Collection<String> getNeighbours(int row, int column, List<List<String>> seatMap) {
        List<String> neighbours = new ArrayList<>();

        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = column - 1; c <= column + 1; c++) {
                if (r >= 0 && r < rows && c >= 0 && c < colums && !(r == row && c == column)) {
                    neighbours.add(seatMap.get(r).get(c));
                }
            }
        }
//        int rowTop = row - 1;
//        int rowBot = row + 1;
//        int seatRight = column + 1;
//        int seatLeft = column - 1;
//
//        String upperLeft = rowTop >= 0 && seatLeft >= 0 ? seatMap.get(rowTop).get(seatLeft) : ".";
//        String upperMiddle = rowTop >= 0 ? seatMap.get(rowTop).get(column) : ".";
//        String upperRight = rowTop >= 0 && seatRight < rows ? seatMap.get(rowTop).get(seatRight) : ".";
//        String middleRight = seatRight < rows ? seatMap.get(row).get(seatRight) : ".";
//        String lowerRight = rowBot < rows && seatRight < rows ? seatMap.get(rowBot).get(seatRight) : ".";
//        String lowerMiddle = rowBot < rows ? seatMap.get(rowBot).get(column) : ".";
//        String lowerLeft = rowBot < rows && seatLeft >= 0 ? seatMap.get(rowBot).get(seatLeft) : ".";
//        String middleLeft = seatLeft >= 0 ? seatMap.get(row).get(seatLeft) : ".";
//
//        neighbours.add(upperLeft);
//        neighbours.add(upperMiddle);
//        neighbours.add(upperRight);
//        neighbours.add(middleRight);
//        neighbours.add(lowerRight);
//        neighbours.add(lowerMiddle);
//        neighbours.add(lowerLeft);
//        neighbours.add(middleLeft);
        return neighbours;
    }

    public boolean listsAreEqual(List<List<String>> list1, List<List<String>> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int row = 0; row < list1.size(); row++) {
            for (int seat = 0; seat < list2.size(); seat++) {
                String seat1 = list1.get(row).get(seat);
                String seat2 = list2.get(row).get(seat);
                if(!seat1.equals(seat2)) {
                    return false;
                }
            }
        }
        return true;
    }
}
