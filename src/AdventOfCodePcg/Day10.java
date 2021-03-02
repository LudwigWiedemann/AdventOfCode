package AdventOfCodePcg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import Shared.FileLoader;

public class Day10 {
    List<String> input;

    public Day10(String path) {
        input = FileLoader.loadFile(path);
    }

    public Day10() {
        this("puzzles/Day10Test.txt");
    }

    public static void main(String[] args) {
        Day10 d10 = new Day10("puzzles/Day10.txt");
        System.out.println(d10.part1());
    }

    public int part1() {
        List<Integer> joltages = convertStringListToIntList(input, Integer::parseInt);
        int joltDifference1 = 0;
        int joltDifference3 = 0;
        int adapterInUse = 0;
        int builtInAdapter = Collections.max(joltages) + 3;
        joltages.add(builtInAdapter);

        while (!(adapterInUse == builtInAdapter)) {
            if (joltages.contains(adapterInUse + 1)) {
                adapterInUse++;
                joltDifference1++;
            } else {
                if (joltages.contains(adapterInUse + 2)) {
                    adapterInUse += 2;
                } else {
                    if (joltages.contains(adapterInUse + 3)) {
                        adapterInUse += 3;
                        joltDifference3++;

                    }
                }
            }
        }
        return joltDifference1 * joltDifference3;
    }

    public int part2() {
        List<Integer[]> arangements = new ArrayList<>();

        return 8;
    }

    private static <T, U> List<U> convertStringListToIntList(List<T> listOfString, Function<T, U> function) {
        return listOfString.stream()
                .map(function)
                .collect(Collectors.toList());
    }
}
