package Day3;

import java.util.Arrays;
import java.util.List;

public class Main2 {

    public static void main(String[] args) {
        List<String> map = Shared.FileLoader.loadFile("U:\\AdventOfCode\\Day3.txt");
        List<int[]> slopes = Arrays.asList(
                new int[]{1, 1},
                new int[]{1, 3},
                new int[]{1, 5},
                new int[]{1, 7},
                new int[]{2, 1}
        );

        long total = slopes.stream()
                .mapToLong(slope -> count(slope, map))
                .reduce(1, (acc, v) -> acc * v);
        // .forEach(System.out::println);

        System.out.println(total);

//        int count1 = count(slopes.get(0), map);
//        int count2 = count(slopes.get(1), map);
//        int count3 = count(slopes.get(2), map);
//        int count4 = count(slopes.get(3), map);
//        int count5 = count(slopes.get(4), map);
//        long total;
//        total = (long) count1 * count2 * count3 * count4 * count5;
//
//        System.out.println("##########");
//        System.out.println(count1);
//        System.out.println(count2);
//        System.out.println(count3);
//        System.out.println(count4);
//        System.out.println(count5);
//        System.out.println("##########");
//        System.out.println(total);
    }


    public static int count(int[] slope, List<String> input) {
        int down = slope[0];
        int right = slope[1];
        int count = 0;
        int column = 0;
        for (int i = 0; i < input.size(); i = i + down) {
            String line = input.get(i);
            if (line.charAt(column % line.length()) == '#') {
                count++;
            }
            column += right;
        }

        return count;
    }
}
