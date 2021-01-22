package AdventOfCodePcg;

import Shared.FileLoader;
import jdk.dynalink.NamespaceOperation;

import java.util.*;

public class Day08 {
    private List<String> input;
    private List<String[]> commands = new ArrayList<>();
    List<Integer> iHasBeen = new ArrayList<>();
    int accumulator = 0;
    int i = 0;

    public Day08() {
        this("puzzles/Day08.txt");

    }

    public Day08(String path) {
        this.input = FileLoader.loadFile(path);
        for (String command : input) {
            commands.add(command.split(" "));
        }
    }

    public int part1() {
        return process();
    }

    public int part2() {
        // alle von hinten alle jmps und nops durchgehen und jedes mal eins ändern (schlechte performance)
        return 0;
    }


    private int process() {
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
        return accumulator;
    }

    public static void main(String[] args) {
        Day08 d8 = new Day08("puzzles/Day08.txt");
        System.out.println(d8.part1());
    }
}
