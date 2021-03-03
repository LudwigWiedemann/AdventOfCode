package Tag10DerLarry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import Shared.FileLoader;

public class Day10_1 {
    List<String> input;

    public Day10_1(String path) {
        input = FileLoader.loadFile(path);
    }

    public Day10_1() {
        this("puzzles/Day10Test.txt");
    }

    public static void main(String[] args) {
        Day10_1 d10 = new Day10_1("puzzles/Day10.txt");
//        System.out.println(d10.part1());
        System.out.println(d10.part2());
    }

    public int part1() {
        int joltDifference1 = 0;
        int joltDifference3 = 0;
        List<Integer> arrangement = getArrangement();
        for (int i = 0; i < arrangement.size() - 1; i++) {
            int currentAdapter = arrangement.get(i);
            int nextAdapter = arrangement.get(i + 1);
            if (nextAdapter - currentAdapter == 1) {
                joltDifference1++;
            }
            if (nextAdapter - currentAdapter == 3) {
                joltDifference3++;
            }
        }
        return joltDifference1 * joltDifference3;
    }

    private List<Integer> getArrangement() {
        List<Integer> joltages = convertStringListToIntList(input, Integer::parseInt);

        int adapterInUse = 0;
        int builtInAdapter = Collections.max(joltages) + 3;
        joltages.add(builtInAdapter);
        List<Integer> arrangement = new ArrayList<>();
        arrangement.add(0);
        while (!(adapterInUse == builtInAdapter)) {
            if (joltages.contains(adapterInUse + 1)) {
                adapterInUse++;
                arrangement.add(adapterInUse);
            } else {
                if (joltages.contains(adapterInUse + 2)) {
                    adapterInUse += 2;
                    arrangement.add(adapterInUse);
                } else {
                    if (joltages.contains(adapterInUse + 3)) {
                        adapterInUse += 3;
                        arrangement.add(adapterInUse);
                    }
                }
            }
        }

        return arrangement;
    }

    public int part2() {
        System.out.println("HELLO YOUSTUPIDMOTHERFUVCKERCANYOUSTORAPPEARINGINMYCODE???");
        List<List<Integer>> arrangements = new ArrayList<>();
        List<Integer> firstList = getArrangement();
        for (int i = 0; i < firstList.size() - 2; i++) {
            List<Integer> changedList = changeList(firstList, i);
            if (!(arrangements.contains(changedList))) {
                arrangements.add(changedList);
            }
        }
        System.out.println(arrangements);
        for (int z = 1; z < arrangements.size(); z++) {
            for (int i = 0; i < arrangements.get(1).size() - 2; i++) {
                List<Integer> twiceChangedList = changeList(arrangements.get(z), i);
                System.out.println("HELLO YOUSTUPIDMOTHERFUVCKERCANYOUSTORAPPEARINGINMYCODE???");

                if (!(arrangements.contains(twiceChangedList))) {
                    arrangements.add(twiceChangedList);
                }
            }
        }
        System.out.println(arrangements);
        return arrangements.size();
    }

    private List<Integer> changeList(List<Integer> list, int changeIndex) {

        List<Integer> listCopy = new ArrayList<>(list);
        if (changeIndex + 2 < listCopy.size()) {
            if (listCopy.get(changeIndex + 2) - listCopy.get(changeIndex) < 4) {
                listCopy.remove(changeIndex + 1);
            }
        }
        return listCopy;
    }

    private static <T, U> List<U> convertStringListToIntList(List<T> listOfString, Function<T, U> function) {
        return listOfString.stream()
                .map(function)
                .collect(Collectors.toList());
    }
}
