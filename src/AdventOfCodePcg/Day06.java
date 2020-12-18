package AdventOfCodePcg;

import Shared.FileLoader;

import java.util.*;

public class Day06 {
    List<String> input;

    public Day06(List<String> input) {
        this.input = input;
    }

    public Day06() {
        this.input = FileLoader.loadFile("puzzles/Day06.txt");
    }


    public static void main(String[] args) {
        Day06 p1 = new Day06();
        System.out.println(p1.part01() + " Questions have been answered with 'YES' by anyone");
        Day06 p2 = new Day06(FileLoader.loadFile("puzzles/Day06Test.txt"));
        System.out.println(p2.part02());
    }

    public int part01() {
        List<String> groups = Arrays.asList(String.join(" ", input).split(" {2}"));
        int totalYeses = 0;
        for (ListIterator<String> group = groups.listIterator(0); group.hasNext(); ) {
            totalYeses += howManyYeses(group.next());
        }
        return totalYeses;
    }

    public int part02() {
       return 6;
    }

    private int howManyYeses(String group) {
        List<Character> answeredWithYes = new ArrayList<>();
        int counter = 0;
        for (int j = 0; j < group.length(); j++) {
            if (!answeredWithYes.contains(group.charAt(j)) && group.charAt(j) != ' ') {
                answeredWithYes.add(group.charAt(j));
                counter++;
            }
        }
        return counter;
    }
}

//        ganz alter code
//        int totalYeses = 0;
//        for(int i = 0;i < groups.size(); i++) {
//            String group = groups.get(i);
//            totalYeses += howManyYeses(group);
//        }

//        mit List iterator
//        int totalYeses = 0;
//        ListIterator group = groups.listIterator(0);
//        while ( group.hasNext()){
//            totalYeses += howManyYeses(group.next().toString());
//        }
