package Day4;

import Shared.FileLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day04 {
    //
//  Code von Jakob
//

    private static final String[] REQUIRED_ATTRIBUTES = new String[] {
        "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"
    };

    private List<String> input;

    public Day04(List<String> input) {
        this.input = input;
    }

    public Day04() {
        this(FileLoader.loadFile("puzzles/Day4.txt"));
    }

    public int part1() {
        List<String> passports = Arrays.asList(String.join(" ", input).split(" {2}"));
        System.out.println(passports);

        return (int) passports.stream().filter(Day04::hasAllRequiredAttributes).count();

//        int count = 0;
//        for (String passport : passports) {
//            if (check(passport)) {
//                count+=1;
//            }
//        }
//
//        return count;
    }

    public static boolean hasAllRequiredAttributes(String passport) {
        return Arrays.stream(REQUIRED_ATTRIBUTES).allMatch(attribute -> passport.contains(attribute));
//        for (String attribute : REQUIRED_ATTRIBUTES) {
//            if (!passport.contains(attribute)) {
//                return false;
//            }
//        }
//        return true;
    }

    public static void main(String[] args) {
        Day04 d = new Day04();
        System.out.println("Part 1:");
        System.out.println(d.part1());
    }

}
