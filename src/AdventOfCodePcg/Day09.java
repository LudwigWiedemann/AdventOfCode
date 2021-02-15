package AdventOfCodePcg;

import Shared.FileLoader;
import java.util.List;

public class Day09 {
    List<String> input;
    public Day09() {
        this("puzzles/Day09Test.txt");
    }
DubisteinHuhrensohngit a
    public Day09(String path) {
        input = FileLoader.loadFile(path);


    }

    public static void main(String[] args) {
        Day09 d9 = new Day09("puzzles/Day09.txt");
        System.out.println(d9.part1());
        System.out.println(d9.input);
    }

    public int part1() {
        for(int instructionCounter = 25; instructionCounter < input.size();instructionCounter ++) {
            if(!pruefeObValide(instructionCounter)) {
                return Integer.parseInt(input.get(instructionCounter));
            }
        }
       return 0;
    }

    private boolean pruefeObValide(int insCount) {
        int toSearchMin = insCount - 25;
        int toSearchMax = insCount -1;
        int zuPruefendeZahl = Integer.parseInt(input.get(insCount));

        for(int i = toSearchMin; i < toSearchMax; i++) {
            int ersterSummand = Integer.parseInt(input.get(i));

            for(int z = i + 1; z <= toSearchMax; z++) {
                int zweiterSummand = Integer.parseInt(input.get(z));
                int ergebnis = ersterSummand + zweiterSummand;
                System.out.println("zu prüfende Zahl: " + zuPruefendeZahl);
                System.out.println( ersterSummand + " + " + zweiterSummand + " = " + ergebnis);
                if(ergebnis == zuPruefendeZahl) {
                    return true;
                }
            }
        }
        return false;
    }
}
