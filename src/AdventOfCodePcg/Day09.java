package AdventOfCodePcg;

import Shared.FileLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day09 {
    List<String> input;

    public Day09() {
        this("puzzles/Day09Test.txt");
    }

    public Day09(String path) {
        input = FileLoader.loadFile(path);
    }

    public static void main(String[] args) {
        Day09 d9 = new Day09("puzzles/Day09.txt");
        System.out.println(d9.part2());
    }

    //uses 'isValidNumberForSearchRange' to check which number from the input is invalid
    public int part1(int searchRange) {

        for(int instructionCounter = searchRange; instructionCounter < input.size(); instructionCounter ++) {
            if(!isValidNumberForSearchRange(instructionCounter, searchRange)) {
                return Integer.parseInt(input.get(instructionCounter));
            }
        }
       return 0;
    }

    public int part2() {
        List<Integer> contiguousSet = getContiguousSet();
        assert contiguousSet != null;
        int max = Collections.max(contiguousSet);
        int min = Collections.min(contiguousSet);
        return max + min;
    }


    private List<Integer> getContiguousSet() {
        int resultFromPartOne = part1(25);
        ArrayList<Integer> contiguousSet = new ArrayList<>();
        for(int i = 0; i < input.size(); i++) {
            contiguousSet.clear();
            int inputAtI = Integer.parseInt(input.get(i));
            contiguousSet.add(inputAtI);
            int sum = inputAtI;
            for(int z = i + 1; sum < resultFromPartOne; z++) {
                int inputAtZ = Integer.parseInt(input.get(z));
                contiguousSet.add(inputAtZ);
                sum += inputAtZ;
                if(sum == resultFromPartOne) {
                    return contiguousSet;
                }
            }
        }
         return null;
    }


    //checks if the number at the given InstructionPointer is valid for a given searchRange
    private boolean isValidNumberForSearchRange(int instructionCounter, int searchRange) {
        int searchAreaMin = instructionCounter - searchRange;
        int searchAreaMax = instructionCounter -1;
        int numberAtInstructionCounter = Integer.parseInt(input.get(instructionCounter));
        for(int indexOfFirstSummand = searchAreaMin; indexOfFirstSummand < searchAreaMax; indexOfFirstSummand++) {
            int firstSummand = Integer.parseInt(input.get(indexOfFirstSummand));
            for(int indexOfSecondSummand = indexOfFirstSummand + 1; indexOfSecondSummand <= searchAreaMax; indexOfSecondSummand++) {
                int secondSummand = Integer.parseInt(input.get(indexOfSecondSummand));
                int sum = firstSummand + secondSummand;

                if(sum == numberAtInstructionCounter) {
                    return true;
                }
            }
        }
        return false;
    }
}
