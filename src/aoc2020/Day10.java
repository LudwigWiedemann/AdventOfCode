package aoc2020;

import java.util.ArrayList;
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
//        System.out.println(d10.part1());
        System.out.println(d10.part2());
    }

    public int part1() {
        List<Integer> numbers = convertStringListToIntList(input, Integer::parseInt);
        numbers.sort(Integer::compare);
        System.out.println(numbers);
        List<Integer> diff = new ArrayList<>();
        diff.clear();
        diff.add(1);
        for (int i = 0; i < numbers.size() - 1; i++) {
            diff.add(numbers.get(i + 1) - numbers.get(i));
        }
        diff.add(3);
        int counter = Collections.frequency(diff, 1) * Collections.frequency(diff, 3);
        return counter;
    }

    public long part2() {
        List<Integer> numbers = convertStringListToIntList(input, Integer::parseInt);
        numbers.sort(Integer::compare);
        System.out.println(numbers);
        long total = 1;
        int streak = 0;
        int prev = 0;
        for (int i = 0; i < numbers.size(); i++) {
            int number = numbers.get(i);
            if (number == prev + 1) {
                System.out.println("Before " + number + " : "  + streak);

                streak++;
                System.out.println("After " + number + " : " + streak);
            } else {
                System.out.println("Combs(streak): " + combs(streak));
                total *= combs(streak);
                System.out.println(total);
                streak = 0;
            }
            prev = number;
        }
        System.out.println("Streak: " + streak);
        total *= combs(streak);
        return total;
    }



    private int combs(int n) {
        if (n == 0) {
            return 1;
        } else {
            if (n == 1) {
                return 1;
            } else {
                if (n == 2) {
                    return 2;
                } else {
                    return combs(n - 1) + combs(n - 2) + combs(n - 3);
                }
            }
        }
    }

    private static <T, U> List<U> convertStringListToIntList(List<T> listOfString, Function<T, U> function) {
        return listOfString.stream()
                .map(function)
                .collect(Collectors.toList());
    }
}
