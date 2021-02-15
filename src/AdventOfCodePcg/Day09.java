package AdventOfCodePcg;

import Shared.FileLoader;
import java.util.List;

public class Day09 {
    List<String> input;

    public Day09() {
        this("puzzles/Day09Test.txt");
    }

    public Day09(String path) {
        input = FileLoader.loadFile(path);
    }

    public static void main(String[] args) {
        Day09 d9 = new Day09("puzzles/Day09.txt");
        System.out.println(d9.part1());
        System.out.println(d9.input);
    }

    public int part1() {
        int searchAreaSize = 25;
        for(int instructionCounter = 25; instructionCounter < input.size(); instructionCounter ++) {
            int searchAreaMin = instructionCounter - searchAreaSize;
            int searchAreaMax = instructionCounter -1;
            int valueAtInstructionCounter = Integer.parseInt(input.get(instructionCounter));

            if(!isValid(valueAtInstructionCounter, searchAreaMin, searchAreaMax)) {
                return valueAtInstructionCounter;
            }
        }
       return 0;
    }

    private boolean isValid(int numberToCheck, int searchAreaMin, int searchAreaMax) {



        for(int i = searchAreaMin; i < searchAreaMax; i++) {
            int ersterSummand = Integer.parseInt(input.get(i));

            for(int z = i + 1; z <= searchAreaMax; z++) {
                int zweiterSummand = Integer.parseInt(input.get(z));
                int ergebnis = ersterSummand + zweiterSummand;
                System.out.println("zu prüfende Zahl: " + numberToCheck);
                System.out.println( ersterSummand + " + " + zweiterSummand + " = " + ergebnis);
                if(ergebnis == numberToCheck) {
                    return true;
                }
            }
        }
        return false;
    }
}
