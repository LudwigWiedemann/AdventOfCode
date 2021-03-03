package Tag10DerLarry;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import Shared.FileLoader;

public class Day10_2 {
    List<String> input;

    public Day10_2(String path) {
        input = FileLoader.loadFile(path);
    }

    public Day10_2() {
        this("puzzles/Day10Test.txt");
    }

    public static void main(String[] args) {
        Day10_2 d10 = new Day10_2("puzzles/Day10Test.txt");
//        System.out.println(d10.part1());
        System.out.println(d10.part2());
    }

    public int part1() {
        int[] diff = new int[10];
        diff[1]++;

        List<Integer> numbers = new ArrayList<>();
        numbers = convertStringListToIntList(input, Integer::parseInt);
        numbers.sort(Integer::compare);

        Arrays.fill(diff, 0);
        for (int i = 0; i < numbers.size() - 1; i++) {
            diff[numbers.get(i + 1) - numbers.get(i)]++;
        }
        diff[3]++;
        System.out.println(diff[1] * diff[3]);

        return 4;
    }

    public static long howManyPerValue(List<Integer> list, int val) {
        long numberOfWays = 0;
        for (Integer i : list) {
            numberOfWays += (i == val + 1) ? 1 : 0;
            numberOfWays += (i == val + 2) ? 1 : 0;
            numberOfWays += (i == val + 3) ? 1 : 0;
        }
        return numberOfWays == 0 ? 1: numberOfWays;
    }

    public BigInteger part2() {
        List<Integer> numbers = new ArrayList<>();
        numbers = convertStringListToIntList(input, Integer::parseInt);
        numbers.sort(Integer::compare);

        BigInteger numberOfWays = BigInteger.valueOf(howManyPerValue(numbers, 0));
        for(Integer i: numbers) {
            numberOfWays = numberOfWays.multiply(BigInteger.valueOf(howManyPerValue(numbers, i)));
        }
        return numberOfWays;
    }


    private static <T, U> List<U> convertStringListToIntList(List<T> listOfString, Function<T, U> function) {
        return listOfString.stream()
                .map(function)
                .collect(Collectors.toList());
    }
}
