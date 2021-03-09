package aoc2020;

import Shared.FileLoader;

import java.util.*;

public class Day08 {
    private final List<String[]> program = new ArrayList<>();

    private int instructionPointer = 0;
    private int accumulator = 0;
    private List<String[]> memory;

    private final List<Integer> instructionPointerHistory = new ArrayList<>();

    public Day08() {
        this("puzzles/Day08Test.txt");
    }

    public Day08(String path) {
        List<String> input = FileLoader.loadFile(path);
        for (String command : input) {
            program.add(command.split(" "));
        }
    }

    public static void main(String[] args) {
        Day08 d8 = new Day08("puzzles/Day08.txt");
        System.out.println(d8.part1());
        System.out.println(d8.part2());
    }

    public int part1() {
        run(program);
        return accumulator;
    }

    public int part2() {
        for (int addressToPatch = 0; addressToPatch < program.size(); addressToPatch++) {
            List<String[]> patchedProgram = duplicateAndPatchAt(addressToPatch);

            run(patchedProgram);
            if (terminatedCorrectly()) {
                return accumulator;
            }
        }
        throw new IllegalStateException("Puzzle not solvable");
    }

    private void run(List<String[]> programToRun) {
        reset(programToRun);
        while (!terminatedCorrectly() && instructionPointerNotInHistory()) {
            processNextInstruction();
        }
    }

    public void reset(List<String[]> programToLoad) {
        instructionPointer = 0;
        accumulator = 0;
        memory = programToLoad;
        instructionPointerHistory.clear();
    }

    private List<String[]> duplicateAndPatchAt(int address) {
        List<String[]> newCommands = new ArrayList<>(program);

        String[] newInstruction = new String[]{"nop", program.get(address)[1]};
        newCommands.set(address, newInstruction);

        return newCommands;
    }

    private boolean terminatedCorrectly() {
        return instructionPointer == memory.size();
    }

    private boolean instructionPointerNotInHistory() {
        return !instructionPointerHistory.contains(instructionPointer);
    }

    private String getOperator(int address) {
        return memory.get(address)[0];
    }

    private int getOperand(int address) {
        return Integer.parseInt(memory.get(address)[1]);
    }

    private void processNextInstruction() {
        instructionPointerHistory.add(instructionPointer);
        String operator = getOperator(instructionPointer);
        int operand = getOperand(instructionPointer);
        computeInstruction(operator, operand);
    }

    private void computeInstruction(String operator, int operand) {
        switch (operator) {
            case "acc" -> {
                accumulator += operand;
                System.out.println("acc " + operand);
                instructionPointer++;
            }
            case "jmp" -> {
                instructionPointer += operand;
                System.out.println("jmp " + operand);
            }
            case "nop" -> {
                System.out.println("nop " + operand);
                instructionPointer++;
            }
        }
    }
}
