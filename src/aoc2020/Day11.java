package aoc2020;

import Shared.FileLoader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Day11 {
    List<String> input;
    List<List<String>> seatMap = new ArrayList<>();
    int rows;
    int column;


    public Day11() {
        this("puzzles/Day11.txt");
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
        column = seatMap.get(0).size();
    }

    public static void main(String[] args) {
        Day11 d11 = new Day11();
        System.out.println(d11.part1());
    }

    public int part2() {
        return 0;
    }

    protected Collection<String> getFirstSeatInRow (int seatNumber, List<String> row) {
        Collection<String> firstSeats = new ArrayList<>();
        for (int c = seatNumber - 1; c < seatNumber + 1; c++) {
            if (c == seatNumber) c++;
            int col = c;
            while (row.get(col).equals(".") && col < row.size() && col >= 0) {
                if (col < seatNumber) {
                    col--;
                }
                if (col > seatNumber) {
                    col++;
                }
            }
            firstSeats.add(row.get(col));
        }
        return firstSeats;
    }

    protected Collection<String> getFirstSeatInColumn (int seatNumber, List<String> column) {
        Collection<String> firstSeats = new ArrayList<>();
        for (int r = seatNumber - 1; r < seatNumber + 1; r++) {
            if (r == seatNumber) r++;
            int row = r;
            while (column.get(row).equals(".") && row < column.size() && row >= 0) {
                if (row < seatNumber) {
                    row--;
                }
                if (row > seatNumber) {
                    row++;
                }
            }
            firstSeats.add(column.get(row));
        }
        return firstSeats;
    }
    // TODO: getFirstSeatDiagonal() {}

    protected int part1() {
        int round = 0;
        while (true) {
            System.out.println("Seat Map: ");
            printCollectionOfLists(seatMap);
            List<List<String>> secondSeatMap = new ArrayList<>();
            for (int row = 0; row < rows; row++) {
                List<String> listNewRow = new ArrayList<>();
                for (int seat = 0; seat < column; seat++) {
                    listNewRow.add(calculateSeatState(row, seat, seatMap));
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

    protected String calculateSeatState(int rowNumber, int seatNumber, List<List<String>> seatMap) {
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

    protected int getOccupiedSeats(List<List<String>> list) {
        int occupiedSeats = 0;
        for(List<String> row: list) {
            occupiedSeats += (int) row.stream().filter((elem) -> elem.equals("#")).count();
        }
        return occupiedSeats;
    }

    protected void printCollectionOfLists(Collection<List<String>> list) {
        for (Collection<String> row : list) {
            System.out.println(row);
        }
    }

    protected int howManyOf(String target, Collection<String> elements) {
        return (int) elements.stream().filter(el -> el.equals(target)).count();
    }


    protected Collection<String> getNeighbours(int row, int column, List<List<String>> seatMap) {
        List<String> neighbours = new ArrayList<>();
        int rows = seatMap.size();
        int colums = seatMap.get(0).size();
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = column - 1; c <= column + 1; c++) {
                if (r >= 0 && r < rows && c >= 0 && c < colums && !(r == row && c == column)) {
                    neighbours.add(seatMap.get(r).get(c));
                }
            }
        }
        return neighbours;
    }

    protected boolean listsAreEqual(List<List<String>> list1, List<List<String>> list2) {
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
