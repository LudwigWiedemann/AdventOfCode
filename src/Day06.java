import Shared.FileLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Day06 {
    List<String> input;
//    public Day06(List<String> input){
//        this.input = input;
//    }

    public Day06() {
        this.input = FileLoader.loadFile("puzzles/Day06.txt");
    }


    public static void main(String[] args) {
        Day06 d = new Day06();
        System.out.print(d.run());
        System.out.println(" Questions have been answered with 'YES'");
    }

    public int run() {
        List<String> groups = Arrays.asList(String.join(" ", input).split(" {2}"));
        int totalCount = 0;
        for(int i = 0;i < groups.size(); i++) {
            String group = groups.get(i);
            totalCount += howManyYeses(group);
        }
        return totalCount;
    }

    private int howManyYeses(String group) {
        List<Character> answeredWithYes = new ArrayList<>();
        int counter = 0;
        for(int j = 0; j < group.length(); j++){
            if(!answeredWithYes.contains(group.charAt(j)) && group.charAt(j) != ' '){
                answeredWithYes.add(group.charAt(j));
                counter++;
            }
        }
        return counter;
    }


}
