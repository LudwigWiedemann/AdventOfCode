package AdventOfCodePcg;

import Shared.FileLoader;
import jdk.dynalink.NamespaceOperation;

import java.util.*;

public class Day08 {
    private List<String> input;
    private List<String[]> commands = new ArrayList<>();


    public Day08() {
        this("puzzles/Day08Test.txt");

    }

    public Day08(String path) {
        this.input = FileLoader.loadFile(path);
        for (String command : input) {
            commands.add(command.split(" "));
        }
    }

    private void part1() {
        int accumulator = 0;
        int i = 0;
        List<Integer> iHasBeen = new ArrayList<>();
        while (!iHasBeen.contains(i)) {
            iHasBeen.add(i);
            String[] command = commands.get(i);
            String instruction = command[0];
            int value = Integer.parseInt(command[1]);
            switch (instruction) {
                case "acc":
                    accumulator += value;
                    System.out.println("acc" + value);
                    i++;
                    break;
                case "jmp":
                    i += value;
                    System.out.println("jmp" + value);

                    break;
                case "nop":
                    System.out.println("nop" + value);
                    i++;
                    break;
            }

        }
        System.out.println(accumulator);


    }

    public static void main(String[] args) {
        Day08 d8 = new Day08("puzzles/Day08.txt");
        d8.part1();
    }
}
